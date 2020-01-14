
# 
# NMAP DATA EXTRACT
# 
def host_has_up(host_result_dict):
    if host_result_dict['nmap']['scanstats']['uphosts'] == '0':
        return False
    else:
        return True

def nmap_result_to_host(host_result_dict):
    ipv4 = str(*host_result_dict["scan"])
    ipv6 = ipv6_check_from_(ipv4)
    dns = host_result_dict['scan'][ipv4]['hostnames'][0]['name']
    host_script = host_result_dict['scan'][ipv4]['hostscript'][0]['output']
    tmp_list = host_script.split('\n')
    net_name = tmp_list[2].split(':')[1]
    country = tmp_list[4].split(':')[1]
    oraganization = tmp_list[3].split(':')[1]
    asn = ''
    last_updated = datetime.now()
    host = {"ipv4":ipv4,"ipv6":ipv6,"dns":dns,"net_name":net_name,"country":country,"oraganization":oraganization,"asn":asn,"last_updated":last_updated}
    return host

def ipv6_check_from_(ipv4):
    return socket.inet_pton(socket.AF_INET6, ipv4)
