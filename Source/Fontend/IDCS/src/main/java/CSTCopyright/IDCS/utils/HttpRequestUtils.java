/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSTCopyright.IDCS.utils;

import CSTCopyright.IDCS.model.MyObject;
import CSTCopyright.IDCS.model.UserAccount;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

/**
 *
 * @author mac
 */
public class HttpRequestUtils {

    public static MyObject PostRequest(String url, String json) {
        MyObject mobj = new MyObject();
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        try {
            StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            HttpResponse httpResponse = client.execute(httpPost);
            String content = IOUtils.toString(httpResponse.getEntity().getContent(), "UTF-8");
            JSONObject jobj = new JSONObject(content);
            int code = httpResponse.getStatusLine().getStatusCode();
            mobj.setCode(code);
            mobj.setObj(jobj);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(HttpRequestUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HttpRequestUtils.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return mobj;
    }
    
    public static UserAccount VerifyUser(String userName, String password) {
        UserAccount user = new UserAccount();
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://127.0.0.1:5000/idcs/user/verify");
        String json = "{\"userName\":\"" + userName + "\",\"password\":\"" + password + "\"}";
        try {
            StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            HttpResponse httpResponse = client.execute(httpPost);
            String content = IOUtils.toString(httpResponse.getEntity().getContent(), "UTF-8");
            Gson gson = new Gson();
            user = gson.fromJson(content, UserAccount.class);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(HttpRequestUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HttpRequestUtils.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return user;
    }
    

    public static boolean AddAnUser() {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://127.0.0.1:5000/idcs/user/add");
        String json = "{\"userName\":\"minhpv\",\"password\":\"minhpv\",\"fullName\":\"Pham Van Minh\",\"gender\":1,\"emailAddress\":\"minhpv@email.com\", "
                + "\"phoneNumber\":\"0987654321\",\"address\":\"HaNoi, Viet Nam\",\"credits\":1,\"userType\":1}";
        try {
            StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            HttpResponse httpResponse = client.execute(httpPost);
            String content = IOUtils.toString(httpResponse.getEntity().getContent(), "UTF-8");
            System.out.println(content);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(HttpRequestUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HttpRequestUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}

