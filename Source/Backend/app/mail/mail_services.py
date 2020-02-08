from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
import smtplib
from string import Template
import os
import logging

#SMTP configurations
MY_ADDRESS = 'noreply.idcs@gmail.com'
PASSWORD = 'jxrjdilpkhmnrpsi'
SMTP_HOST = 'smtp.gmail.com'
SMTP_PORT = 25
# SMTP
# send new password to user mail
def send_access_code_to_mail(user, access_code, exp_date):
    dirname, tail = os.path.split(os.path.dirname(__file__))
    message_template = read_template(os.path.join(dirname, "mail/verify_code_temple.html"))
    message = message_template.substitute(full_name=user["fullName"], access_code=access_code, exp_date=exp_date)
    msg = MIMEMultipart()
    msg['From'] = MY_ADDRESS
    msg['To'] = user["emailAddress"]
    msg['Subject']= "!!Change password validation code - IDCS"
    msg.attach(MIMEText(message, 'html'))
    try:
        # init smpt service
        mail = smtplib.SMTP(SMTP_HOST, SMTP_PORT)
        mail.ehlo()
        mail.starttls()
        mail.login(MY_ADDRESS, PASSWORD)
        mail.send_message(msg)
        del msg
        mail.quit()
    except Exception as ex:
        logging.error(str(ex.errors()))
        return False
    return True

# read template from file
def read_template(filename):
    with open(filename, 'r', encoding='utf-8') as template_file:
        template_file_content = template_file.read()
    return Template(template_file_content)