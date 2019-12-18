/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSTCopyright.IDCS.utils;

import static CSTCopyright.IDCS.utils.MySQLConnUtils.getMySQLConnection;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mac
 */
public class ClientSocketUtils {
    public static Socket getMySQLConnection()
            throws ClassNotFoundException, SQLException {
        // Chú ý: Thay đổi các thông số kết nối cho phù hợp.
        String serverHost = "localhost";
        int serverPort = 12017;
        int buffSize = 4096;
        return getMySQLConnection(serverHost, serverPort, buffSize);
    }
    private static Socket getMySQLConnection(String serverHost, int serverPort, int buffSize){
        Socket socketOfClient = null;
        try {
            socketOfClient = new Socket(serverHost, serverPort);
            socketOfClient.setReceiveBufferSize(4096);
        } catch (final UnknownHostException e) {
            System.err.println("Don't know about host " + serverHost);
        } catch (IOException ex) {
            Logger.getLogger(ClientSocketUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return socketOfClient;
    }
}
