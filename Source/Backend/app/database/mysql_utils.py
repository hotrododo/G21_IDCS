import mysql.connector
from convert import user_list_to_dict, user_dict_to_list
# 
#
# 
#  
#get an user
def get_user_by_name(conn, user_name):
    with conn as cursor:   
        cursor.execute("SELECT * FROM user_tbl WHERE user_name = '{0}'".format(user_name))
        data = cursor.fetchone()
    if data is None:
        return None
    return user_list_to_dict(data)
    
# get list user
def get_list_user(conn):
    cursor = conn.cursor()
    cursor.execute("SELECT * FROM user_tbl")
    data = cursor.fetchall()
    cursor.close()
    return data

# return True when update completed, False when failed.
def update_user_info(conn, user):
    user_temp = get_user_by_name(conn, user["userName"])
    if user_temp is not None:
        try:
            cursor = conn.cursor()
            #genarate sql string
            sql_string = "UPDATE user_tbl u SET u.full_name = '{0}', u.gender = {1}, u.email = '{2}', \
                u.phone = '{3}', u.address = '{4}', u.credits = {5}, \
                u.user_type = {6} WHERE u.user_name = '{7}'".format(user["fullName"], user["gender"], \
                    user["emailAddress"], user["phoneNumber"], user["address"], \
                    user["credits"], user["userType"], user["userName"])
            cursor.execute(sql_string)
            conn.commit()
        except mysql.connector.Error as error:
            print("Failed to update table record: {}".format(error))
            return False
    return True

def update_user_password(conn, user_name, password):
    user_temp = get_user_by_name(conn, user_name)
    if not user_temp:
        try:
            cursor = conn.cursor()
            sql_string = "UPDATE user_tbl u SET u.password = {0} WHERE u.user_name = {1}".format(password, user_name)
            cursor.execute(sql_string)
            conn.commit()
        except mysql.connector.Error as error:
            print("Failed to update table record: {}".format(error))
            return False
    return True
    
def add_user(conn, user):
    user_temp = get_user_by_name(conn, user["userName"])
    if user_temp is None:
        try:
            with conn as cursor:   
                sql_string = "INSERT INTO user_tbl(user_name, password, full_name, gender, email, \
                    phone, address, credits, user_type) \
                    VALUES('{0}','{1}','{2}',{3},'{4}','{5}','{6}',{7},{8})".format(*user_dict_to_list(user))
                cursor.execute(sql_string)
                conn.commit()
        except mysql.connector.Error as error:
            print("Failed to ADD table record: {}".format(error))
            return False
    return True


def get_verify_code(conn, user):
    try:
        with conn as cursor:
            sql_string = "SELECT * FROM verify_tbl WHERE user_name = '{0}'".format(user["userName"])
            cursor.execute(sql_string)
            data = cursor.fetchone()
        except mysql.connector.Error as error:
        print("Failed to update table record: {}".format(error))
        return None
    return data

def update_user_type(conn, user):
    try:
        with conn as cursor:
            sql_string = "UPDATE user_tbl SET user_type = {0} WHERE user_name = {1}"
            sql_string.format(user["userType"], user["userName"])
            cursor.execute(sql_string)
            conn.commit()
    except mysql.connector.Error as error:
        print("Failed to Update table record: {}".format(error))
        return False
    return True

def delete_verify_code(conn, user_name):
    try:
        with conn as cursor:
            sql_string = "DELETE * FROM verify_tbl WHERE user_name = '{0}'"
            sql_string.format(user_name)
            cursor.execute(sql_string)
            conn.commit()
    except mysql.connector.Error as error:
        print("Failed to Delete table record: {}".format(error))
        return False
    return True


def get_host_from_db(conn, host):
    try:
        with conn as cursor:
            sql_string = "SELECT * FROM host_tbl h WHERE h.ipv4 = {0}".format(host["ipv4"])
            cursor.execute(sql_string)
            data = cursor.fetchone()
    except mysql.connector.Error as error:
        print("Failed to GET table record: {}".format(error))
        return None
    return data


def add_host_to_db(conn, host):
    host_stamp = get_host_from_db(conn, host)
    if host_stamp is None:
        try:
            cursor = conn.cursor()
            # sql_string = "INSERT INTO host_tbl(ipv4,ipv6,dns,net_name,country,oraganization,asn,last_updated)"
            sql_string = "INSERT INTO host_tbl({0},{1},{2},{3},{4},{5},{6},{7}) \
                VALUE({8},{9},{10},{11},{12},{13},{14},{15},{16})".format(*host, *host.values())
            cursor.execute(sql_string)
        except mysql.connector.Error as error:
            print("Failed to ADD table record: {}".format(error))
            return False
    return True
# update host
def update_host_to_db(conn, host):
    try:
        with conn as cursor:
            sql_string = "UPDATE host_tbl SET ipv4 = '{0}', ipv6 = '{1}', dns = '{2}', net_name = '{3}', \
                country = '{4}', oraganization = '{5}', asn = '{6}', last_updated = {7}".format(*host.values())
            cursor.execute(sql_string)
            conn.commit()
    except mysql.connector.Error as error:
        print("Failed to update table record: {}".format(error))
        return False
    return True

def delete_host_from_db(conn, host):
    try:
        with conn as cursor:
            sql_string = "DELETE * FROM host_tbl WHERE ipv4 = '{}'".format(host["ipv4"])
            cursor.execute(sql_string)
            conn.commit()
    except mysql.connector.Error as error:
        print("Failed to update table record: {}".format(error))
        return False
    return True

# 
# PORT DB QUERY
def get_port_by_id(conn, port):
    try:
        with conn as cursor:
            sql_string = "SELECT * FROM port_tbl p WHERE p.port_id = {0}".format(port["port_id"])
            cursor.execute(sql_string)
            data = cursor.fetchone()
    except mysql.connector.Error as error:
        print("Failed to update table record: {}".format(error))
        return None
    return data

def get_port_by_host(conn, host):
    try:
        with conn as cursor:
            sql_string = "SELECT * FROM port_tbl p WHERE p.host_ip = {0}".format(host["ipv4"])
            cursor.execute(sql_string)
            data = cursor.fetchall()
    except mysql.connector.Error as error:
        print("Failed to update table record: {}".format(error))
        return None
    return data

