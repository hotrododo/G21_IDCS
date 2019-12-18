/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSTCopyright.IDCS.model;

import java.sql.Date;

/**
 *
 * @author macbook
 */
public class Scan {

    String S_ID;
    String U_ID;
    String TARGET;
    String IPv4;
    String NETNAME;
    String DDNS;
    String DESCR;
    String COUNTRY;
    String PERSON;
    String EMAIL;
    Date DATESCAN;

    public Scan() {
    }

    public String getS_ID() {
        return S_ID;
    }

    public void setS_ID(String S_ID) {
        this.S_ID = S_ID;
    }

    public String getU_ID() {
        return U_ID;
    }

    public void setU_ID(String U_ID) {
        this.U_ID = U_ID;
    }

    public String getTARGET() {
        return TARGET;
    }

    public void setTARGET(String TARGET) {
        this.TARGET = TARGET;
    }

    public String getIPv4() {
        return IPv4;
    }

    public void setIPv4(String IPv4) {
        this.IPv4 = IPv4;
    }

    public String getNETNAME() {
        return NETNAME;
    }

    public void setNETNAME(String NETNAME) {
        this.NETNAME = NETNAME;
    }

    public String getDDNS() {
        return DDNS;
    }

    public void setDDNS(String DDNS) {
        this.DDNS = DDNS;
    }

    public String getDESCR() {
        return DESCR;
    }

    public void setDESCR(String DESCR) {
        this.DESCR = DESCR;
    }

    public String getCOUNTRY() {
        return COUNTRY;
    }

    public void setCOUNTRY(String COUNTRY) {
        this.COUNTRY = COUNTRY;
    }

    public String getPERSON() {
        return PERSON;
    }

    public void setPERSON(String PERSON) {
        this.PERSON = PERSON;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public Date getDATESCAN() {
        return DATESCAN;
    }

    public void setDATESCAN(Date DATESCAN) {
        this.DATESCAN = DATESCAN;
    }
    
    
}
