import nmap
import socket
import database.mysql_excute as _sql

# 
# NMAP SCAN
# 
nm = nmap.PortScanner()

def _scan(hosts, arguments):
	return nm.scan(hosts = hosts, arguments = arguments)
