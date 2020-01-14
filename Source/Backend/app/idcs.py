from flask import Flask, request, jsonify
from flaskext.mysql import MySQL
from werkzeug.security import check_password_hash, generate_password_hash
import json, random, string
from datetime import datetime, timedelta
import database.mysql_utils as msql
import mail.mail_services as mail
from nmap_tools import nmap_data_processing, nmap_services


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

# 
# -----http directional------
# 

# verify user, return user when user_name and password matched
@app.route("/idcs/user/verify", methods=['POST'])
def verify_user():
    user_stamp = request.json
    user_name = next(iter(user_stamp))
    user = msql.get_user_by_name(conn, user_name)
    if check_password_hash(user["password"], user_stamp[user_name]):
        return jsonify(user)
    return jsonify({"status":"failed"})

# update user info
@app.route("/idcs/user/update/info", methods=['POST'])
def update_information():
    user = request.json
    return return_change_status(msql.update_user_info(conn, user))

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
    return return_change_status(msql.add_user(conn, user))

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
    user["credits"] = 0
    user["userType"] = 3
    return return_change_status(msql.add_user(conn, user))


@app.route("/idcs/email/verify", methods = ['POST'])
def email_verify():
    user = request.json
    verify = msql.get_verify_code(conn, user)
    if verify is not None:
        if user["verify_code"] == verify[2]:
            # update user type to #2 (FREE Account)
            user["userType"] = 2
            #  out of date verify code
            return return_change_status(msql.update_user_type(conn, user) and msql.delete_verify_code(conn, user["userName"]))
    return return_change_status(False)

@app.route("/idcs/host/get", methods = ['POST'])
def get_host_from_db():
    # get smt
    host_stamp = request.json
    host = msql.get_host_by_ip(conn, host_stamp)
    if not host:
        return return_change_status(False)
    return host

@app.route("/idcs/port/get", methods = ['POST'])
def get_port_from_db():
    # get smt
    host_stamp = request.json
    host = msql.get_host_by_ip(conn, host_stamp["ipv4"])
    if not host:
        return return_change_status(False)
    return host




# genarate random code
def genarate_random_code(code_lenght):
    letters = string.ascii_letters
    return ''.join(random.choice(letters) for i in range(code_lenght))

def host_list_to_dict(data):
    return None



# return value with dict 'status':'<status_task>'
def return_change_status(b):
    if b:
        return jsonify({"status":"completed"})
    else: 
        return jsonify({"status":"failed"})




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