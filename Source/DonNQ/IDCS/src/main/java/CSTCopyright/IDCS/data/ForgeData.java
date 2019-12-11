/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSTCopyright.IDCS.data;

import CSTCopyright.IDCS.controller.DomainScan;
import CSTCopyright.IDCS.controller.UserAccount;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author mac
 */
public class ForgeData {
    
    /**
     *
     * @return
     */
    public static ArrayList<UserAccount> fakeListUser(){
        ArrayList<UserAccount> list = new ArrayList<>();
        UserAccount user = new UserAccount();
        user.setUserName("admin");
        user.setFullName("System Management");
        user.setPassword("admin");
        user.setEmailAddress("admin@cstcopyright.com");
        user.setGender(true);
        user.setExpiryDate(Date.valueOf("2020-11-11"));
        user.setUserType(1);
        user.setPhoneNumber("0358008828");
        user.setUseCount(0);
        list.add(user);
        UserAccount user1 = new UserAccount();
        user1.setUserName("donnq");
        user1.setFullName("Nguyen Quy Don");
        user1.setPassword("donnq");
        user1.setEmailAddress("donnq@cstcopyright.com");
        user1.setGender(true);
        user1.setExpiryDate(Date.valueOf("2020-11-11"));
        user1.setUserType(2);
        user1.setPhoneNumber("0358008828");
        user1.setUseCount(0);
        list.add(user1);
        UserAccount user2 = new UserAccount();
        user2.setUserName("minhpc");
        user2.setFullName("Pham Cong Minh");
        user2.setPassword("minhpc");
        user2.setEmailAddress("minhpc@cstcopyright.com");
        user2.setGender(true);
        user2.setExpiryDate(Date.valueOf("2019-11-11"));
        user2.setUserType(2);
        user2.setPhoneNumber("0969307911");
        user2.setUseCount(0);
        list.add(user2);
        return list;
    }
    
    public static UserAccount getUserFake(String userName, String password){
        ArrayList<UserAccount> list;
        list = fakeListUser();
        for(UserAccount user : list){
            if(user.getUserName().equals(userName) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }
    public static UserAccount findUserFake(String userName){
        List<UserAccount> list = fakeListUser();
        for(UserAccount user : list){
            if(user.getUserName().equals(userName)){
                return user;
            }
        }
        return null;
    }
    public static ArrayList<DomainScan> getDomainList(){
        ArrayList<DomainScan> list = new ArrayList<>();
        list.add(new DomainScan("45.55.13.187", "The mod_proxy module in the Apache"
                + " HTTP Server 2.4.x before 2.4.10, when a reverse proxy is enabled,"
                + " allows remote attackers to cause a denial of service (child-process crash) via a crafted HTTP Connection header."));
        list.add(new DomainScan("emin001.core-stack.com", "HTTP/1.1 500 Internal Server Error\n" +
"Date: Thu, 03 Oct 2019 05:53:38 GMT\n" +
"Server: Apache\n" +
"Connection: close\n" +
"cache-control: no-cache, no-store, must-revalidate\n" +
"X-Frame-Options: SAMEORIGIN\n" +
"Content-Length: 3065\n" +
"Status: 500 Internal Server Error\n" +
"Content-Type: text/html; charset=UTF-8"));
        return list;
    }
}
