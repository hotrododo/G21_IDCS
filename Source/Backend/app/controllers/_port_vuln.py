import database.mysql_excute as _sql

class _port_vuln:
    def __init__(self, port_id, cve_num, last_updated):
        super().__init__()
        self.port_id = port_id
        self.cve_num = cve_num
        self.last_updated =last_updated

# GET
def _get_by_id(conn, port_id):
    sql_string = "SELECT * FROM port_vuln_tbl  WHERE port_id = {0}".format(port_id)
    result = _sql._get_list_items(conn, sql_string)
    return data


# ADD
def _add_to_db(conn, port_vuln):
    sql_string = "INSERT INTO port_vuln_tbl(port_id, cve_num, last_updated) \
            VALUES({0},'{1}',{2})".format(*port_vuln)
    result = _sql._excute_without_return(conn, sql_string)
    return result

# UPDATE
def _update_by_id(conn, port_vuln):
    sql_string = "UPDATE port_vuln_tbl SET cve_num = '{1}', last_updated = {2} WHERE port_id = {0}".format(*port_vuln)
    result = _sql._excute_without_return(conn, sql_string)
    return result

