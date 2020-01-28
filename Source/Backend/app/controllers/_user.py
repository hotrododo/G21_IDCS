import database.mysql_excute as _sql
import controllers._convert as _convert

class _user:
    def __init__(self):
        super().__init__()

#get an user
def _get_by_name(conn, user_name):
    sql_string = ("SELECT * FROM user_tbl WHERE user_name = '{0}'".format(user_name))
    data = _sql._get_an_item(conn, sql_string)
    if data is None:
        return None
    return _convert.user_list_to_dict(data)
    
# get list user
def _get_list(conn):
    sql_string = ("SELECT * FROM user_tbl")
    data = _sql._get_list_items(conn, sql_string)
    return _convert.users_to_dict(data)

# return True when update completed, False when failed.
def _update_info(conn, user):
    user_temp = _get_by_name(conn, user["userName"])
    if user_temp is not None:
        #genarate sql string
        sql_string = "UPDATE user_tbl u SET u.full_name = '{0}', u.gender = {1}, u.email = '{2}', \
            u.phone = '{3}', u.address = '{4}', u.credits = {5}, \
            u.user_type = {6} WHERE u.user_name = '{7}'".format(user["fullName"], user["gender"], \
            user["emailAddress"], user["phoneNumber"], user["address"], \
            user["credits"], user["userType"], user["userName"])
        result = _sql._excute_without_return(conn, sql_string)
    return result

def _update_password(conn, user_name, password):
    user_temp = _get_by_name(conn, user_name)
    if not user_temp: 
        sql_string = "UPDATE user_tbl u SET u.password = {0} WHERE u.user_name = {1}".format(password, user_name)
        result = _sql._excute_without_return(conn, sql_string)
    return result
            
        
    
def _add(conn, user):
    user_temp = _get_by_name(conn, user["userName"])
    if user_temp is None:  
        sql_string = "INSERT INTO user_tbl(user_name, password, full_name, gender, email, \
            phone, address, credits, user_type) \
            VALUES('{0}','{1}','{2}',{3},'{4}','{5}','{6}',{7},{8})".format(*_convert.user_dict_to_list(user))
        result = _sql._excute_without_return(conn, sql_string)
    return result


def _get_verify_code(conn, user):
    sql_string = "SELECT * FROM verify_tbl WHERE user_name = '{0}'".format(user["userName"])
    result = _sql._get_an_item(conn, sql_string)
    return result


def _update_user_type(conn, user):
    sql_string = "UPDATE user_tbl SET user_type = {0} WHERE user_name = {1}"
    sql_string.format(user["userType"], user["userName"])
    result = _sql._excute_without_return(conn, sql_string)
    return result

def _delete_verify_code(conn, user_name):
    sql_string = "DELETE * FROM verify_tbl WHERE user_name = '{0}'"
    sql_string.format(user_name)
    result = _sql._excute_without_return(conn, sql_string)
    return result


