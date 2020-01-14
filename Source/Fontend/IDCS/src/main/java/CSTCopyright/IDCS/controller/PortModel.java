package CSTCopyright.IDCS.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mac
 */
public class PortModel {

    public PortModel() {
    }

    public PortModel(String S_ID, String PORTNUM, String NAME, String VERSION, String CPE, String PRODUCT, String EXTRALINFO) {
        this.S_ID = S_ID;
        this.PORTNUM = PORTNUM;
        this.NAME = NAME;
        this.VERSION = VERSION;
        this.CPE = CPE;
        this.PRODUCT = PRODUCT;
        this.EXTRALINFO = EXTRALINFO;
    }

    public String getS_ID() {
        return S_ID;
    }

    public void setS_ID(String S_ID) {
        this.S_ID = S_ID;
    }

    public String getPORTNUM() {
        return PORTNUM;
    }

    public void setPORTNUM(String PORTNUM) {
        this.PORTNUM = PORTNUM;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getVERSION() {
        return VERSION;
    }

    public void setVERSION(String VERSION) {
        this.VERSION = VERSION;
    }

    public String getCPE() {
        return CPE;
    }

    public void setCPE(String CPE) {
        this.CPE = CPE;
    }

    public String getPRODUCT() {
        return PRODUCT;
    }

    public void setPRODUCT(String PRODUCT) {
        this.PRODUCT = PRODUCT;
    }

    public String getEXTRALINFO() {
        return EXTRALINFO;
    }

    public void setEXTRALINFO(String EXTRALINFO) {
        this.EXTRALINFO = EXTRALINFO;
    }
    private String S_ID;
    private String PORTNUM;
    private String NAME;
    private String VERSION;
    private String CPE;
    private String PRODUCT;
    private String EXTRALINFO;
}
