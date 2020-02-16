import database.mysql_excute as _sql
import controllers._convert as _convert

class _user:
    def __init__(self):
        super().__init__()

#get an user
def _get_by_name(conn, user_name):
    sql_string = """SELECT * FROM user_tbl WHERE user_name = %s"""
    data = _sql._get_an_item(conn, sql_string, tuple([user_name]))
    if data is None:
        return None
    return _convert.user_list_to_dict(data)
    
# get list user
def _get_list(conn):
    sql_string = ("SELECT * FROM user_tbl")
    data = _sql._get_list_items(conn, sql_string, None)
    return _convert.users_to_dict(data)

# return True when update completed, False when failed.
def _update_info(conn, user):
    user_temp = _get_by_name(conn, user["userName"])
    if user_temp is not None:
        #genarate sql string
        sql_string = "UPDATE user_tbl u SET u.full_name = %s, u.gender = %s, u.email = %s, \
            u.phone = %s, u.address = %s, u.credits = %s, \
            u.user_type = %s WHERE u.user_name = %s"
        params = tuple([user["fullName"], user["gender"], \
            user["emailAddress"], user["phoneNumber"], user["address"], \
            user["credits"], user["userType"], user["userName"]])
        result = _sql._excute_without_return(conn, sql_string, params)
        return result
    return None

def _update_password(conn, user_name, password):
    user_temp = _get_by_name(conn, user_name)
    if not user_temp: 
        sql_string = "UPDATE user_tbl u SET u.password = %s WHERE u.user_name = %s"
        result = _sql._excute_without_return(conn, sql_string, tuple([password, user_name]))
        return result
    return None
        
    
def _add(conn, user):
    user_temp = _get_by_name(conn, user["userName"])
    if user_temp is None:  
        sql_string = "INSERT INTO user_tbl(user_name, password, full_name, gender, email, \
            phone, address, credits, user_type) \
            VALUES(%s,%s,%s,%s,%s,%s,%s,%s,%s)"
        result = _sql._excute_without_return(conn, sql_string, tuple([*_convert.user_dict_to_list(user)]))
        return result
    return None



def _update_user_type(conn, user):
    sql_string = "UPDATE user_tbl SET user_type = %s WHERE user_name = %s"
    result = _sql._excute_without_return(conn, sql_string, tuple([user["userType"], user["userName"]]))
    return result