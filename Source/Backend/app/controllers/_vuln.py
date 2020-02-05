import datetime


class _vuln:
    def __init__(self, cve_num, cve_desc, last_updated):
        super().__init__()
        self.cve_num = cve_num
        self.cve_desc = cve_desc
        self.last_updated = last_updated

# GET
def get_by_num(conn, cve_num):
    sql_string = "SELECT * FROM vuln_tbl  WHERE cve_num = '{0}'".format(cve_num)
    result = _sql._get_an_item(conn, sql_string)
    return data


# ADD
def _add_to_db(conn, vuln):
    sql_string = "INSERT INTO vuln_tbl(cve_num, cve_desc, last_updated) \
            VALUES({0},'{1}',{2})".format(*vuln)
    result = _sql._excute_without_return(conn, sql_string)
    return result

# UPDATE
def _update_by_id(conn, vuln):
    sql_string = "UPDATE vuln_tbl SET cve_desc = '{1}', last_updated = {2} WHERE cve_num = '{0}'".format(*vuln)
    result = _sql._excute_without_return(conn, sql_string)
    return result

# Read file and update to database
def _update_vuln_from_file(conn, file_name):
    last_updated = datetime.now()
    with open(file_name) as infile:
        for line in infile:
            data_list = line.split(",",3)
            if "CVE-" in data_list[0]:
                cve_num = data_list[0]
                cve_desc = data_list[2]
                vuln = [cve_num, cve_desc, last_updated]
                if get_by_num(conn, cve_num) is not None:
                    result = _update_by_id(conn, vuln)
                else:
                    result = _add_to_db(conn, vuln)
