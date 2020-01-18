
def port_list_to_dict(port):
    port_dict = {"port_id":port[0],"port_num":port[1],"host_ip":port[2], "service_name":port[3], /
    "version":port[4], "status":port[5], "cpe":port[6]}
    return port_dict


# convert user list to dict
def user_list_to_dict(user):
    return {"userName":user[0],"password":user[1],"fullName":user[2],"gender":user[3],"emailAddress":user[4], \
    "phoneNumber":user[5],"address":user[6],"credits":user[7],"userType":user[8]}

# convert user dict to list
def user_dict_to_list(user):
    return [user["userName"],user["password"],user["fullName"],user["gender"],user["emailAddress"],\
        user["phoneNumber"],user["address"],user["credits"],user["userType"]]
