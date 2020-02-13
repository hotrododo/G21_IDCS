#!/usr/bin/python3

from configparser import SafeConfigParser
import os

class baseObj:
    def __init__(
            self,
            app_host,
            app_port,
            db_user,
            db_password,
            db_name,
            db_host,
            db_port,
            workers,
            location):
        self.app_host = app_host
        self.app_port = app_port
        self.db_user = db_user
        self.db_password = db_password
        self.db_name = db_name
        self.db_host = db_host
        self.db_port = db_port
        self.workers = workers
        self.location = location

def get_base_config():
    parser = SafeConfigParser()
    parser.read(
        os.path.join(
            os.path.dirname(__file__),
            'config',
            'app.conf'))

    app_host=parser.get('Flask', 'app_host')
    app_port=int(parser.get('Flask', 'app_port'))
    db_user=parser.get('Mysql', 'db_user')
    db_password=parser.get('Mysql', 'db_password')
    db_name=parser.get('Mysql', 'db_name')
    db_host=parser.get('Mysql', 'db_host')
    db_port=int(parser.get('Mysql', 'db_port'))
    workers=parser.get('Scanner', 'workers')
    location=parser.get('Vuln', 'location')
    return baseObj(
        app_host,
        app_port,
        db_user,
        db_password,
        db_name,
        db_host,
        db_port,
        workers,
        location)
