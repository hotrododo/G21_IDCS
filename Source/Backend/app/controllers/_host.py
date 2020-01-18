import database.mysql_excute as _sql
import nmap_tools._nmap as _nm
import nmap_tools._extract_data as _ed

class _host:
    def __init__(self, ipv4, ipv6, dns, net_name, country, oraganization, last_updated):
        super().__init__()
        self.ipv4 = ipv4
        self.ipv6 = ipv6
        self.dns = dns
        self.net_name = net_name
        self.country = country
        self.oraganization = oraganization
        self.last_updated = last_updated
    

# 
# HOST QUERY DATABASE
# 
# 

def _get_by_ip(conn, host):
    sql_string = "SELECT * FROM host_tbl h WHERE h.ipv4 = {0}".format(host["ipv4"])
    data = _sql._get_an_item(conn, sql_string)
    return data


def add_host_to_db(conn, host):
    host_stamp = get_host_from_db(conn, host)
    if host_stamp is None:
            # sql_string = "INSERT INTO host_tbl(ipv4,ipv6,dns,net_name,country,oraganization,asn,last_updated)"
        sql_string = "INSERT INTO host_tbl({0},{1},{2},{3},{4},{5},{6}) \
            VALUE('{7}','{8}','{9}','{10}','{11}','{12}','{13}')".format(*host, *host.values())
    result = _sql._excute_without_return(conn, sql_string)
    return result


# update host
def _update_to_db(conn, host):
    sql_string = "UPDATE host_tbl SET ipv4 = '{0}', ipv6 = '{1}', dns = '{2}', net_name = '{3}', \
        country = '{4}', oraganization = '{5}', asn = '{6}', last_updated = {7}".format(*host.values())
    result = _sql._excute_without_return(conn, sql_string)
    return result


def _delete_from_db(conn, host):
    sql_string = "DELETE * FROM host_tbl WHERE ipv4 = '{}'".format(host["ipv4"])
    result = _sql._excute_without_return(conn, sql_string)
    return result



# 
# SCAN WITH NMAP
# 
def _scan_host(host):
    data = {}
	hosts = host["ipv4"]
    arguments='-sn --script whois-ip')     #-sn no port scan
    result = nm._scan(hosts, arguments)
	if result['nmap']['scanstats']['uphosts'] == '0':
        return False      #Return F when hots down
	data = _ed._result_to_host(result)
	return data
        