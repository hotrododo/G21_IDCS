/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSTCopyright.IDCS.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author mac
 */
public class ScanServices {
    private final String serverHost = "localhost";
    private final int serverPort = 12017;
    private Socket socketOfClient = null;
    private BufferedWriter os = null;
    private BufferedReader is = null;
    
    // Khởi tạo kết nối tới server
    public boolean initConn() throws IOException {
        try {
            socketOfClient = new Socket(serverHost, 9999);
        } catch (final UnknownHostException e) {
            System.err.println("Don't know about host " + serverHost);
            return false;
        }
        return true;
    }
    
    // gửi data tới server và nhận lại dữ liệu.
    // Nếu return không phải string data thì kết nối failed
    public String dataTransfer(final String str) throws IOException {
        String mess = null;
        try {
            // Tạo luồng đầu ra tại client (Gửi dữ liệu tới server)
            os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));

            // Luồng đầu vào tại Client (Nhận dữ liệu từ server).
            is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));

            os.write(str);
            while (is.readLine() != null) {
                mess += is.readLine();
            }
        } catch (final IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + serverHost);
            return "-1";
        }
        os.close();
        is.close();
        return mess;
    }
    
    //Đóng kết nối tới server
    //return true: đóng được kết nối
    //return false: không thể đóng kết nối.
    public boolean closeConn() throws IOException{
        if(socketOfClient != null){
            os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));
            is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));
            os.write("Quit");
            if(is.readLine().equals("OK")){
                socketOfClient.close();
                socketOfClient = null;
                return true;
            }
        }
        return false;
    }

}
