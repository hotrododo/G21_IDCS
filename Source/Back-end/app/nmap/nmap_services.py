import nmap
import socket
# 
# NMAP SCAN
# 
def nmap_scan_host_by_ip(ip):
    return nm.scan(hosts=ip, arguments='-sn --script whois-ip')     #-sn no port scan

#Checking port open
def is_open(ip,port):
	s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
	try:
		s.settimeout(0.5)
		s.connect((ip, int(port)))
		s.shutdown(2)
		return port
	except:
		return None

#Get ports open in range(port_min, port_max)

def get_open_ports(ip, port_min, port_max):
	ports = []
	with ThreadPoolExecutor(max_workers=100) as executor:
		future = {executor.submit(is_open,target,p)
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

def nmap_scan_ports(ip, port_min, port_max):
    ports = get_open_ports(ip, port_min, port_max)
	ports_list = ','.join(str(port) for port in ports)
	data = nm.scan(hosts=target, arguments='-sV -p ' + ports_list)       #-sV scan service
	return data
