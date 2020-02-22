/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSTCopyright.IDCS.utils;

import CSTCopyright.IDCS.model.MyObject;

/**
 *
 * @author mac
 */
public class DBUtils {
    public static MyObject GetHost(String ipv4, String userName){
        String json = "{\"ipv4\":\"" + ipv4 + "\",\"userName\":\"" + userName + "\"}";
        String url = "http://127.0.0.1:5000/idcs/host/get";
        return HttpRequestUtils.PostRequest(url, json);
    }
    
    public static MyObject VerifyUser(String userName, String password) {
        String json = "{\"userName\":\"" + userName + "\",\"password\":\"" + password + "\"}";
        String url = "http://127.0.0.1:5000/idcs/user/verify";
        return HttpRequestUtils.PostRequest(url, json);
    }
    
    
}
