from flask import Flask, request, jsonify
from flaskext.mysql import MySQL
from werkzeug.security import check_password_hash, generate_password_hash
import json, random, string
from datetime import datetime, timedelta
import mail.mail_services as mail
from nmap_tools import nmap_data_processing, nmap_services
import controllers._user as _user
import controllers._host as _host
import controllers._task as _task


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
    user = _user._get_by_name(conn, user_name)
    if check_password_hash(user["password"], user_stamp[user_name]):
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

@app.route("/idcs/host/get", methods = ['POST'])
def get_host_from_db():
    # get smt
    host_stamp = request.json
    host = _host._get_by_ip(conn, host_stamp)
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
    return host

@app.route("/idcs/task/get", methods = ['POST'])
def get_task_from_db():
    task_stamp = request.json
    task = _task._get_by_user_name()
    # !!!Missing convert list to dict
    if task:
        return task
    return return_change_status(False)

@app.route("/idcs/port/get", methods = ['POST'])
def get_port_from_db():
    # get smt
    host_stamp = request.json
    host = _user._get_by_ip(conn, host_stamp["ipv4"])
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