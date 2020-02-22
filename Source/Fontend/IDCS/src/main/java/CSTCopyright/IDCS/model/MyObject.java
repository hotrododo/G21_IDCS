/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSTCopyright.IDCS.model;

import org.json.JSONObject;

/**
 *
 * @author mac
 */
public class MyObject {
    public MyObject(int code, JSONObject obj) {
        this.code = code;
        this.obj = obj;
    }

    public MyObject() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public JSONObject getObj() {
        return obj;
    }

    public void setObj(JSONObject obj) {
        this.obj = obj;
    }
    private int code;
    private JSONObject obj;
    
}
