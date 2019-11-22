/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idcs.model.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author macbook
 */
public class DBContext {

    private static volatile DBContext instance;
    private Connection connection = null;

    private DBContext() {
        try {
            String username = "admin";
            String password = "111111";
            //String url = "jdbc:mysql://34.84.62.87:3306/idcs";
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://54.199.156.1:3306/idcs";
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception ex) {
            Logger.getLogger("DBContext.GetConnection").log(Level.SEVERE, null, ex);
        }
    }

    public static DBContext getInstance() {
        if (instance == null) {
            // Do the task too long before create instance ...
            // Block so other threads cannot come into while initialize
            synchronized (DBContext.class) {
                // Re-check again. Maybe another thread has initialized before
                if (instance == null) {
                    instance = new DBContext();
                }
            }
        }
        return instance;
    }

    public Connection GetConnection() throws Exception {
        return connection;
    }

    public void closeConnection(Connection con) throws Exception {
        if ((!con.isClosed()) && (con != null)) {
            con.close();
        }
    }

    public void closeStatement(PreparedStatement statement) throws Exception {
        if ((!statement.isClosed()) && (statement != null)) {
            statement.close();
        }
    }

    public void closeResultSet(ResultSet rs) throws Exception {
        if ((!rs.isClosed()) && (rs != null)) {
            rs.close();
        }
    }
}
