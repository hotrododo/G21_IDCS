/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idcs.controller;

import com.idcs.model.User;
import com.idcs.model.dal.DBUtil;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author macbook
 */
public class loginServlet extends HttpServlet {
public static final String ATT_NAME_USER_NAME = "loginedUser";

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
        RequestDispatcher dispatcher;
        dispatcher = this.getServletContext().getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
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
        processRequest(request, response);
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
            
            if(authenUser(strUsername, strPassword, u)){
                storeLoginedUser(request, session, u);
                request.setAttribute("currentUser", u);
                request.getRequestDispatcher("homepage.html").forward(request, response);
            } else{
                request.setAttribute("notif", "User or Pass wrong!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            
        
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
    
    // Store user info in Session.
    public static void storeLoginedUser(HttpServletRequest request,HttpSession session, User loginedUser) {
        // On the JSP can access via ${loginedUser}
        session = request.getSession();
        session.setMaxInactiveInterval(5*60);
        session.setAttribute(ATT_NAME_USER_NAME, loginedUser);
    }

    // Get the user information stored in the session.
    public static User getLoginedUser(HttpSession session) {
        User loginedUser = (User) session.getAttribute("loginedUser");
        return loginedUser;
    }

    // Store info in Cookie
    public static void storeUserCookie(HttpServletResponse response, User user) {
        System.out.println("Store user cookie");
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, user.getStrUsername());
        // 1 day (Converted to seconds)
        cookieUserName.setMaxAge(24 * 60 * 60);
        cookieUserName.setSecure(true);
        response.addCookie(cookieUserName);
    }

    public static String getUserNameInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (ATT_NAME_USER_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    // Delete cookie.
    public static void deleteUserCookie(HttpServletResponse response) {
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, null);
        // 0 seconds (This cookie will expire immediately)
        cookieUserName.setMaxAge(0);
        response.addCookie(cookieUserName);
    }
}
