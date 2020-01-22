/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author mac
 */
public class ServiceModel {

    public ServiceModel() {
    }

    public ServiceModel(String S_ID, String PORTNUM, String SCRIPT) {
        this.S_ID = S_ID;
        this.PORTNUM = PORTNUM;
        this.SCRIPT = SCRIPT;
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

    public String getSCRIPT() {
        return SCRIPT;
    }

    public void setSCRIPT(String SCRIPT) {
        this.SCRIPT = SCRIPT;
    }
    private String S_ID;
    private String PORTNUM;
    private String SCRIPT;
}
