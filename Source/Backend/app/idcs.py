import json
import logging
import os.path
import random
import string
import time
import urllib.request
from concurrent.futures import ThreadPoolExecutor, as_completed
from datetime import datetime, timedelta

import psutil
import schedule
from flask import Flask, jsonify, request
from flaskext.mysql import MySQL
from werkzeug.security import check_password_hash, generate_password_hash

import controllers._convert as _cv
import controllers._host as _host
import controllers._port as _port
import controllers._port_vuln as _pv
import controllers._task as _task
import controllers._user as _user
import controllers._verify_code as _vc
import controllers._vuln as _vuln
import controllers.vuln_scanner as _vs
import mail.mail_services as mail
import nmap_tools._extract_data as _ed
from utils.config_utils import get_base_config
from threading import Thread

app = Flask(__name__)
mysql = MySQL()
#Read config file

_config = get_base_config()

# logging config
logging.basicConfig(level=logging.DEBUG, filename='log.txt', filemode='w', format=(
    '%(asctime)s:\t'
    '%(filename)s:'
    '%(funcName)s():'
    '%(lineno)d\t'
    '%(message)s'
))

MAX_WORKER = _config.workers

# MySQL configurations
app.config['MYSQL_DATABASE_USER'] = _config.db_user
app.config['MYSQL_DATABASE_PASSWORD'] = _config.db_password
app.config['MYSQL_DATABASE_DB'] = _config.db_name
app.config['MYSQL_DATABASE_HOST'] = _config.db_host
app.config['MYSQL_DATABASE_PORT'] = _config.db_port
mysql.init_app(app)
conn = mysql.connect()

# 
# -----http directional------
# 

# verify user, return user when user_name and password matched
@app.route("/idcs/user/verify", methods=['POST'])
def verify_user():
    user_stamp = request.json
    if user_stamp is None:
        return 204  #no-content
    user_name = user_stamp["userName"]
    user = _user._get_by_name(conn, user_name)
    if check_password_hash(user["password"], user_stamp["password"]):
        return jsonify(user), 202   #accepted
    return jsonify({"status":"failed"}), 404    #not-found

# update user info
@app.route("/idcs/user/update/info", methods=['POST'])
def update_information():
    user = request.json
    result = _user._update_info(conn, user)
    if result:
        return return_change_status(True), 202
    return return_change_status(False), 403

# update user password
@app.route("/idcs/user/update/password", methods = ['POST'])
def update_password():
    user = request.json
    user["password"] = generate_password_hash(user["password"])
    return return_change_status(_user._update_password(conn, user["userName"], user["password"]))

# add an user to db
@app.route("/idcs/user/add", methods = ['POST'])
def add_an_user():
    user = request.json
    user["password"] = generate_password_hash(user["password"])
    result = _user._add(conn, user)
    if result is None:
        return {"status":"account has taked"}, 303  #see-other
    if result:
        return return_change_status(result), 201    #created
    return return_change_status(result), 409    #conflict

# get list users from db
@app.route("/idcs/user/list", methods = ['POST'])
def get_list_users():
    return _user._get_list(conn)

# 
# @app.route("/idcs/user/forgot", methods = ['POST'])
# def forgot_password():
#     user_stamp = request.json
#     user = get_user_by_name(conn, user_stamp["userName"])
#     if user_stamp["email"] == user["email"] and user_stamp["code"] == user["phone"]:
#         return True

# genarate authen code and send email to user
@app.route("/idcs/user/send-email", methods = ['POST'])
def send_email_code():
    data = request.json
    verify_code = _vc.get_code_by_user(conn, data["userName"])
    verify = {}
    if verify_code is None:
        verify["verify_code"] = genarate_random_code(6)
        verify["time_stamp"] = datetime.now() + timedelta(hours=1)
        verify["user_name"] = data["userName"]
        result = _vc._add_verify_code(conn, verify)
    else:
        verify = _cv.verify_code_to_dict(verify_code)
    return return_change_status(mail.send_access_code_to_mail(data, verify["verify_code"],verify["time_stamp"]))


# 
@app.route("/idcs/user/register", methods = ['POST'])
def user_register():
    user = request.json
    user["password"] = generate_password_hash(user["password"])
    user["credits"] = 1
    # userType = 3 mean account have been created.
    user["userType"] = 3
    return return_change_status(_user._add(conn, user))

@app.route("/idcs/email/unique", methods = ['POST'])
def user_by_email():
    data = request.json
    user_name = _user._get_by_email(conn, data["email"])
    if(user_name is None or user_name == data["userName"]):
        return jsonify({"status":"accept"}), 202
    else:
        return jsonify({"status":"reject"}), 404

@app.route("/idcs/email/verify", methods = ['POST'])
def email_verify():
    user = request.json
    verify = _vc._get_verify_code(conn, user)
    if verify is not None:
        if user["verify_code"] == verify[2]:
            # update user type to #2 (FREE Account)
            user["userType"] = 2
            stt = _user._update_user_type(conn, user)
            if stt:
                #  out of date verify code
                _vc._delete_verify_code(conn, user["userName"])
                return return_change_status(stt), 202
    return return_change_status(False), 404

# 
# HOST
# 

# input host= {"ipv4"=ipv4}
@app.route("/idcs/host/get", methods = ['POST'])
def get_host_from_db():
    # get smt
    data = request.json
    #check user has credits
    _credits = _user._get_credits(conn, data["userName"])
    if _credits[0] < 1:
        return jsonify({"status":"not has credits"}), 406 #not-acceptable
    host_db = _host._get_by_ip(conn, data)
    # if host haven't in db or host no longer update in 30 days
    if not host_db or (host_db[6] + timedelta(days = 30)) < datetime.now():
        # check task has exits
        if not _task._get_from_db(conn, data):
            # create a task scan
            data["status"] = 0      #set status 0 mean
            data["time_stamp"] = datetime.now()     #set time stamp
            # add new task to db
            if _task._add_to_db(conn, data):   
                return jsonify({"status":"task created"}), 201
        return jsonify({"status":"task processing"}), 206 #pending
    host = _cv.host_to_dict(host_db)
    ports_db = _port._get_by_host(conn, host["ipv4"])
    if ports_db is not None:
        ports = _cv.ports_to_dict(ports_db)
        for port in ports.values():
            vulns = _pv._get_by_port(conn, port["port_id"])
            list_vuln = {}
            if vulns is not None:
                for v in vulns:
                    vuln = _vuln.get_by_num(conn, v[1])
                    if vuln is not None:
                        list_vuln[v[1]] = {"cve_num":v[1],"cve_status":vuln[1], "cve_desc":vuln[2]}
            port["vuln"] = list_vuln
    host["ports"] = ports
    #reduce credits
    result = _user._update_credits(conn, data["userName"], _credits - 1)
    return host, 202 #successful


@app.route("/idcs/task/get", methods = ['POST'])
def get_task_from_db():
    user = request.json
    tasks = _task._get_by_user_name(conn, user["user_name"])
    if tasks:
        return _cv.tasks_to_dict(tasks)
    return return_change_status(False)


# genarate random code
def genarate_random_code(code_lenght):
    letters = string.ascii_letters
    return ''.join(random.choice(letters) for i in range(code_lenght))


# return value with dict 'status':'<status_task>'
def return_change_status(b):
    if b:
        return jsonify({"status":"completed"})
    else: 
        return jsonify({"status":"failed"})


def do_task(task):
    last_updated = datetime.now()
    # scan host with nmap
    s_host = _host._scan_host(task[0])
    # s_host = {'nmap': {'command_line': 'nmap -oX - -sn --script whois-ip 39.106.116.39', 'scaninfo': {}, 'scanstats': {'timestr': 'Tue Apr 21 22:53:52 2020', 'elapsed': '2.56', 'uphosts': '1', 'downhosts': '0', 'totalhosts': '1'}}, 'scan': {'39.106.116.39': {'hostnames': [{'name': '', 'type': ''}], 'addresses': {'ipv4': '39.106.116.39'}, 'vendor': {}, 'status': {'state': 'up', 'reason': 'syn-ack'}, 'hostscript': [{'id': 'whois-ip', 'output': 'Record found at whois.apnic.net\ninetnum: 39.96.0.0 - 39.108.255.255\nnetname: ALISOFT\ndescr: Aliyun Computing Co., LTD\ncountry: CN\nperson: Li Jia\nemail: jiali.jl@alibaba-inc.com'}]}}}
    logging.debug(s_host)
    if s_host is None or s_host["nmap"]["scanstats"]["uphosts"] == 0:
        return {"status":"host down or not public"}
    fail_string = "ERROR: Script execution failed"
    if fail_string in s_host["scan"][task[0]]["hostscript"][0]["output"]:
        # log error
        logging.error("Fail to scan host" + s_host["scan"][task[0]]["hostscript"][0]["output"])
        # exit scan
        return jsonify({"status":"failed to scan host"})
    # extract host from nmap scan to dict
    host = _ed._result_to_host(s_host)
    # check host in db
    host_stamp = _host._get_by_ip(conn, host)
    if host_stamp is None:
        # add new host scan to db
        if _host.add_host_to_db(conn, host):
            logging.info("host added!")
        else:
            logging.info("cannot add host")
    else:
        # update new host-scan to db
        if _host._update_to_db(conn, host):
            logging.info("host updated!")
        else:
            logging.info("cannot update host")
    # scan port with nmap
    s_ports = _port._nmap_scan(task[0])
    # s_ports = {'nmap': {'command_line': 'nmap -oX - -Pn -n -O -sC -sV -p- --min-parallelism 10 39.106.116.39', 'scaninfo': {'tcp': {'method': 'syn', 'services': '1-65535'}}, 'scanstats': {'timestr': 'Fri Apr 24 01:10:36 2020', 'elapsed': '942.68', 'uphosts': '1', 'downhosts': '0', 'totalhosts': '1'}}, 'scan': {'39.106.116.39': {'hostnames': [{'name': '', 'type': ''}], 'addresses': {'ipv4': '39.106.116.39'}, 'vendor': {}, 'status': {'state': 'up', 'reason': 'user-set'}, 'uptime': {'seconds': '9963609', 'lastboot': 'Mon Dec 30 17:30:27 2019'}, 'tcp': {80: {'state': 'open', 'reason': 'syn-ack', 'name': 'http', 'product': 'Apache Tomcat/Coyote JSP engine', 'version': '1.1', 'extrainfo': '', 'conf': '10', 'cpe': 'cpe:/a:apache:coyote_http_connector:1.1', 'script': {'http-server-header': 'Apache-Coyote/1.1', 'http-title': "Site doesn't have a title (text/html;charset=UTF-8)."}}, 443: {'state': 'open', 'reason': 'syn-ack', 'name': 'http', 'product': 'Apache Tomcat/Coyote JSP engine', 'version': '1.1', 'extrainfo': '', 'conf': '10', 'cpe': 'cpe:/a:apache:coyote_http_connector:1.1', 'script': {'ssl-cert': 'Subject: commonName=*.aizushidai.com\nSubject Alternative Name: DNS:*.aizushidai.com, DNS:aizushidai.com\nNot valid before: 2019-03-28T00:00:00\nNot valid after:  2020-04-26T12:00:00', 'ssl-date': '2020-04-23T18:10:33+00:00; 0s from scanner time.'}}, 3389: {'state': 'open', 'reason': 'syn-ack', 'name': 'rdp', 'product': '', 'version': '', 'extrainfo': '', 'conf': '3', 'cpe': '', 'script': {'ssl-cert': 'Subject: commonName=iZi42ol9hq1gamZ\nNot valid before: 2020-01-20T16:00:01\nNot valid after:  2020-07-21T16:00:01'}}, 8089: {'state': 'open', 'reason': 'syn-ack', 'name': 'http', 'product': 'Apache Tomcat/Coyote JSP engine', 'version': '1.1', 'extrainfo': '', 'conf': '10', 'cpe': 'cpe:/a:apache:coyote_http_connector:1.1', 'script': {'http-server-header': 'Apache-Coyote/1.1', 'http-title': 'Apache Tomcat/7.0.61 - Error report'}}, 8329: {'state': 'open', 'reason': 'syn-ack', 'name': '', 'product': '', 'version': '', 'extrainfo': '', 'conf': '', 'cpe': ''}, 8349: {'state': 'open', 'reason': 'syn-ack', 'name': '', 'product': '', 'version': '', 'extrainfo': '', 'conf': '', 'cpe': ''}}, 'portused': [{'state': 'open', 'proto': 'tcp', 'portid': '80'}, {'state': 'closed', 'proto': 'tcp', 'portid': '1'}], 'osmatch': [{'name': 'Microsoft Windows Server 2012 R2', 'accuracy': '100', 'line': '75297', 'osclass': [{'type': 'general purpose', 'vendor': 'Microsoft', 'osfamily': 'Windows', 'osgen': '2012', 'accuracy': '100', 'cpe': ['cpe:/o:microsoft:windows_server_2012:r2']}]}]}}}
    logging.debug(s_ports)
    if s_ports is None or s_ports["scan"][task[0]]["tcp"] is None:
        return {"status":"no port open"}
    ports = _ed._result_to_ports(s_ports)
    for port in ports.values():
        port_tmp = _port._check_exits_on_db(conn, task[0], port["port_num"])
        result = None
        if port_tmp is not None:
            # set port_id
            port["port_id"] = port_tmp[0]
            result = _port._update_by_id(conn, port)
        else:
            # add port to db
            result = _port._add_to_db(conn, port)
        if result:
            logging.info("updated port: {} of {}".format(port["port_num"], port["host_ip"]))
        # reload port from db
        port = _port._check_exits_on_db(conn, task[0], port["port_num"])
        port = _cv.port_to_dict(port)
        if port["service_name"] == "rdp":
            # cpe data separation
            l_cpe = port["cpe"].split(":")
            # vulns = _vs.scan(port["host_ip"],port["port_num"],l_cpe[len(l_cpe)-1],"rdp")
            vulns = _vs.scan(port["host_ip"],port["port_num"],"rdp")
            # get exits vuln by port
            exits_vulns = _pv._get_by_port(conn, port["port_id"])
            # if exits_vulns is not None:
            if vulns is not None:
                # check lists contain list
                # if list_exits vuln none then add all of vuln to db
                if exits_vulns is None:
                    for vuln in vulns:
                        port_vuln = [port["port_id"], vuln, last_updated]
                        result = _pv._add_to_db(conn, port_vuln)
                        if result:
                            logging.info("Port {0} has vuln: {1}".format(port_vuln[0], port_vuln[1]))
                        else:
                            logging.warning("Cannot add vuln {0} to port {1}".format(port_vuln[1], port_vuln[0]))
                else:
                    for ev in exits_vulns:
                        # convert ev tuple to list
                        ev = list(ev)
                        # remove vuln if not still exits.
                        if ev[1] not in vulns:
                            result = _pv._remove_row(conn, ev)
                            if result: logging.info("Remove vuln {0} in port {1}".format(ev[1], ev[0]))
                            else: logging.info("Cannot remove vuln {0} in port {1}".format(ev[1], ev[0]))
                        # update if can scan in target
                        else:
                            ev[2] = last_updated
                            if _pv._update_by_id(conn, ev):
                                logging.info("Update vuln {0} in port {1}".format(ev[1], ev[0]))
                                # remove updated item
                                vulns.remove(ev[1])
                    if vulns is not None and len(vulns) > 0:
                        # query each vuln
                        for vuln in vulns:
                            port_vuln = [port["port_id"], vuln, last_updated]
                            result = _pv._add_to_db(conn, port_vuln)
                            if result:
                                logging.info("Add vuln {0} in port {1}".format(port_vuln[1], port_vuln[0]))
            else:   
                # if cannot scan vuln in port then clear all port_vuln in db
                if exits_vulns is not None:
                    for ev in exits_vulns:
                        result = _pv._remove_row(conn, ev)
                        if result: logging.info("Remove vuln {0} in port {1}".format(ev[1], ev[0]))
                        else: logging.info("Cannot remove vuln {0} in port {1}".format(ev[1], ev[0]))
        # update task status to 1: host scan and updated to db
    task = list(task)
    task[2] = 1
    result = _task._update_status(conn, task)
    if result:
        logging.info("Task {} completed".format(task[0]))


# download cve data file
# def downloadFile():
#     url = 'https://cve.mitre.org/data/downloads/allitems.csv'
#     file_name = "allitems.csv"
#     urllib.request.urlretrieve(url, file_name)


@app.route("/idcs/task/excute", methods = ['POST'])
def excute_task():
    if psutil.cpu_percent(1) < 70:
        # get list task does not excuted
        tasks = _task._get_list_new(conn)
        if tasks is None:
            logging.info("No task to excute")
        if not any(isinstance(el, list) for el in tasks):
            tasks = [tasks]
        with ThreadPoolExecutor(max_workers=5) as executor:
            future = {executor.submit(do_task, task) for task in tasks}

@app.route("/idcs/vuln/update", methods = ['POST'])
def check_update():
    time_now = datetime.now()
    date = time_now.date()
    # if date == 1:
        # update to db
    result = _vuln._update_vuln_from_file(conn, _config.location)

#
#  
# 
def run_schedule():
    
    while True:
        schedule.run_pending()
        time.sleep(1000)

if __name__ == '__main__':
    # do update cve on first day of month at 1 a.m
    schedule.every().day.at("01:00").do(check_update)
    # check exp date each 5 minutes
    schedule.every(5).minutes.do(_vc.expiry_code, conn, (datetime.now() - timedelta(hours=1)))
    schedule.every(15).minutes.do(excute_task)
    t = Thread(target=run_schedule)
    t.start()
    app.run()


