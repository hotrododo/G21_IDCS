import database.mysql_excute as _sql

def _delete_verify_code(conn, user_name):
    sql_string = "DELETE FROM verify_tbl WHERE user_name = '{0}'".format(user_name)
    result = _sql._excute_without_return(conn, sql_string)
    return result

def _add_verify_code(conn, verify):
    sql_string = "INSERT INTO verify_tbl(time_stamp, user_name, verify_code) \
        VALUES('{0}', '{1}', '{2}')".format(verify["time_stamp"], verify["user_name"], verify["verify_code"])
    result = _sql._excute_without_return(conn, sql_string)
    return result

def get_code_by_user(conn, user_name):
    sql_string = "SELECT * FROM verify_tbl WHERE user_name = '{0}'".format(user_name)
    result = _sql._get_an_item(conn, sql_string)
    if result is None:
        return None
    return _convert.verify_code_to_dict(result)


def expiry_code(conn, time_stamp):
    sql_string = "DELETE FROM verify_tbl WHERE time_stamp < {}".format(time_stamp)
    result = _sql._excute_without_return(conn, sql_string)
    return result

def _get_verify_code(conn, user):
    sql_string = "SELECT * FROM verify_tbl WHERE user_name = '{0}'".format(user["userName"])
    result = _sql._get_an_item(conn, sql_string)
    return result
    