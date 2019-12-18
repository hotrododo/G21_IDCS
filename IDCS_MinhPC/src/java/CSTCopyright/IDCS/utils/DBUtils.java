/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSTCopyright.IDCS.utils;

import CSTCopyright.IDCS.model.UserAccount;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mac
 */
public class DBUtils {
    //---------Donnq------------//
    //find user info with username
    public UserAccount findUser(Connection conn, String userName) {

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

    public List<UserAccount> getUsers(Connection conn) throws SQLException {
        String sql = "Select * from UserInfo a INNER JOIN User t"
                + " on a.UserName = t.UserName";

        ArrayList<UserAccount> list = new ArrayList<UserAccount>();
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
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
    
    //----------End Donnq----------//
    
    //----------MinhPC------------//
    

    public void register(Connection conn,UserAccount user) {
        String strSQLCommand = "INSERT INTO User(username,password) VALUES (?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(strSQLCommand);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger("DBUtils").log(Level.SEVERE, null, ex);
        }
    }

    public void delUser(Connection conn,String user) {
        String strSQLCommand = "DELETE User WHERE username like ?";
        try {
            PreparedStatement ps = conn.prepareStatement(strSQLCommand);
            ps.setString(1, user);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger("DBUtils").log(Level.SEVERE, null, ex);
        }
    }

    public void changeInfoUser(Connection conn,UserAccount user) {
        String strSQLCommand = "UPDATE UserInfo SET fullname = ?, email = ?, phone = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(strSQLCommand);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getEmailAddress());
            ps.setString(3, user.getPhoneNumber());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger("DBUtils").log(Level.SEVERE, null, ex);
        }
    }

    public void changePassword(Connection conn,String pass) {
        String strSQLCommand = "UPDATE User SET password = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(strSQLCommand);
            ps.setString(1, pass);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger("DBUtils").log(Level.SEVERE, null, ex);
        }
    }
}
