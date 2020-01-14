import requests
from urllib.parse import urlencode
from urllib.request import Request, urlopen

URL = "http://127.0.0.1:5000/idcs/user/verify"

host = {"nmap": {"command_line": "nmap -oX - -sn --script whois-ip 141.101.220.172", "scaninfo": {}, "scanstats": {"timestr": "Fri Nov 22 15:55:54 2019", "elapsed": "3.98", "uphosts": "1", "downhosts": "0", "totalhosts": "1"}}, "scan": {"141.101.220.172": {"hostnames": [{"name": "141.101.220.172.leadertelecom.ru", "type": "PTR"}], "addresses": {"ipv4": "141.101.220.172"}, "vendor": {}, "status": {"state": "up", "reason": "syn-ack"}, "hostscript": [{"id": "whois-ip", "output": "Record found at whois.ripe.net\ninetnum: 141.101.220.0 - 141.101.220.255\nnetname: Transavtoliz-NET\ndescr: Transavtoliz LLC\ncountry: RU\nperson: Dmitriy Kuprin\nemail: it_bill@avtoline.ru"}]}}}
print(*host["scan"])
host_script = host['scan']['141.101.220.172']['hostscript'][0]['output']
print(host_script.split('\n')[2].split(':')[1])

tmp = {"userName":"tuannq","password":"tuannq","fullName":"Nguyen Quy Tuan","gender":1,"emailAddress":"minhpv@email.com", \
    "phoneNumber":"0987654321","address":"HaNoi, Viet Nam","credits":1,"userType":1}
user = {"donnq":"donnq"}
r = requests.post(URL, data=tmp)
  
# extracting data in json format 
data = r.json() 
  
print(data)
