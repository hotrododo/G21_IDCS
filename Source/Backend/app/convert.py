# convert object list to dict
def user_list_to_dict(data):
    return {"userName":data[0],"password":data[1],"fullName":data[2],"gender":data[3],"emailAddress":data[4], \
    "phoneNumber":data[5],"address":data[6],"credits":data[7],"userType":data[8]}

# convert user dict to list
def user_dict_to_list(data):
    return [data["userName"],data["password"],data["fullName"],data["gender"],data["emailAddress"],\
        data["phoneNumber"],data["address"],data["credits"],data["userType"]]
