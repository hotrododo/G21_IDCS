/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Objects;

/**
 *
 * @author mac
 */
public class VultModel {

    public VultModel() {
    }
    private int VultID;         //vulnerabilities ID
    private String VultObj;     //vulnerabilities service
    private String VultDes;     //vulnerabilities string stamp
    private String VultRem;     //vulnerabilities remedies
    private String VultNote;    //keyword for open search

    public int getVultID() {
        return VultID;
    }

    public void setVultID(int VultID) {
        this.VultID = VultID;
    }

    public String getVultObj() {
        return VultObj;
    }

    public void setVultObj(String VultObj) {
        this.VultObj = VultObj;
    }

    public String getVultDes() {
        return VultDes;
    }

    public void setVultDes(String VultDes) {
        this.VultDes = VultDes;
    }

    public String getVultRem() {
        return VultRem;
    }

    public void setVultRem(String VultRem) {
        this.VultRem = VultRem;
    }

    public String getVultNote() {
        return VultNote;
    }

    public void setVultNote(String VultNote) {
        this.VultNote = VultNote;
    }

    public VultModel(int VultID, String VultObj, String VultDes, String VultRem, String VultNote) {
        this.VultID = VultID;
        this.VultObj = VultObj;
        this.VultDes = VultDes;
        this.VultRem = VultRem;
        this.VultNote = VultNote;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        VultModel vult = (VultModel) obj;
        return VultID == vult.VultID && VultObj.equals(VultObj) && VultDes.equals(vult.VultDes)
                && VultRem.equals(vult.VultRem) && VultNote.equals(vult.VultNote);
    }

    @Override
    public int hashCode() {
        return Objects.hash(VultID, VultObj, VultDes, VultRem, VultNote);
    }
    
}
