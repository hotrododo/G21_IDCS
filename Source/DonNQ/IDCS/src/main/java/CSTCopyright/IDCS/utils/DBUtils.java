/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSTCopyright.IDCS.utils;

import CSTCopyright.IDCS.controller.UserAccount;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mac
 */
public class DBUtils {

    //find User with uName & uPassword
    public static UserAccount findUser(Connection conn, //
            String userName, String password) throws SQLException {
        String sql = "Select ... from User_Account a INNER JOIN User_Type t"
                + " on a.User_Name = t.User_Name INNER JOIN User_EXP e"
                + " on a.User_Name = e.User_Name"
                + " where a.User_Name = ? and a.password= ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            //get Value from DB
            boolean gender = rs.getBoolean("Gender");
            String email = rs.getString("Email");
            String fName = rs.getString("Full_Name");
            String phone = rs.getString("Phone_Number");
            int exp = rs.getInt("EXP_Date");
            int uType = rs.getInt("User_Type");
            int useCount = rs.getInt("Use_Count");
            //set User value
            UserAccount user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);
            user.setGender(gender);
            user.setEmailAddress(email);
            user.setFullName(fName);
            user.setPhoneNumber(phone);
            user.setExpiryDate(exp);
            user.setUserType(uType);
            user.setUseCount(useCount);
            return user;
        }
        return null;
    }
    
    public static UserAccount findUser(Connection conn, String userName) throws SQLException {
 
        String sql = "Select ... from User_Account a INNER JOIN User_Type t"
                + " on a.User_Name = t.User_Name INNER JOIN User_EXP e"
                + " on a.User_Name = e.User_Name"
                + " where a.User_Name = ? ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
 
        ResultSet rs = pstm.executeQuery();
 
        if (rs.next()) {
            //get Value from DB
            String password = rs.getString("Password");
            boolean gender = rs.getBoolean("Gender");
            String email = rs.getString("Email");
            String fName = rs.getString("Full_Name");
            String phone = rs.getString("Phone_Number");
            int exp = rs.getInt("EXP_Date");
            int uType = rs.getInt("User_Type");
            int useCount = rs.getInt("Use_Count");
            //set User value
            UserAccount user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);
            user.setGender(gender);
            user.setEmailAddress(email);
            user.setFullName(fName);
            user.setPhoneNumber(phone);
            user.setExpiryDate(exp);
            user.setUserType(uType);
            user.setUseCount(useCount);
            return user;
        }
        return null;
    }
    
}
