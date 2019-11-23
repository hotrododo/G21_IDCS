/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idcs.model.dal;

import com.idcs.model.dal.DBContext;
import com.idcs.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author VanPN
 */
public class DBUtil {

    public User findUser(String userName) {
        String strSQLCommand = "SELECT * from user where username like ?";
        try {
            Connection connection = DBContext.getInstance().GetConnection();
            PreparedStatement ps = connection.prepareStatement(strSQLCommand);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setStrUsername(rs.getString("username"));
                user.setStrPassword(rs.getString("password"));
                user = getUserInfo(user);
                return user;
            }
        } catch (Exception ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private User getUserInfo(User user) {

        String strSQLCommand = "SELECT * from userinfo where username like ?";
        try {
            Connection connection = DBContext.getInstance().GetConnection();
            PreparedStatement ps = connection.prepareStatement(strSQLCommand);
            ps.setString(1, user.getStrUsername());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user.setStrFullname(rs.getString("fullname"));
                user.setStrEmail(rs.getString("email"));
                user.setStraddress(rs.getString("address"));
                user.setbGender(rs.getString("gender"));
                user.setStrPhone(rs.getString("phone"));
                user.setExpiryDate(rs.getDate("expeirydate"));
                user.setnUserType(rs.getInt("usertype"));
            }
        } catch (Exception ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
}
