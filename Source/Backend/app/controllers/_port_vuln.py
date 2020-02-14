import database.mysql_excute as _sql

class _port_vuln:
    def __init__(self, port_id, cve_num, last_updated):
        super().__init__()
        self.port_id = port_id
        self.cve_num = cve_num
        self.last_updated =last_updated

# GET
# Check port has vuln by cve_num, return exits vuln
def _check_exits(conn, port_id, cve_num):
    sql_string = "SELECT * FROM port_vuln_tbl  WHERE port_id = %s AND cve_num = %s"
    result = _sql._get_an_item(conn, sql_string, tuple(port_id, cve_num))
    return result

def _get_by_port(conn, port_id):
    sql_string = "SELECT * FROM port_vuln_tbl  WHERE port_id = {0}"
    result = _sql._get_list_items(conn, sql_string, tuple([port_id]))
    return result


# ADD
def _add_to_db(conn, port_vuln):
    sql_string = "INSERT INTO port_vuln_tbl(port_id, cve_num, last_updated) \
            VALUES(%s,%s,%s)"
    result = _sql._excute_without_return(conn, sql_string, tuple(*port_vuln))
    return result

# UPDATE
def _update_by_id(conn, port_vuln):
    sql_string = "UPDATE port_vuln_tbl SET cve_num = %s, last_updated = %s WHERE port_id = %s"
    result = _sql._excute_without_return(conn, sql_string, tuple(*port_vuln))
    return result


