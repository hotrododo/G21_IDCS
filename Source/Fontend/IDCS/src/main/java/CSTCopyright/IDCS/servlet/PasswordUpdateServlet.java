/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSTCopyright.IDCS.servlet;

import CSTCopyright.IDCS.model.UserAccount;
import CSTCopyright.IDCS.utils.DBUtils;
import CSTCopyright.IDCS.utils.MyUtils;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mac
 */
public class PasswordUpdateServlet extends HttpServlet {

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
        HttpSession session = request.getSession();

        // Check User has logged on
        UserAccount loginedUser = MyUtils.getLoginedUser(session);

        // Not logged in
        if (loginedUser == null) {
            // Redirect to login page.
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        String oldpassword = (String)request.getParameter("txt-old-pass");
        String newpassword = (String)request.getParameter("txt-new-pass");
        String repassword = (String)request.getParameter("txt-re-pass");
        UserAccount user = loginedUser;
        String mess = "";
        boolean hasError = false;
//        if(!user.getPassword().equals(DataSecure.MD5Generate(oldpassword))){
//            mess = "Wrong Password!";
//            hasError = true;
//        } else if(!newpassword.equals(repassword)){
//            mess = "New Password Not Match! Attention: Remember your new Password!";
//            hasError = true;
//        }
//        if(!hasError){
//            user.setPassword(DataSecure.MD5Generate(newpassword));
//            Connection conn = MyUtils.getStoredConnection(request);
//            boolean isUpdated = DBUtils.updateUserPassword(conn, user);
//            if(isUpdated){
//                mess = "Password Update Successful!";
//            }else mess = "Failed to Change Password!";
//        }
        request.setAttribute("mess", mess);
        request.setAttribute("user", user);
        request.setAttribute("uds", "5"); //set user-display-state = 1
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/userInfoView.jsp");
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
