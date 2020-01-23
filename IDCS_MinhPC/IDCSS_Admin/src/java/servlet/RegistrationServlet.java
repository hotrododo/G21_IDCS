/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Constant;
import model.UserAccount;

/**
 *
 * @author macbook
 */
@WebServlet(name = "register", urlPatterns = {"/register"})
public class RegistrationServlet extends HttpServlet {

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
        dispatcher = this.getServletContext().getRequestDispatcher("/registration.jsp");
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
        UserAccount user = new UserAccount();
        user.setUserName(request.getParameter("account"));
        user.setFullName(request.getParameter("fullname"));
        user.setEmailAddress(request.getParameter("email"));
        user.setPhoneNumber(request.getParameter("phoneNumDialog"));
        user.setPassword(request.getParameter("password"));
        String repass = request.getParameter("repeatPass");
        String address = request.getParameter("address");
        String gender = request.getParameter("gender");

        try {
            //check user if exist
            
            
//            if (db.findUser(conn, user.getUserName()) != null) {
//                request.setAttribute("uname", user.getUserName());
//                request.setAttribute("Notif", Constant.registFail_ExitsAccount);
//                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/registration.jsp");
//                dispatcher.forward(request, response);
//            } else {
////                db.(conn, user);
//                request.setAttribute("Notif", Constant.registSuccess);
//                response.sendRedirect(request.getContextPath() + "/login.jsp");
//            }

        } catch (Exception e) {

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

}
