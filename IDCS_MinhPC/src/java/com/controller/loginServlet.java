/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.idcs.model.User;
import com.idcs.model.dal.DBUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author macbook
 */
public class loginServlet extends HttpServlet {

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
        HttpSession session=request.getSession();  
        if (session.isNew()) {
            return;
        }else{
            response.sendRedirect("home.jsp");
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
        String strUsername = request.getParameter("uname");
        String strPassword = request.getParameter("pswd");
        //Clear old session
        HttpSession session = request.getSession();
        session.invalidate();
        User u = null;

        if (authenUser(strUsername, strPassword, u)) {
            session = request.getSession(true);  
            session.setAttribute("user", strUsername);
            request.getRequestDispatcher("resultServlet.jsp").forward(request, response);
        } else {
            request.setAttribute("uname", strUsername);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        
        
    }
    
    private boolean authenUser(String user, String password, User u){
        DBUtil dbu = new DBUtil();
        u = dbu.findUser(user);
        if(u != null){
            if(u.getStrPassword().compareTo(password) == 0){
                System.out.println(u);
                return true;
            }
        }
        return false;
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
