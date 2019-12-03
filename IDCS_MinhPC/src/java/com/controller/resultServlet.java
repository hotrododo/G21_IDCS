/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.idcs.model.Scan;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author macbook
 */
public class resultServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            InputStream input = new FileInputStream("../lib/scan.json");

            // Lấy ra đối tượng JsonReader từ class Json
            JsonReader jsonReader = Json.createReader(input);
            // Lấy ra đối tượng JsonObject mô tả dữ liệu JSON.
            JsonObject rootJSON = jsonReader.readObject();
            // Close resources
            jsonReader.close();
            input.close();

            // Tạo đối tượng Company rỗng.
            Scan scan = new Scan();

            JsonObject addressesJSON = rootJSON.getJsonObject("addresses");
            scan.setS_ID(request.getParameter("IP"));
            scan.setIPv4(addressesJSON.getString("ipv4"));

            JsonObject hostJSON = rootJSON.getJsonObject("hostnames");
            scan.setNETNAME(hostJSON.getString("name"));

            JsonObject ddnsJSON = rootJSON.getJsonObject("hostscript");
            scan.setDDNS(ddnsJSON.getString("output"));

            // Đọc thông tin mảng các website trong JSON.
//        JsonArray jsonArray = rootJSON.getJsonArray("websites");
//        String[] websites = new String[jsonArray.size()];
//        int index = 0;
//        for (JsonValue value : jsonArray) {
//            websites[index++] = value.toString();
//        }
//        company.setWebsites(websites);
            // Lấy ra đối tượng JsonObject nằm trong đối tượng json.
//        JsonObject addressJSON = rootJSON.getJsonObject("address");
//        Address address = new Address();
//        address.setStreet(addressJSON.getString("street"));
//        address.setCity(addressJSON.getString("city"));
//
//        company.setAddress(address);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
