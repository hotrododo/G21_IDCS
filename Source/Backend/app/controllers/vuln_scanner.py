#!/usr/bin/python3

import importlib
import os
import re
import sys


CDIR = os.path.dirname(os.path.realpath(__file__))
ROOTDIR = os.path.abspath(os.path.join(CDIR, os.pardir))


def load_plugins(platform, service):
    """Load all modules in the 'plugins' subdirectory.

    Returns:
    - modules: (type: Module list) list of modules.
    """
    subdir = "plugins"
    if platform:
        subdir = subdir + "/" + platform
    if service:
        subdir = subdir + "/" + service
    try:
        pysearchre = re.compile('.py$', re.IGNORECASE)
        # remove last path component
        dirname, tail = os.path.split(os.path.dirname(__file__))
        pluginfiles = filter(pysearchre.search, os.listdir(os.path.join(dirname, subdir)))
        subdir = subdir.replace("/",".")
        form_module = lambda fp: '.' + os.path.splitext(fp)[0]
        plugins = map(form_module, pluginfiles)
        importlib.import_module(subdir)
        modules = []

        for plugin in plugins:
            if not plugin.startswith('.__'):
                modules.append(importlib.import_module(plugin, package=subdir))
        return modules
    except Exception as e:
        print(e)
        pass



def scan(ip, port, platform, service):
    vulns = []
    plugins = load_plugins(platform, service)
    for plugin in plugins:
        res = plugin.check(ip, port)
        if res:
            vulns.append(plugin.NAME)
    return vulns

# print(scan("123.31.41.27",3389,"windows","rdp"))