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
    sql_string = "SELECT * FROM host_tbl h WHERE h.ipv4 = '{0}'".format(host["ipv4"])
    data = _sql._get_an_item(conn, sql_string)
    return data


def add_host_to_db(conn, host):
    host_stamp = _get_by_ip(conn, host)
    if host_stamp is None:
            # sql_string = "INSERT INTO host_tbl(ipv4,ipv6,dns,net_name,country,oraganization,asn,last_updated)"
        sql_string = "INSERT INTO host_tbl(%s,%s,%s,%s,%s,%s,%s) \
            VALUE(%s,%s,%s,%s,%s,%s,%s)"
    result = _sql._excute_without_return(conn, sql_string, tuple(*host, *host.values()))
    return result


# update host
def _update_to_db(conn, host):
    sql_string = "UPDATE host_tbl SET ipv4 = %s, ipv6 = %s, dns = %s, net_name = %s, \
        country = %s, oraganization = %s, last_updated = %s"
    result = _sql._excute_without_return(conn, sql_string, tuple(*host.values()))
    return result


def _delete_from_db(conn, host):
    sql_string = "DELETE * FROM host_tbl WHERE ipv4 = %s"
    result = _sql._excute_without_return(conn, sql_string, tuple([host["ipv4"]]))
    return result



# 
# SCAN WITH NMAP
# 
def _scan_host(host):
    result = {}
    arguments='-sn --script whois-ip'     #-sn no port scan
    result = _nm._scan(host, arguments)
    if result['nmap']['scanstats']['uphosts'] == '0':
        return None      #Return F when hots down
    return result

        