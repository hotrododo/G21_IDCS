/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idcs.model;

import java.sql.Date;

/**
 *
 * @author DevIDCS
 */
public class User {

    private String strUsername;
    private String strPassword;
    private String strEmail;
    private String strFullname;
    private String strPhone;
    private String straddress;
    private String bGender;
    private Date expiryDate;
    private int nUserType;
    
    public User(){
        
    }

    public User(String strUsername, String strPassword, String strEmail, String strFullname, String strPhone, String straddress, String bGender, Date expiryDate, int nUserType) {
        this.strUsername = strUsername;
        this.strPassword = strPassword;
        this.strEmail = strEmail;
        this.strFullname = strFullname;
        this.strPhone = strPhone;
        this.straddress = straddress;
        this.bGender = bGender;
        this.expiryDate = expiryDate;
        this.nUserType = nUserType;
    }

    

    public String getStrUsername() {
        return strUsername;
    }

    public void setStrUsername(String strUsername) {
        this.strUsername = strUsername;
    }

    public String getStrPassword() {
        return strPassword;
    }

    public void setStrPassword(String strPassword) {
        this.strPassword = strPassword;
    }

    public String getStrEmail() {
        return strEmail;
    }

    public void setStrEmail(String strEmail) {
        this.strEmail = strEmail;
    }

    public String getStrFullname() {
        return strFullname;
    }

    public void setStrFullname(String strFullname) {
        this.strFullname = strFullname;
    }

    public String getStrPhone() {
        return strPhone;
    }

    public void setStrPhone(String strPhone) {
        this.strPhone = strPhone;
    }

    public String isbGender() {
        return bGender;
    }

    public void setbGender(String bGender) {
        this.bGender = bGender;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getnUserType() {
        return nUserType;
    }

    public void setnUserType(int nUserType) {
        this.nUserType = nUserType;
    }

    public String getStraddress() {
        return straddress;
    }

    public void setStraddress(String straddress) {
        this.straddress = straddress;
    }
    

    @Override
    public String toString() {
        return "User { " + strUsername + " - " + strPassword + " - " + strFullname + " - "+ strEmail 
                + " - " + strPhone + " - " + bGender + " - " + expiryDate + " - " + nUserType  + " }"; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
