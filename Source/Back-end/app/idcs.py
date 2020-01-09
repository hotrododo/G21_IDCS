from flask import Flask, request, jsonify
from flaskext.mysql import MySQL
from werkzeug.security import check_password_hash, generate_password_hash
import json, random, string, smtplib
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
from string import Template
from datetime import datetime, timedelta

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
MY_ADDRESS = 'support@cstcopyright.comm'
PASSWORD = 'idcs@cst'
SMTP_HOST = 'smtp.gmail.com'
SMTP_PORT = 25
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


@app.route("/idcs/user/update/info", methods=['POST'])
def update_information():
    user = request.json
    if update_user_info(conn, user):
        return jsonify({"status":"completed"})
    else:
        return jsonify({"status":"failed"})

@app.route("/idcs/user/update/password", methods = ['POST'])
def update_password():
    user = request.json
    user["password"] = generate_password_hash(user["password"])
    if update_user_password(conn, user["userName"], user["password"]):
        return jsonify({"status":"completed"})
    else:
        return jsonify({"status":"failed"})

@app.route("/idcs/user/add", methods = ['POST'])
def add_an_user():
    user = request.json
    user["password"] = generate_password_hash(user["password"])
    return add_user(conn, user)

# 
# @app.route("/idcs/user/forgot", methods = ['POST'])
# def forgot_password():
#     user_stamp = request.json
#     user = get_user_by_name(conn, user_stamp["userName"])
#     if user_stamp["email"] == user["email"] and user_stamp["code"] == user["phone"]:
#         return True

# 
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
    cursor = conn.cursor()
    cursor.execute("SELECT * FROM user_tbl WHERE user_name = '{0}'".format(user_name))
    data = cursor.fetchone()
    cursor.close()
    user = {"userName":data[0],"password":data[1],"fullName":data[2],"gender":data[3],"emailAddress":data[4], \
    "phoneNumber":data[5],"address":data[6],"credits":data[7],"userType":data[8]}
    return user
    

# return True when update completed, False when failed.
def update_user_info(conn, user):
    user_temp = get_user_by_name(conn, user[0])
    if user_temp is not None:
        cursor = conn.cursor()
        #genarate sql string
        sql_string = "UPDATE user_tbl u SET u.full_name = '{0}', u.gender = {1}, u.email = '{2}', "
        sql_string + "u.phone = '{3}', u.address = '{4}', u.credits = {5}, "
        sql_string + "u.user_type = {6} WHERE u.user_name = '{7}'"
        sql_string.format(user["fullName"], user["gender"], user["emailAddress"], user["phoneNumber"], user["address"], user["userName"])
        cursor.execute(sql_string)
        conn.commit()
        if cursor.rowcount() == 1:
            return True
        cursor.close()
    return False

def update_user_password(conn, user_name, password):
    user_temp = get_user_by_name(conn, user_name)
    if not user_temp:
        cursor = conn.cursor()
        sql_string = "UPDATE user_tbl u SET u.password = {0} WHERE u.user_name = {1}".format(password, user_name)
        cursor.execute(sql_string)
        conn.commit()
        if cursor.rowcount() == 1:
            return True
        cursor.close()
    return False
    
def add_user(conn, user):
    user_temp = get_user_by_name(user["userName"])
    if user_temp is None:
        data = ",".join(list(user.values()))
        cursor = conn.cursor()
        sql_string = "INSERT INTO user_tbl(user_name, password, full_name, gender, email, phone_number, address, user_type, used_count, exp_date, status) "
        sql_string + "VALUES({0})".format(data)
        cursor.execute(sql_string)
        cursor.commit()
        if cursor.rowcount() == 1:
            return True
        cursor.close()
    return False


def get_host_from_db(conn, host):
    return


def add_host_to_db(conn, host):
    host_stamp = get_host_from_db(conn, host)
    if host_stamp is None:
        cursor = conn.cursor()
        sql_string = "INSERT INTO host_tbl(ipv4,ipv6,dns,net_name,country,oraganization,asn,last_updated)"
        sql_string + "VALUE({})"
    return


# genarate random code
def genarate_random_code(code_lenght):
    letters = string.ascii_letters
    return ''.join(random.choice(letters) for i in range(code_lenght))



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

def read_template(filename):
    with open(filename, 'r', encoding='utf-8') as template_file:
        template_file_content = template_file.read()
    return Template(template_file_content)

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