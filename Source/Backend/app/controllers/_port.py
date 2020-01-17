import database.mysql_excute as _sql

class _port:
    def __init__(self, port_id, port_num, ipv4, service_name, version, cpe, status):
        super().__init__()
        self.port_id = port_id
        self.port_num = port_num
        self.ipv4 = ipv4
        self.service_name = service_name
        self.version = version
        self.cpe = cpe
        self.status = status

# 
# PORT DB QUERY

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
    sql_string = "SELECT * FROM port_tbl p WHERE p.host_ip = {0}".format(host["ipv4"])
    list_port = _sql._get_list_items(conn, sql_string)
    return list_port
    

