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

/**
 *
 * @author mac
 */
public class ScanServices {

   
    private BufferedWriter os = null;
    private BufferedReader is = null;

    
    // gửi data tới server và nhận lại dữ liệu.
    // Nếu return không phải string data thì kết nối failed
    public String dataTransfer(final String str, Socket socketOfClient) {
        String mess = "";
        try {
            // Tạo luồng đầu ra tại client (Gửi dữ liệu tới server)
            os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));

            // Luồng đầu vào tại Client (Nhận dữ liệu từ server).
            is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));

            os.write(str);
            os.newLine();
            os.flush();
            String responseLine;
            while (!"[END-PCK]".equals((responseLine = is.readLine()))) {
                System.out.println("Server:" + responseLine);
                if(responseLine != null && !responseLine.equals("null")) mess += (responseLine);
            }
            if(os != null) os = null;
            if(is != null) is= null;
        } catch (final IOException e) {
            System.err.println("Couldn't get I/O for the connection to server");
            return "-1";
        } 
        return mess;
    }

    //Đóng kết nối tới server
    //return true: đóng được kết nối
    //return false: không thể đóng kết nối.
    public boolean closeConn(Socket socketOfClient) throws IOException {
        if (socketOfClient != null) {
            os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));
            is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));
            os.write("Quit");
            if (is.readLine().equals("OK")) {
                socketOfClient.close();
                socketOfClient = null;
                return true;
            }
        }
        return false;
    }

}
