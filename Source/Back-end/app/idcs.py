from flask import Flask, request, jsonify
from flaskext.mysql import MySQL
from werkzeug.security import check_password_hash, generate_password_hash
import json, random, string, smtplib
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
from string import Template
from datetime import datetime, timedelta
import nmap

app = Flask(__name__)
mysql = MySQL()

# MySQL configurations
app.config['MYSQL_DATABASE_USER'] = 'admin'
app.config['MYSQL_DATABASE_PASSWORD'] = '111111'
app.config['MYSQL_DATABASE_DB'] = 'idcs'
app.config['MYSQL_DATABASE_HOST'] = '54.199.156.1'
app.config['MYSQL_DATABASE_PORT'] = 3306
mysql.init_app(app)
conn = mysql.connect()
#SMTP configurations
MY_ADDRESS = 'noreply.idcs@gmail.comm'
PASSWORD = 'ldxlikrcrlxlifyn'
SMTP_HOST = 'smtp.gmail.com'
SMTP_PORT = 465
# 
# -----http directional------
# 

# verify user, return user when user_name and password matched
@app.route("/idcs/user/verify", methods=['POST'])
def verify_user():
    user_stamp = request.json
    user_name = next(iter(user_stamp))
    user = get_user_by_name(conn, user_name)
    if check_password_hash(user["password"], user_stamp[user_name]):
        return jsonify(user)
    return jsonify({"status":"failed"})

# update user info
@app.route("/idcs/user/update/info", methods=['POST'])
def update_information():
    user = request.json
    return return_change_status(update_user_info(conn, user))

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
    return return_change_status(add_user(conn, user))

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
    send_access_code_to_mail(data, code,exp_time)



# 
@app.route("/idcs/port/get", methods = ['POST'])
def get_port_from_db():
    port = request.json


# 
#
# 
#  
#get an user
def get_user_by_name(conn, user_name):
    with conn as cursor:   
        cursor.execute("SELECT * FROM user_tbl WHERE user_name = '{0}'".format(user_name))
        data = cursor.fetchone()
    if data is None:
        return None
    return user_list_to_dict(data)
    
# get list user
def get_list_user(conn):
    cursor = conn.cursor()
    cursor.execute("SELECT * FROM user_tbl")
    data = cursor.fetchall()
    cursor.close()
    return data

# return True when update completed, False when failed.
def update_user_info(conn, user):
    user_temp = get_user_by_name(conn, user[0])
    if user_temp is not None:
        try:
            cursor = conn.cursor()
            #genarate sql string
            sql_string = "UPDATE user_tbl u SET u.full_name = '{0}', u.gender = {1}, u.email = '{2}', "
            sql_string + "u.phone = '{3}', u.address = '{4}', u.credits = {5}, "
            sql_string + "u.user_type = {6} WHERE u.user_name = '{7}'"
            sql_string.format(user["fullName"], user["gender"], user["emailAddress"], user["phoneNumber"], user["address"], user["userName"])
            cursor.execute(sql_string)
            conn.commit()
        except mysql.connector.Error as error:
            print("Failed to update table record: {}".format(error))
            return False
    return True

def update_user_password(conn, user_name, password):
    user_temp = get_user_by_name(conn, user_name)
    if not user_temp:
        try:
            cursor = conn.cursor()
            sql_string = "UPDATE user_tbl u SET u.password = {0} WHERE u.user_name = {1}".format(password, user_name)
            cursor.execute(sql_string)
            conn.commit()
        except mysql.connector.Error as error:
            print("Failed to update table record: {}".format(error))
            return False
    return True
    
def add_user(conn, user):
    user_temp = get_user_by_name(conn, user["userName"])
    if user_temp is None:
        try:
            with conn as cursor:   
                sql_string = "INSERT INTO user_tbl(user_name, password, full_name, gender, email, \
                    phone, address, credits, user_type) \
                    VALUES('{0}','{1}','{2}',{3},'{4}','{5}','{6}',{7},{8})".format(*user_dict_to_list(user))
                cursor.execute(sql_string)
                conn.commit()
        except mysql.connector.Error as error:
            print("Failed to ADD table record: {}".format(error))
            return False
    return True


def get_host_from_db(conn, host):
    try:
        with conn as cursor:
            sql_string = "SELECT * FROM host_tbl h WHERE h.ipv4 = {0}".format(host["ipv4"])
            cursor.execute(sql_string)
            data = cursor.fetchone()
    except mysql.connector.Error as error:
        print("Failed to GET table record: {}".format(error))
        return None
    return data


def add_host_to_db(conn, host):
    host_stamp = get_host_from_db(conn, host)
    if host_stamp is None:
        try:
            cursor = conn.cursor()
            # sql_string = "INSERT INTO host_tbl(ipv4,ipv6,dns,net_name,country,oraganization,asn,last_updated)"
            sql_string = "INSERT INTO host_tbl({0},{1},{2},{3},{4},{5},{6},{7}) \
                VALUE({8},{9},{10},{11},{12},{13},{14},{15},{16})".format(*host, *host.values())
            cursor.execute(sql_string)
        except mysql.connector.Error as error:
            print("Failed to ADD table record: {}".format(error))
            return False
    return True
# update host
def update_host_to_db(conn, host):
    try:
        with conn as cursor:
            sql_string = "UPDATE host_tbl SET ipv4 = '{0}', ipv6 = '{1}', dns = '{2}', net_name = '{3}', \
                country = '{4}', oraganization = '{5}', asn = '{6}', last_updated = {7}".format(*host.values())
            cursor.execute(sql_string)
            conn.commit()
    except mysql.connector.Error as error:
        print("Failed to update table record: {}".format(error))
        return False
    return True

def delete_host_from_db(conn, host):
    try:
        with conn as cursor:
            sql_string = "DELETE * FROM host_tbl WHERE ipv4 = '{}'".format(host["ipv4"])
            cursor.execute(sql_string)
            conn.commit()
    except mysql.connector.Error as error:
        print("Failed to update table record: {}".format(error))
        return False
    return True

# 
# PORT DB QUERY
def get_port_by_id(conn, port):
    try:
        with conn as cursor:
            sql_string = "SELECT * FROM port_tbl p WHERE p.port_id = {0}".format(port["port_id"])
            cursor.execute(sql_string)
            data = cursor.fetchone()
    except mysql.connector.Error as error:
        print("Failed to update table record: {}".format(error))
        return None
    return data

def get_port_by_host(conn, host):
    try:
        with conn as cursor:
            sql_string = "SELECT * FROM port_tbl p WHERE p.host_ip = {0}".format(host["ipv4"])
            cursor.execute(sql_string)
            data = cursor.fetchall()
    except mysql.connector.Error as error:
        print("Failed to update table record: {}".format(error))
        return None
    return data




# convert object list to dict
def user_list_to_dict(data):
    return {"userName":data[0],"password":data[1],"fullName":data[2],"gender":data[3],"emailAddress":data[4], \
    "phoneNumber":data[5],"address":data[6],"credits":data[7],"userType":data[8]}

# convert user dict to list
def user_dict_to_list(data):
    return [data["userName"],data["password"],data["fullName"],data["gender"],data["emailAddress"],\
        data["phoneNumber"],data["address"],data["credits"],data["userType"]]

# genarate random code
def genarate_random_code(code_lenght):
    letters = string.ascii_letters
    return ''.join(random.choice(letters) for i in range(code_lenght))

def host_list_to_dict(data):
    return {"":}

# SMTP
# send new password to user mail
def send_access_code_to_mail(user, access_code, exp_date):
    message_template = read_template("/Users/mac/Desktop/CP/python/forgot_password_sample.html")
    message = message_template.substitute(full_name=user["full_name"], access_code=access_code, exp_date=exp_date)
    msg = MIMEMultipart()
    msg['From'] = MY_ADDRESS
    msg['To'] = user["email"]
    msg['Subject']= "!!Change password validation code - IDCS"
    msg.attach(MIMEText(message, 'html'))
    # init smpt service
    mail = smtplib.SMTP(SMTP_HOST, SMTP_PORT)
    mail.ehlo()
    mail.starttls()
    mail.login(MY_ADDRESS, PASSWORD)
    mail.send_message(msg)
    del msg
    mail.quit()

# read template from file
def read_template(filename):
    with open(filename, 'r', encoding='utf-8') as template_file:
        template_file_content = template_file.read()
    return Template(template_file_content)

# return value with dict 'status':'<status_task>'
def return_change_status(b):
    if b:
        return jsonify({"status":"completed"})
    else: 
        return jsonify({"status":"failed"})

# 
# NMAP SCAN
# 
def nmap_scan_host_by_ip(ip):
    return nm.scan(hosts=ip, arguments='-sn --script whois-ip')     #-sn no port scan

#Checking port open
def is_open(ip,port):
	s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
	try:
		s.settimeout(0.5)
		s.connect((ip, int(port)))
		s.shutdown(2)
		return port
	except:
		return None

#Get ports open in range(port_min, port_max)

def get_open_ports(ip, port_min, port_max):
	ports = []
	with ThreadPoolExecutor(max_workers=100) as executor:
		future = {executor.submit(is_open,target,p)
					for p in range(port_min,port_max + 1)}
		for f in as_completed(future):
			try:
				result = f.result()
				if result is not None:
					ports.append(result)
					continue
			except:
				pass
	return ports    #RETURN list of port open on range

def nmap_scan_ports(ip, port_min, port_max):
    ports = get_open_ports(ip, port_min, port_max)
	ports_list = ','.join(str(port) for port in ports)
	data = nm.scan(hosts=target, arguments='-sV -p ' + ports_list)       #-sV scan service
	return data

# 
# NMAP DATA EXTRACT
# 
def host_has_up(host_result_dict):
    if host_result_dict['nmap']['scanstats']['uphosts'] == '0':
        return False
    else:
        return True

def nmap_result_to_host(host_result_dict):
    ipv4 = *host_result_dict['scan']
    ipv6 = ipv6_check_from_(ipv4)
    dns = host_result_dict['scan'][ipv4]['hostnames'][0]['name']
    host_script = host_result_dict['scan'][ipv4]['hostscript'][0]['output']
    tmp_list = host_script.split('\n')
    net_name = tmp_list[2].split(':')[1]
    country = tmp_list[4].split(':')[1]
    oraganization = tmp_list[3].split(':')[1]
    asn = ''
    last_updated = datetime.now()
    host = {"ipv4":}

def ipv6_check_from_(ipv4):
    return socket.inet_pton(socket.AF_INET6, ipv4)

#
#  
# 

if __name__ == '__main__':
    app.run()

# 
# 
# 
class User:
    def __init__(self, user_name, password, full_name, gender, email, phone, address, credit, user_type):
        super().__init__()
        self.user_name = user_name
        self.password = password
        self.full_name = full_name
        self.gender = gender
        self.email = email
        self.phone = phone
        self.address = address
        self.credits = credit
        self.user_type = user_type