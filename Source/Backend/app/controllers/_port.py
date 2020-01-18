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
    sql_string = "SELECT * FROM port_tbl p WHERE p.port_id = {0}".format(port["port_id"])
    data = _sql._get_an_item(conn, sql_string)
    return data

#
# GET ALL PORTS ON HOST BY IPv4
# INPUT: host {"ipv4":"<host_ip>"}
def _get_by_host(conn, host):
    sql_string = "SELECT * FROM port_tbl p WHERE p.host_ip = '{0}'".format(host["host_ip"])
    list_port = _sql._get_list_items(conn, sql_string)
    return list_port


def _add_to_db(conn, port):
    sql_string = "INSERT INTO port_tbl(port_num, host_ip, service_name, version, status, cpe) \
            VALUES({0},'{1}','{2}','{3}',{4},'{5}')".format(*port)
    result = _sql._excute_without_return(conn, sql_string)
    return result


def _update_by_id(conn, port):
    sql_string = "UPDATE port_tbl SET port_num = {1}, host_ip = '{2}', service_name = '{3}', \
        version = '{4}', status = {5}, cpe = '{6}' WHERE port_id = {0}"
    sql_string.format(*port.values())
    result = _sql._excute_without_return(conn, sql_string)
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
	with ThreadPoolExecutor(max_workers=50) as executor:
		future = {executor.submit(_is_open,target,p)
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



def _nmap_scan(ip, port_min = 0, port_max = 2000):
	ports = _get_open_ports(ip, port_min, port_max)
	ports_list = ','.join(str(port) for port in ports)
	arguments='-sV -p ' + ports_list       #-sV scan service
    result = _nm._scan(ip, arguments)
	return data

