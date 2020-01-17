import mysql.connector

def _get_an_item(conn, sql_string):
    try:
        with conn as cursor:
            cursor.execute(sql_string)
            data = cursor.fetchone()
    except mysql.connector.Error as error:
        print("Error: {}".format(error))
        return None
    return data

def _get_list_items(conn, sql_string):
    try:
        with conn as cursor:
            cursor.execute(sql_string)
            data = cursor.fetchall()
    except mysql.connector.Error as error:
        print("Error: {}".format(error))
        return None
    return data

def _excute_without_return(conn, sql_string):
    try:
        with conn as cursor:
            cursor.execute(sql_string)
            conn.commit()
    except mysql.connector.Error as error:
        print("Error: {}".format(error))
        return False
    return True