import requests
from urllib.parse import urlencode
from urllib.request import Request, urlopen


data = {"nmap": {"command_line": "nmap -oX - -sn --script whois-ip 141.101.220.172", "scaninfo": {}, "scanstats": {"timestr": "Fri Nov 22 15:55:54 2019", "elapsed": "3.98", "uphosts": "1", "downhosts": "0", "totalhosts": "1"}}, "scan": {"141.101.220.172": {"hostnames": [{"name": "141.101.220.172.leadertelecom.ru", "type": "PTR"}], "addresses": {"ipv4": "141.101.220.172"}, "vendor": {}, "status": {"state": "up", "reason": "syn-ack"}, "hostscript": [{"id": "whois-ip", "output": "Record found at whois.ripe.net\ninetnum: 141.101.220.0 - 141.101.220.255\nnetname: Transavtoliz-NET\ndescr: Transavtoliz LLC\ncountry: RU\nperson: Dmitriy Kuprin\nemail: it_bill@avtoline.ru"}]}}}

host_script = data['scan']['141.101.220.172']['hostscript'][0]['output']
print(host_script.split('\n')[2].split(':')[1])
