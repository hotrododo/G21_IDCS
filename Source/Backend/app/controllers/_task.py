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
            sql_string = "SELECT * FROM task_tbl  WHERE u_id = '{0}' AND host_ip = '{1}"
            sql_string.format(data["user_name"],data["ipv4"])
            cursor.execute(sql_string)
            data = cursor.fetchone()
    except mysql.connector.Error as error:
        print("Failed to update table record: {}".format(error))
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