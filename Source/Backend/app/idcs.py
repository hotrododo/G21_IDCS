from flask import Flask, request, jsonify
from concurrent.futures import ThreadPoolExecutor, as_completed
from flaskext.mysql import MySQL
from werkzeug.security import check_password_hash, generate_password_hash
from datetime import datetime, timedelta
import json, random, string
import mail.mail_services as mail
import controllers._user as _user
import controllers._host as _host
import controllers._task as _task
import controllers._port as _port
import os.path
import controllers.vuln_scanner as _vs
import controllers._port_vult as _pv
import controllers._convert as _cv
import controllers._vult as _vult


app = Flask(__name__)
mysql = MySQL()
#Read config file
my_path = os.path.abspath(os.path.dirname(__file__))
path = os.path.join(my_path, "app.conf")
with open(path) as json_data_file:
    _config = json.load(json_data_file)



# MySQL configurations
app.config['MYSQL_DATABASE_USER'] = _config["mysql"]["MYSQL_DATABASE_USER"]
app.config['MYSQL_DATABASE_PASSWORD'] = _config["mysql"]["MYSQL_DATABASE_PASSWORD"]
app.config['MYSQL_DATABASE_DB'] = _config["mysql"]["MYSQL_DATABASE_DB"]
app.config['MYSQL_DATABASE_HOST'] = _config["mysql"]["MYSQL_DATABASE_HOST"]
app.config['MYSQL_DATABASE_PORT'] = _config["mysql"]["MYSQL_DATABASE_PORT"]
mysql.init_app(app)
conn = mysql.connect()

# 
# -----http directional------
# 

# verify user, return user when user_name and password matched
@app.route("/idcs/user/verify", methods=['POST'])
def verify_user():
    user_stamp = request.json
    user_name = user_stamp["userName"]
    # user = _user._get_by_name(conn, user_name)
    if check_password_hash(user_stamp["password"], user_stamp[user_name]):
        return jsonify(user)
    return jsonify({"status":"failed"})

# update user info
@app.route("/idcs/user/update/info", methods=['POST'])
def update_information():
    user = request.json
    return return_change_status(_user._update_info(conn, user))

# update user password
@app.route("/idcs/user/update/password", methods = ['POST'])
def update_password():
    user = request.json
    user["password"] = generate_password_hash(user["password"])
    return return_change_status(update_user_password(conn, user["userName"], user["password"]))

# add an user to db
@app.route("/idcs/user/add", methods = ['POST'])
def add_an_user():
    user = request.json
    user["password"] = generate_password_hash(user["password"])
    return return_change_status(_user._add(conn, user))

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
    code = genarate_random_code(6)
    exp_time = datetime.now() + timedelta(hours=1)
    return return_change_status(mail.send_access_code_to_mail(data, code,exp_time))


# 
@app.route("/idcs/user/register", methods = ['POST'])
def user_register():
    user = request.json
    user["password"] = generate_password_hash(user["password"])
    user["credits"] = 1
    # userType = 3 mean account have been created.
    user["userType"] = 3
    return return_change_status(_user._add(conn, user))


@app.route("/idcs/email/verify", methods = ['POST'])
def email_verify():
    user = request.json
    verify = _user._get_verify_code(conn, user)
    if verify is not None:
        if user["verify_code"] == verify[2]:
            # update user type to #2 (FREE Account)
            user["userType"] = 2
            stt = _user._update_user_type(conn, user)
            if stt:
                #  out of date verify code
                _user._delete_verify_code(conn, user["userName"])
                return return_change_status(stt)
    return return_change_status(False)

# 
# HOST
# 

# input host= {"ipv4"=ipv4}
@app.route("/idcs/host/get", methods = ['POST'])
def get_host_from_db():
    # get smt
    host_stamp = request.json
    host_db = _host._get_by_ip(conn, host_stamp)
    # if host haven't in db or host no longer update in 30 days
    if not host or datetime(host["time_stamp"]) + timedelta(days = 30) < datetime.now:
        # check task has exits
        if not _task._get_from_db(conn, data):
            # create a task scan
            data["status"] = 0      #set status 0 mean
            data["time_stamp"] = datetime.now()     #set time stamp
            # add new task to db
            if _task._add_to_db(conn, data):   
                return jsonify({"status":"task created"})
        return jsonify({"status":"task processing"})
    host = _cv.host_to_dict(host_db)
    ports_db = _port._get_by_host(host)
    if ports_db is not None:
        ports = _cv.ports_to_dict(ports_db)
        for port in ports:
            vults = _pv._get_by_id(conn, port["port_id"])
            if vults is not None:
                list_vult = {}
                for v in vults:
                    vult = _vult.get_by_num(conn, v[1])
                    if vult is not None:
                        list_vult[v[1]] = {"cve_num":v[1], "cve_desc":vult[1]}
            port["vult"] = list_vult
    host["ports"] = ports
    return host


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
    s_host = _host._scan_host(task[1])
    if s_host is None or s_host["nmap"]["scanstats"]["uphosts"] == 0:
        return {"status":"host down or not public"}
    # extract host from nmap scan to dict
    host = _ed._result_to_host(s_host)
    # check host in db
    host_stamp = _host._get_by_ip(conn, host)
    if host_stamp is None:
        # add new host scan to db
        if _host.add_host_to_db(conn, host):
            print("host added!")
        else:
            print("cannot add host")
    else:
        # update new host scan to db
        if _host._update_to_db(conn, host):
            print("host updated!")
        else:
            print("cannot update host")
    # scan port with nmap
    s_ports = _port._nmap_scan(task[1])
    if s_ports is None or s_ports["scan"][task[1]]["tcp"] is None:
        return {"status":"no port open"}
    ports = _ed._result_to_ports(s_ports)
    for port in ports:
        port_tmp = _port._check_exits_on_db(port)
        result = None
        if port_tmp is not None:
            # set port_id
            port["port_id"] = port_tmp[0]
            result = _port._update_by_id(conn, port)
        else:
            result = _port._add_to_db(conn, port)
        if result:
            print("updated port: {} of {}".format(port["port_num"], port["host_ip"]))
        # get port in db
        port = _port._check_exits_on_db(port)
        if port["service_name"] == "ms-wbt-server":
            vults = _vs.scan(port["host_ip"],port["port_num"],"windows","rdp")
            if vults is not None:
                for vult in vults:
                    port_vult = [port["port_id"], vult, last_updated]
                    if _pv._get_by_id(conn, port["port_id"]) is not None:
                        result = _pv._update_by_id(conn, port_vult)
                    else:
                        result = _pw._add_to_db(conn, port_vult)
                    if result:
                        print("update completed")






def excute_task():
    # get list task does not excuted
    tasks = _task._get_list_new(conn)
    if tasks is None:
        return {"status":"no task to scan"}
    with ThreadPoolExecutor(max_workers=5) as executor:
        future = {executor.submit(do_task, task) for task in tasks}


def check_update():
    
    result = _vult._update_vult_from_file(conn, _config["cve_file"])
#
#  
# 

if __name__ == '__main__':
    app.run()
    excute_task()

