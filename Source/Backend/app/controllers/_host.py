class _host:
    def __init__(self, ipv4, ipv6, dns, net_name, country, oraganization, last_updated):
        super().__init__()
        self.ipv4 = ipv4
        self.ipv6 = ipv6
        self.dns = dns
        self.net_name = net_name
        self.country = country
        self.oraganization = oraganization
        self.last_updated = last_updated
    
    def _get_by_ip(conn, host):
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
    def _update_to_db(conn, host):
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

    def _delete_from_db(conn, host):
        try:
            with conn as cursor:
                sql_string = "DELETE * FROM host_tbl WHERE ipv4 = '{}'".format(host["ipv4"])
                cursor.execute(sql_string)
                conn.commit()
        except mysql.connector.Error as error:
            print("Failed to update table record: {}".format(error))
            return False
        return True

        