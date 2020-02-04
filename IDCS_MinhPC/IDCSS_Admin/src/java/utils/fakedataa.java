/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.UserAccount;

/**
 *
 * @author macbook
 */
public class fakedataa {

    public static ArrayList<UserAccount> listUser() {
        ArrayList<UserAccount> listUser = new ArrayList<>();
        UserAccount user = new UserAccount();
        Date dateConverted = null;
        String dateNeedConvert;

        user.setUserName("donnq");
        user.setFullName("Nguyen Quy Don");
        user.setEmailAddress("donnq@gmail.com");
        user.setPhoneNumber("0914258794");
        user.setAddress("Quang Ninh, Viet Nam");
        dateNeedConvert = "03/12/2030";
        converDate(dateNeedConvert, dateConverted);
        user.setExpiryDate(dateConverted);
        user.setGender(true);
        listUser.add(user);
        
        dateConverted = null;
        user = new UserAccount();
        user.setUserName("minhpc");
        user.setFullName("Pham Cong Minh");
        user.setEmailAddress("minhpc@gmail.com");
        user.setPhoneNumber("0976543123");
        user.setAddress("Ninh Binh, Viet Nam");
        dateNeedConvert = "01/30/2050";
        converDate(dateNeedConvert, dateConverted);
        user.setExpiryDate(dateConverted);
        user.setGender(true);
        listUser.add(user);

        dateConverted = null;
        user = new UserAccount();
        user.setUserName("tuannq");
        user.setFullName("Nguyen Quy Tuan");
        user.setEmailAddress("tuannq@gmail.com");
        user.setPhoneNumber("09xxxxxxxx");
        user.setAddress("Ha Noi, Viet Nam");
        dateNeedConvert = "22/02/2022";
        converDate(dateNeedConvert, dateConverted);
        user.setExpiryDate(dateConverted);
        user.setGender(true);
        listUser.add(user);
        
        return listUser;
    }

    private static void converDate(String dateNeedConvert, Date dateConverted) {
        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
            java.util.Date date = sdf1.parse(dateNeedConvert);
            dateConverted = new java.sql.Date(date.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(fakedataa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
