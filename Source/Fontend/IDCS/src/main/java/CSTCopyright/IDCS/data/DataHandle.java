/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSTCopyright.IDCS.data;

import com.sun.javafx.util.Utils;
import org.json.JSONObject;

/**
 *
 * @author mac
 */
public class DataHandle {
//    check host can scan
    public static boolean serviceHasScript(String data) {
        if(data == null || data.equals("")){
            return false;
        }
        return Utils.contains(data, "script");
    }
    //check host up
    public static boolean hostHasUp(String data) {
        if(data == null || data.equals("")){
            return false;
        }
        JSONObject jObject = new JSONObject(data);
        String upHosts = jObject.getJSONObject("nmap").getJSONObject("scanstats").getString("uphosts");
        return !upHosts.equals("0");
    }
    //check host has registed
    public static boolean hostHasSign(String data) {
        if(data == null || data.equals("")){
            return false;
        }
        return Utils.contains(data, "hostscript");
    }
}
