import database.mysql_excute as _sql
class _task:
    def __init__(self, user_name, ipv4, status, time_stamp):
        super().__init__()
        self.user_name = user_name
        self.ipv4 = ipv4
        self.status = status
        self.time_stamp = time_stamp


def _get_from_db(conn, data):
    sql_string = "SELECT * FROM task_tbl  WHERE u_id = '{0}' AND host_ip = '{1}'"
    sql_string.format(data["user_name"],data["ipv4"])
    result = _sql._get_an_item(conn, sql_string)
    return result


def _add_to_db(conn, data):
    sql_string = "INSERT INTO task_tbl(u_id, host_ip, status, time_stamp) \
        VALUE('{0}','{1}',{2},{3})"
    sql_string.format(*data.values())
    result = _sql._excute_without_return(conn, sql_string)
    return result


def _get_by_user_name(conn, data):
    sql_string = "SELECT * FROM task_tbl  WHERE u_id = '{0}'"
    sql_string.format(data["user_name"])
    result = _sql._get_list_items(conn, sql_string)
    return result


def _update_status(data):
    sql_string = "UPDATE task_tbl SET status = {0} WHERE user_name = '{1}' AND ipv4 = '{2}'"
    sql_string.format(data["status"], data["user_name"], data["ipv4"])
    result = _sql._excute_without_return(conn, sql_string)
    return result
            


def _task_list_to_dict(l_data):
    d_data = {"user_name":l_data[0], "ipv4":l_data[1], "status":l_data[2], "time_stamp":l_data[3]}
    return d_data

    