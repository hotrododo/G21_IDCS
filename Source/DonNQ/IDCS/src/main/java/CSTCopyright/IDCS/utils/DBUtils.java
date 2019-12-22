/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSTCopyright.IDCS.utils;

import CSTCopyright.IDCS.controller.UserAccount;
import CSTCopyright.IDCS.controller.VultModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
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
    
    public static List<VultModel> GetVultData(Connection conn, String vultObj){
        String sql = "SELECT * FROM VultTbl v WHERE v.VultObj = ? ";
        List<VultModel> list = new ArrayList<>();
        try{
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, vultObj);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                String vultDes = rs.getString("VultDes");
                String vultRem = rs.getString("VultRem");
                String vultNote = rs.getString("VultNote");
                int vultID = rs.getInt("VultID");
                list.add(new VultModel(vultID, vultObj, vultDes, vultRem, vultNote));
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
        return list;
    }
    
    public static boolean updateUser(Connection conn, UserAccount user) {

        String sql = "Update UserInfo u"
                + " set u.Gender = ?, u.Email = ?, u.FullName = ?,"
                + " u.PhoneNumber = ?, u.EXPDate = ?, u.UserType = ?,"
                + " u.Address = ? WHERE u.UserName = ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setBoolean(1, user.isGender());
            pstm.setString(2, user.getEmailAddress());
            pstm.setString(3, user.getFullName());
            pstm.setString(4, user.getPhoneNumber());
            pstm.setDate(5, user.getExpiryDate());
            pstm.setInt(6, user.getUserType());
            pstm.setString(7, user.getAddress());
            pstm.setString(8, user.getUserName());

            int rs = pstm.executeUpdate();

        } catch (SQLException ex) {
            return false;
        }

        return true;
    }
    
    public static boolean updateUserPassword(Connection conn, UserAccount user) {

        String sql = "Update User u"
                + " set u.Password = ? WHERE u.UserName = ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, user.getPassword());
            pstm.setString(2, user.getUserName());

            int rs = pstm.executeUpdate();

        } catch (SQLException ex) {
            return false;
        }

        return true;
    }
    

}