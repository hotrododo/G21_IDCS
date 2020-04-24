import nmap

# 
# NMAP SCAN
# 
nm = nmap.PortScanner()

def _scan(hosts, arguments):
	result = nm.scan(hosts = hosts, arguments = arguments)
	return result

