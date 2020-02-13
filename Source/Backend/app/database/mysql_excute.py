import mysql.connector
import logging

def _get_an_item(conn, sql_string, para):
    try:
        with conn as cursor:
            cursor.execute(sql_string, para)
            data = cursor.fetchone()
    except mysql.connector.Error as error:
        logging.error("Error: {}".format(error))
        return None
    return data

def _get_list_items(conn, sql_string, para):
    try:
        with conn as cursor:
            cursor.execute(sql_string, para)
            data = cursor.fetchall()
    except mysql.connector.Error as error:
        logging.error("Error: {}".format(error))
        return None
    return data

def _excute_without_return(conn, sql_string, para):
    try:
        with conn as cursor:
            cursor.execute(sql_string, para)
            conn.commit()
    except mysql.connector.Error as error:
        logging.error("Error: {}".format(error))
        return False
    return True