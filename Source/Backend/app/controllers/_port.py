from concurrent.futures import ThreadPoolExecutor, as_completed
import socket
import database.mysql_excute as _sql
import nmap_tools._nmap as _nm
import nmap_tools._extract_data as _ed

class _port:
    def __init__(self, port_id, port_num, host_ip, service_name, version, status, cpe):
        super().__init__()
        self.port_id = port_id
        self.port_num = port_num
        self.host_ip = host_ip
        self.service_name = service_name
        self.version = version
        self.status = status
        self.cpe = cpe

# 
# PORT DB QUERY ON DB

# 
# GET PORT BY ID
# INPUT: port {"port_id":"<port_id>"}
def _get_by_id(conn, port):
    sql_string = "SELECT * FROM port_tbl p WHERE p.port_id = %s"
    data = _sql._get_an_item(conn, sql_string, tuple([port["port_id"]]))
    return data

# check port has exits on database
# return port if has exits / None if not exits
def _check_exits_on_db(conn, host_ip, port_num):
    sql_string = "SELECT * FROM port_tbl p WHERE p.port_num = %s AND p.host_ip = %s"
    data = _sql._get_an_item(conn, sql_string, tuple([port_num,host_ip]))
    return data


#
# GET ALL PORTS ON HOST BY IPv4
# INPUT: host {"ipv4":"<host_ip>"}
def _get_by_host(conn, host):
    sql_string = "SELECT * FROM port_tbl p WHERE p.host_ip = %s"
    list_port = _sql._get_list_items(conn, sql_string, tuple([host]))
    return list_port


def _add_to_db(conn, port):
    sql_string = "INSERT INTO port_tbl(port_num, host_ip, service_name, version, status, cpe) \
            VALUES(%s,%s,%s,%s,%s,%s)"
    result = _sql._excute_without_return(conn, sql_string, tuple([*port.values()]))
    return result


def _update_by_id(conn, port):
    sql_string = "UPDATE port_tbl SET port_num = %s, host_ip = %s, service_name = %s, \
        version = %s, status = %s, cpe = %s WHERE port_id = %s"
    result = _sql._excute_without_return(conn, sql_string, tuple([*port.values()]))
    return result

# 
# SCAN WITH NMAP
# 

#Checking port open
def _is_open(ip,port):
	s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
	try:
		s.settimeout(0.5)
		s.connect((ip, int(port)))
		s.shutdown(2)
		return port
	except:
		return None

#Get ports open in range(port_min, port_max)
def _get_open_ports(ip, port_min, port_max):
	ports = []
	with ThreadPoolExecutor(max_workers=100) as executor:
		future = {executor.submit(_is_open,ip,p)
					for p in range(port_min,port_max + 1)}
		for f in as_completed(future):
			try:
				result = f.result()
				if result is not None:
					ports.append(result)
					continue
			except:
				pass
	return ports    #RETURN list of port open on range


# scan port with nmap
def _nmap_scan(ip, port_min = 0, port_max = 4000):
    ports = _get_open_ports(ip, port_min, port_max)
    ports_list = ','.join(str(port) for port in ports)
    arguments='-sV -p ' + ports_list       #-sV scan service
    result = _nm._scan(ip, arguments)
    return result

# store new scan to db
def _store_in_db(conn, port):
    result = None
    tmp = _check_exits_on_db(port["host_ip"], port["port_num"])
    if tmp:
        port = [tmp[0], port["port_num"], port["host_ip"], port["service_name"], port["version"], port["status"], port["cpe"]]
        result = _update_by_id(conn, port)
    else:
        port = [port["port_num"], port["host_ip"], port["service_name"], port["version"], port["status"], port["cpe"]]
        result = _add_to_db(conn, port)
    return result  #result = True/False
