/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSTCopyright.IDCS.controller;

import CSTCopyright.IDCS.model.Constant;
import CSTCopyright.IDCS.model.UserAccount;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mac
 */
public class DataSecure {

    public static String MD5Generate(String s) {
        String endcode = "";
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(s.getBytes());
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            endcode = bigInt.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (endcode.length() < 32) {
                endcode = "0" + endcode;
            }

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(DataSecure.class.getName()).log(Level.SEVERE, null, ex);
        }
        return endcode;
    }

    public static int totalRequest(ArrayList<UserAccount> listUser) {
        int request = 0;
        for (UserAccount user : listUser) {
            if (user.getStatus() != null) {
                if (user.getStatus().equals(Constant.returnPassword)) {
                    request++;
                }
            }
        }
        return request;
    }
}
