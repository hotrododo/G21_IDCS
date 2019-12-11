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
import java.sql.Date;
import java.util.List;

/**
 *
 * @author mac
 */
public class DBUtils {

    //find user info with username
    public static UserAccount findUser(Connection conn, String userName) {

        String sql = "Select * from User a INNER JOIN UserInfo t"
                + " on a.username = t.username"
                + " where a.username = ? ";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, userName);

            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                //get Value from DB
                String password = rs.getString("Password");
                boolean gender = rs.getBoolean("Gender");
                String email = rs.getString("Email");
                String fName = rs.getString("FullName");
                String phone = rs.getString("PhoneNumber");
                Date exp = rs.getDate("EXPDate");
                int uType = rs.getInt("UserType");
                int useCount = rs.getInt("UseCount");
                String address = rs.getString("Address");
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
        } catch (SQLException ex) {
            return null;
        }

        return null;
    }

    public static List<UserAccount> getUsers(Connection conn) throws SQLException {
        String sql = "Select * from User a INNER JOIN UserInfo t"
                + " on a.username = t.username"
                + " where a.username = ? ";

        List<UserAccount> list = null;
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                //get Value from DB
                String userName = rs.getString("UserName");
                String password = rs.getString("Password");
                boolean gender = rs.getBoolean("Gender");
                String email = rs.getString("Email");
                String fName = rs.getString("FullName");
                String phone = rs.getString("PhoneNumber");
                Date exp = rs.getDate("EXPDate");
                int uType = rs.getInt("UserType");
                int useCount = rs.getInt("UseCount");
                String address = rs.getString("Address");
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
                list.add(user);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
        return list;
    }

}
