/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSTCopyright.IDCS.servlet;

import CSTCopyright.IDCS.model.Constant;
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

/**
 *
 * @author macbook
 */
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
        dispatcher = this.getServletContext().getRequestDispatcher("/views/registration.jsp");
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
        
        boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        UserAccount user = new UserAccount();
        user.setUserName(request.getParameter("uname"));
        user.setFullName(request.getParameter("fullname"));
        user.setGender(gender);
        user.setEmailAddress(request.getParameter("email"));
        user.setPhoneNumber(request.getParameter("psphone"));
        user.setPassword(request.getParameter("passw"));
        user.setAddress(request.getParameter("address"));
        user.setUseCount(Constant.User);

        try {
            Connection conn = MyUtils.getStoredConnection(request);
            DBUtils db = new DBUtils();
            if (db.findUser(conn, user.getUserName()) != null) {
                request.setAttribute("uname", user.getUserName());
                request.setAttribute("Notif", Constant.registFail_ExitsAccount);
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/registration.jsp");
                dispatcher.forward(request, response);
            } else {
                db.requestCreateUser(conn, user);
                request.setAttribute("uname", user.getUserName());
                request.setAttribute("NotifSuc", Constant.registSuccess);
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/registration.jsp");
                dispatcher.forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
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
