import datetime, os
import csv, requests
import database.mysql_excute as _sql

class _vuln:
    def __init__(self, cve_num, cve_desc, last_updated):
        super().__init__()
        self.cve_num = cve_num
        self.cve_desc = cve_desc
        self.last_updated = last_updated

# GET
def get_by_num(conn, cve_num):
    sql_string = "SELECT * FROM vuln_tbl  WHERE cve_num = %s"
    result = _sql._get_an_item(conn, sql_string, tuple([cve_num]))
    return data

def get_by_status(conn, cve_status):
    try:
        sql_string = """SELECT cve_num FROM vuln_tbl  WHERE cve_status = %s;"""
        #sql_string.format(cve_status)
        result = _sql._get_list_items(conn, sql_string, tuple([cve_status]))
        return result
    except Exception as e:
        print("get_by_status"+str(e))
        return []

# ADD
def _add_to_db(conn, vuln):
    sql_string = "INSERT INTO vuln_tbl(cve_num, cve_desc, last_updated) \
            VALUES(%s,%s,%s)"
    result = _sql._excute_without_return(conn, sql_string, tuple([*vuln]))
    return result

# UPDATE
def _update_by_id(conn, vuln):
    sql_string = "UPDATE vuln_tbl SET cve_desc = %s, last_updated = %s WHERE cve_num = %s"
    result = _sql._excute_without_return(conn, sql_string, tuple([*vuln]))
    return result

# Read file and update to database
def _update_vuln_from_file(conn, link):
    lastmodified = requests.head(link).headers['Last-Modified']
    if os.path.exists("lastupdate"):
        if open('lastupdate').read().strip() == lastmodified:
            return True
    try:
        entry = get_by_status(conn, ["Entry"])
        candidate = get_by_status(conn, ["Candidate"])
        entry = [e for tup in entry for e in tup]
        candidate = [e for tup in candidate for e in tup]
        res = requests.get(link)
        text = res.iter_lines(decode_unicode='utf-8')
        reader = csv.reader(text, delimiter=',')
        for row in reader:
            cve_num = row[0]
            cve_status = row[1] 
            cve_desc = row[2]
            vuln = [cve_num, cve_status, cve_desc]
            if cve_num in entry:
                continue
            elif cve_num in candidate:
                if cve_status == "Candidate":
                    continue
                else:
                    result = _update_by_id(conn, vuln)
            elif reader.line_num > 10:
                result = _add_to_db(conn, vuln)
    except Exception as ex:
        print(ex)
        return False
    open('lastupdate','w+').write(lastmodified)
    return True
