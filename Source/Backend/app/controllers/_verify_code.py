import database.mysql_excute as _sql

def _delete_verify_code(conn, user_name):
    sql_string = "DELETE FROM verify_tbl WHERE user_name = %s"
    result = _sql._excute_without_return(conn, sql_string, tuple([user_name]))
    return result

def _add_verify_code(conn, verify):
    sql_string = "INSERT INTO verify_tbl(time_stamp, user_name, verify_code) \
        VALUES(%s, %s, %s)"
    result = _sql._excute_without_return(conn, sql_string, tuple([verify["time_stamp"], verify["user_name"], verify["verify_code"]]))
    return result

def get_code_by_user(conn, user_name):
    sql_string = "SELECT * FROM verify_tbl WHERE user_name = %s"
    result = _sql._get_an_item(conn, sql_string, tuple([user_name]))
    if result is None:
        return None
    return _convert.verify_code_to_dict(result)


def expiry_code(conn, time_stamp):
    sql_string = "DELETE FROM verify_tbl WHERE time_stamp < %s"
    result = _sql._excute_without_return(conn, sql_string, tuple([time_stamp]))
    return result

def _get_verify_code(conn, user):
    sql_string = "SELECT * FROM verify_tbl WHERE user_name = %s"
    result = _sql._get_an_item(conn, sql_string, tuple([user["userName"]]))
    return result
    