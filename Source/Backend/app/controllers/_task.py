import database.mysql_excute as _sql
class _task:
    def __init__(self, user_name, ipv4, status, time_stamp):
        super().__init__()
        self.user_name = user_name
        self.ipv4 = ipv4
        self.status = status
        self.time_stamp = time_stamp


def _get_from_db(conn, data):
    sql_string = "SELECT * FROM task_tbl  WHERE user_name = %s AND ipv4 = %s"
    result = _sql._get_an_item(conn, sql_string, tuple([data["userName"],data["ipv4"]]))
    return result

# status = 0 : doesn't excute.
def _get_list_new(conn):
    sql_string = "SELECT * FROM task_tbl WHERE status = 0 ORDER BY time_stamp DESC"
    result = _sql._get_an_item(conn, sql_string, None)
    return result


def _add_to_db(conn, data):
    sql_string = "INSERT INTO task_tbl(ipv4, user_name, status, time_stamp) \
        VALUE(%s,%s,%s,%s)"
    result = _sql._excute_without_return(conn, sql_string, tuple([*data.values()]))
    return result


def _get_by_user_name(conn, data):
    sql_string = "SELECT * FROM task_tbl  WHERE user_name = %s"
    result = _sql._get_list_items(conn, sql_string, tuple([data["user_name"]]))
    return result


def _update_status(conn, data):
    sql_string = "UPDATE task_tbl SET status = %s WHERE user_name = %s AND ipv4 = %s"
    result = _sql._excute_without_return(conn, sql_string, tuple([data[2], data[0], data[1]]))
    return result
            


def _task_list_to_dict(l_data):
    d_data = {"user_name":l_data[0], "ipv4":l_data[1], "status":l_data[2], "time_stamp":l_data[3]}
    return d_data

    