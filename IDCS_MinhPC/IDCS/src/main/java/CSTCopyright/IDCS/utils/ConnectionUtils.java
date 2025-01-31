/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSTCopyright.IDCS.utils;

import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author mac
 */
public class ConnectionUtils {

    public static Connection getConnection()
            throws ClassNotFoundException, SQLException {
         return MySQLConnUtils.getMySQLConnection();
    }

    public static void closeQuietly(Connection conn) {
        try {
            conn.close();
        } catch (Exception e) {
        }
    }

    public static void rollbackQuietly(Connection conn) {
        try {
            conn.rollback();
        } catch (Exception e) {
        }
    }
    public static Socket getSocket() 
            throws ClassNotFoundException, SQLException {
        return ClientSocketUtils.getMySQLConnection();
    }
}
