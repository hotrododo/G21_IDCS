class _xstask:
    def __init__(self, user_name, ipv4, status, time_stamp):
        super().__init__()
        self.user_name = user_name
        self.ipv4 = ipv4
        self.status = status
        self.time_stamp = time_stamp

def _get_from_db(conn, data):
    try:
        with conn as cursor:
            sql_string = "SELECT * FROM task_tbl  WHERE u_id = '{0}' AND host_ip = '{1}'"
            sql_string.format(data["user_name"],data["ipv4"])
            cursor.execute(sql_string)
            data = cursor.fetchone()
    except mysql.connector.Error as error:
        print("Failed to get table record: {}".format(error))
        return None
    return data

def _add_to_db(conn, data):
    try:
        with conn as cursor:
            sql_string = "INSERT INTO task_tbl(u_id, host_ip, status, time_stamp) \
                VALUE('{0}','{1}',{2},{3})"
            sql_string.format(*data.values())
            cursor.execute(sql_string)
        conn.commit()
    except mysql.connector.Error as error:
        print("Failed to update table record: {}".format(error))
        return False
    return True

def _get_by_user_name(conn, data):
    try:
        with conn as cursor():
            sql_string = "SELECT * FROM task_tbl  WHERE u_id = '{0}'"
            sql_string.format(data["user_name"])
            cursor.execute(sql_string)
            data = cursor.fetchall()
    except mysql.connector.Error as error:
        print("Failed to get table record: {}".format(error))
        return None
    return data

def _update_status(data):
    try:
        with conn as cursor:
            sql_string = "UPDATE task_tbl SET status = {0} WHERE user_name = '{1}' AND ipv4 = '{2}'"
            sql_string.format(data["status"], data["user_name"], data["ipv4"])
            cursor.execute(sql_string)
            conn.commit()
    except mysql.connector.Error as error:
        print("Failed to Update table record: {}".format(error))
        return False
    return True


def _task_list_to_dict(l_data):
    d_data = {"user_name":l_data[0], "ipv4":l_data[1], "status":l_data[2], "time_stamp":l_data[3]}
    return d_data

    