import requests
from urllib.parse import urlencode
from urllib.request import Request, urlopen



headers = {'Content-type': 'application/json', 'Accept': 'application/json'}


# host = {"nmap": {"command_line": "nmap -oX - -sn --script whois-ip 141.101.220.172", "scaninfo": {}, "scanstats": {"timestr": "Fri Nov 22 15:55:54 2019", "elapsed": "3.98", "uphosts": "1", "downhosts": "0", "totalhosts": "1"}}, "scan": {"141.101.220.172": {"hostnames": [{"name": "141.101.220.172.leadertelecom.ru", "type": "PTR"}], "addresses": {"ipv4": "141.101.220.172"}, "vendor": {}, "status": {"state": "up", "reason": "syn-ack"}, "hostscript": [{"id": "whois-ip", "output": "Record found at whois.ripe.net\ninetnum: 141.101.220.0 - 141.101.220.255\nnetname: Transavtoliz-NET\ndescr: Transavtoliz LLC\ncountry: RU\nperson: Dmitriy Kuprin\nemail: it_bill@avtoline.ru"}]}}}
# print(*host["scan"])
# host_script = host['scan']['141.101.220.172']['hostscript'][0]['output']
# print(host_script.split('\n')[2].split(':')[1])

# tmp = {"userName":"minhpv","password":"minhpv","fullName":"Pham Van Minh","gender":1,"emailAddress":"minhpv@email.com", \
#     "phoneNumber":"0987654321","address":"HaNoi, Viet Nam","credits":1,"userType":1}

  
# 
# TEST ADD USERs
# 
def test_user_add():
    URL1 = "http://127.0.0.1:5000/idcs/user/add"
    minhpv = {"userName":"minhpv","password":"minhpv","fullName":"Pham Van Minh","gender":1,"emailAddress":"minhpv@email.com", \
        "phoneNumber":"0987654321","address":"HaNoi, Viet Nam","credits":1,"userType":1}
    donnq = {"userName":"donnq","password":"donnq","fullName":"Nguyen Quy Don","gender":1,"emailAddress":"donnq@email.com", \
        "phoneNumber":"0987654321","address":"HaNoi, Viet Nam","credits":1,"userType":0}
    r = requests.post(URL1, json = minhpv, headers = headers)
    dat = r.json() 
    print(dat)
    r = requests.post(URL1, json = donnq, headers = headers)
    dat = r.json() 
    print(dat)

# 
# TEST UPDATE USER
# 
def test_user_update():
    URL2 = "http://127.0.0.1:5000/idcs/user/update/info"
    donnq_updated = {"userName":"donnq","password":"donnq","fullName":"Nguyen Quy Don","gender":1,"emailAddress":"hotro.dodo@gmail.com", \
        "phoneNumber":"0358008828","address":"HaNoi, Viet Nam","credits":1,"userType":0}
    r = requests.post(URL2, json = donnq_updated, headers = headers)
    dat = r.json() 
    print(dat)

def test_user_verify():
    URL = "http://127.0.0.1:5000/idcs/user/verify"
    user = {"donnq":"donnq"}
    r = requests.post(URL, json = user, headers = headers)
    dat = r.json() 
    print(dat)

def test_user_resgiter(user_name):
    URL = "http://127.0.0.1:5000/idcs/user/send-email"
    data = {"userName":user_name,"password":user_name,"fullName":"Mr. Sample","gender":1,"emailAddress":"hotro.dodo@gmail.com", \
        "phoneNumber":"0358008828","address":"HaNoi, Viet Nam","credits":1,"userType":4}
    r = requests.post(URL, json = data, headers = headers)
    dat = r.json() 
    print(dat)

# TEST MAIL
def test_mail_send(user_name, email):
    URL = "http://127.0.0.1:5000/idcs/user/send-email"
    data = {"userName":user_name,"password":user_name,"fullName":"Mr. Sample","gender":1,"emailAddress":email, \
        "phoneNumber":"0358008828","address":"HaNoi, Viet Nam","credits":1,"userType":0}
    r = requests.post(URL, json = data, headers = headers)
    # extracting data in json format 
    dat = r.json() 
    print(dat)

def test_mail_verify(user_name, email, code):
    URL = "http://127.0.0.1:5000/idcs/email/verify"
    data = {"userName":user_name,"emailAddress":email,"verify_code":code,"credits":1,"userType":6}
    r = requests.post(URL, json = data, headers = headers)
    # extracting data in json format 
    dat = r.json() 
    print(dat)



test_user_add()