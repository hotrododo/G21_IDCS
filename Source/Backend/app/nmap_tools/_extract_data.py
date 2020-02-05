import socket
from datetime import datetime
# 
# NMAP DATA EXTRACT
# 
def host_has_up(result):
    if result['nmap']['scanstats']['uphosts'] == '0':
        return False
    else:
        return True

def _result_to_host(result):
    ipv4 = str(*result["scan"])
    ipv6 = ipv6_check_from_(ipv4)
    dns = result['scan'][ipv4]['hostnames'][0]['name']
    host_script = result['scan'][ipv4]['hostscript'][0]['output']
    tmp_list = host_script.split('\n')
    net_name = tmp_list[2].split(':')[1]
    country = tmp_list[4].split(':')[1]
    oraganization = tmp_list[3].split(':')[1]
    last_updated = datetime.now()
    host = {"ipv4":ipv4,"ipv6":ipv6,"dns":dns,"net_name":net_name,"country":country,"oraganization":oraganization,"last_updated":last_updated}
    return host

def ipv6_check_from_(ipv4):
    return socket.getaddrinfo(ipv4, 22, socket.AF_INET6)[0][4][0]


def _result_to_ports(result):
    ports = {}
    ip = next(iter(result["scan"]))
    list_port = result["scan"][ip]["tcp"].keys()
    for port_num in list_port:
        service_name = result["scan"][ip]["tcp"][port_num]["name"]
        version = result["scan"][ip]["tcp"][port_num]["version"]
        status = result["scan"][ip]["tcp"][port_num]["state"]
        cpe = result["scan"][ip]["tcp"][port_num]["cpe"]
        port = {"port_num":port_num, "host_ip":ip, "service_name":service_name, "version":version, "status":status, "cpe":cpe}
        ports[port_num] = port
    return ports

