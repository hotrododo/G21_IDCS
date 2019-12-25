/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSTCopyright.IDCS.utils;

import CSTCopyright.IDCS.controller.DataSecure;
import CSTCopyright.IDCS.model.Constant;
import CSTCopyright.IDCS.model.UserAccount;
import CSTCopyright.IDCS.model.VultModel;
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
                user.setAddress(address);
                return user;
            }
        } catch (SQLException ex) {
            return null;
        }

        return null;
    }

    public List<UserAccount> getUsers(Connection conn) throws SQLException {
        String sql = "Select * from UserInfo WHERE UserType != ?";

        ArrayList<UserAccount> list = new ArrayList<UserAccount>();
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, Constant.Admin);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                //get Value from DB
                String userName = rs.getString("UserName");
                boolean gender = rs.getBoolean("Gender");
                String email = rs.getString("Email");
                String fName = rs.getString("FullName");
                String phone = rs.getString("PhoneNumber");
                Date exp = rs.getDate("EXPDate");
                int uType = rs.getInt("UserType");
                int useCount = rs.getInt("UseCount");
                String address = rs.getString("Address");
                String status = rs.getString("Status");
                //set User value
                UserAccount user = new UserAccount();
                user.setUserName(userName);
                user.setGender(gender);
                user.setEmailAddress(email);
                user.setFullName(fName);
                user.setPhoneNumber(phone);
                user.setExpiryDate(exp);
                user.setUserType(uType);
                user.setUseCount(useCount);
                user.setAddress(address);
                user.setStatus(status);
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
    public void delActiceUser(Connection conn, String user) {
        String sql1 = "DELETE FROM User WHERE UserName like ?";
        String sql2 = "DELETE FROM UserInfo WHERE UserName like ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql2);
            ps.setString(1, user);
            ps.executeUpdate();
            ps.close();
            
            ps = conn.prepareStatement(sql1);
            ps.setString(1, user);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger("DBUtils").log(Level.SEVERE, null, ex);
        }
    }
    
    public void delRequestUser(Connection conn, String user) {
        String strSQLCommand = "DELETE FROM HandleTbl WHERE UserName like ?";
        try {
            PreparedStatement ps = conn.prepareStatement(strSQLCommand);
            ps.setString(1, user);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger("DBUtils").log(Level.SEVERE, null, ex);
        }
    }

    public static List<VultModel> GetVultData(Connection conn, String vultObj) {
        String sql = "SELECT * FROM VultTbl v WHERE v.VultObj = ? ";
        List<VultModel> list = new ArrayList<>();
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, vultObj);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
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
                + " set u.Email = ?, u.FullName = ?,"
                + " u.PhoneNumber = ?, u.EXPDate = ?, u.UseCount = ?,"
                + " u.Address = ? WHERE u.UserName = ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, user.getEmailAddress());
            pstm.setString(2, user.getFullName());
            pstm.setString(3, user.getPhoneNumber());
            pstm.setDate(4, user.getExpiryDate());
            pstm.setInt(5, user.getUseCount());
            pstm.setString(6, user.getAddress());
            pstm.setString(7, user.getUserName());

            pstm.executeUpdate();

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

            pstm.executeUpdate();

        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    public List<UserAccount> getAllAccountRequest(Connection conn) throws SQLException {
        String sql = "Select * from HandleTbl ";

        ArrayList<UserAccount> list = new ArrayList<UserAccount>();
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                //get Value from DB
                String userName = rs.getString("UserName");
                String password = rs.getString("PassWord");
                boolean gender = rs.getBoolean("Gender");
                String email = rs.getString("Email");
                String fName = rs.getString("FullName");
                String phone = rs.getString("PhoneNumber");
                int uType = rs.getInt("UserType");
                String address = rs.getString("Address");
                Date exp = rs.getDate("EXPDate");
                //set User value
                UserAccount user = new UserAccount();
                user.setUserName(userName);
                user.setPassword(password);
                user.setGender(gender);
                user.setEmailAddress(email);
                user.setFullName(fName);
                user.setPhoneNumber(phone);
                user.setUserType(uType);
                user.setAddress(address);
                user.setExpiryDate(exp);
                list.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger("DBUtils.getAccountRequest").log(Level.SEVERE, null, ex);
            return null;
        }
        return list;
    }

    public UserAccount getAccountRequest(Connection conn,String username) throws SQLException {
        String sql = "Select * from HandleTbl WHERE UserName like ?";
        UserAccount user = new UserAccount();
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, username);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                //get Value from DB
                String userName = rs.getString("UserName");
                String password = rs.getString("PassWord");
                boolean gender = rs.getBoolean("Gender");
                String email = rs.getString("Email");
                String fName = rs.getString("FullName");
                String phone = rs.getString("PhoneNumber");
                int uType = rs.getInt("UserType");
                String address = rs.getString("Address");
                Date exp = rs.getDate("EXPDate");
                //set User value
                user.setUserName(userName);
                user.setPassword(password);
                user.setGender(gender);
                user.setEmailAddress(email);
                user.setFullName(fName);
                user.setPhoneNumber(phone);
                user.setUserType(uType);
                user.setAddress(address);
                user.setExpiryDate(exp);
            }
        } catch (SQLException ex) {
            Logger.getLogger("DBUtils.getAccountRequest").log(Level.SEVERE, null, ex);
            return null;
        }
        return user;
    }
    
    public void requestForgot(Connection conn, String fullname, String email) {
        String strSQLCommand = "INSERT INTO UserInfo(Status) VALUES (?) WHERE UserInfo.Email = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(strSQLCommand);
            ps.setString(1, Constant.returnPassword);
            ps.setString(2, email);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger("DBUtils.requestForgot").log(Level.SEVERE, null, ex);
        }
    }

    public void requestCreateUser(Connection conn, UserAccount user) {

        String sql = "INSERT INTO HandleTbl(UserName,Email,FullName,PhoneNumber,UserType,Address,PassWord,EXPDate,Gender,UseCount)"
                + " VALUES (?,?,?,?,?,?,?,CURDATE(),?,?)";
        try {
            String passEncrypt = DataSecure.MD5Generate(user.getPassword());
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, user.getUserName());
            pstm.setString(2, user.getEmailAddress());
            pstm.setString(3, user.getFullName());
            pstm.setString(4, user.getPhoneNumber());
            pstm.setInt(5, Constant.User);
            pstm.setString(6, user.getAddress());
            pstm.setString(7, passEncrypt);
            pstm.setBoolean(8, user.isGender());
            pstm.setInt(9, 0);

            pstm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger("DBUtils.requestCreateUser").log(Level.SEVERE, null, ex);
        }
    }

    public void createNewAcc(Connection conn, String user) {

        String sql1 = "INSERT INTO User (UserName,Password) SELECT UserName,PassWord FROM HandleTbl WHERE UserName = ?";
        String sql2 = "INSERT INTO UserInfo (UserName,Email,FullName,PhoneNumber,Gender,EXPDate,UserType,UseCount,Address) "
                + " SELECT UserName,Email,FullName,PhoneNumber,Gender,EXPDate,UserType,1,Address "
                + " FROM HandleTbl WHERE UserName = ?";
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(sql1);
            pstm.setString(1, user);
            pstm.executeUpdate();
            pstm.close();

            pstm = conn.prepareStatement(sql2);
            pstm.setString(1, user);
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger("DBUtils.createNewAcc").log(Level.SEVERE, null, ex);
        }
    }
}
