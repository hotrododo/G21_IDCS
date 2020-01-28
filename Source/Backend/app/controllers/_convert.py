
def port_list_to_dict(port):
    port_dict = {"port_id":port[0],"port_num":port[1],"host_ip":port[2], "service_name":port[3], \
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

# convert multiple list user to dict
def users_to_dict(users):
    data = {}
    for user in users:
        dict_user = user_list_to_dict(user)
        data[user[0]] = dict_user
    return data

# port id-ness to dict
def port_to_dict(port):
    return {"port_id":port[0],"port_num":port[1],"host_ip":port[2], "service_name":port[3], \
    "version":port[4], "status":port[5], "cpe":port[6]}


# convert multiple list ports to dict
def ports_to_dict(ports):
    data = {}
    for port in ports:
        data[port[1]] = port_to_dict(port)
    return data

# convert task to dict:
def task_to_dict(task):
    return {"user_name":task[0], "ipv4": task[1], "status":task[2], "time_stamp":task[3]}

# convert tasks to dict
def tasks_to_dict(tasks):
    data = {}
    count = 0
    for task in tasks:
        count += 1
        task_dict = task_to_dict(task)
        data[str(count)] = task_dict
    return data

# convert host to dict
def host_to_dict(host):
    return {"ipv4":host[0], "ipv6":host[1], "dns":host[2], "net_name":host[3], "country":host[4], \
        "oraganization":host[5], "last_updated":host[6]}
