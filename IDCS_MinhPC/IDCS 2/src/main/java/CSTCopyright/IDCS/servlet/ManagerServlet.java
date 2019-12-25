/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSTCopyright.IDCS.servlet;

import CSTCopyright.IDCS.controller.DataSecure;
import CSTCopyright.IDCS.model.Constant;
import CSTCopyright.IDCS.model.UserAccount;
import CSTCopyright.IDCS.utils.DBUtils;
import CSTCopyright.IDCS.utils.MyUtils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author macbook
 */
@WebServlet(name = "manager", urlPatterns = {"/manager"})
public class ManagerServlet extends HttpServlet {

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
        DBUtils db = new DBUtils();
        Connection conn = null;
        int requestPass;
        // Check User has logged on
        UserAccount loginedUser = MyUtils.getLoginedUser(session);

        // Not logged in
        if (loginedUser == null) {
            // Redirect to login page.
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        } else if (loginedUser.getUserType() == Constant.User) {
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/homeView.jsp");
            dispatcher.forward(request, response);
        } else {
            try {
                conn = MyUtils.getStoredConnection(request);
                ArrayList<UserAccount> listUser = (ArrayList<UserAccount>) db.getUsers(conn);
                requestPass = DataSecure.totalRequest(listUser);
                ArrayList<UserAccount> userRequestCreate = (ArrayList<UserAccount>) db.getAllAccountRequest(conn);

                if (requestPass != Constant.User && request != null) {
                    request.setAttribute("requestPass", requestPass);
                    request.setAttribute("total", userRequestCreate.size() + requestPass);
                } else {
                    request.setAttribute("requestPass", Constant.User);
                    request.setAttribute("total", userRequestCreate.size());
                }

                request.setAttribute("requestCreate", userRequestCreate.size());
                request.setAttribute("listUser", listUser);
                request.setAttribute("user", loginedUser);
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/adminHomePage.jsp");
                dispatcher.forward(request, response);

            } catch (SQLException e) {
            }
        }
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
        String submit = request.getParameter("btn");
        String expiry = request.getParameter("expiryDate");

        DBUtils db = new DBUtils();
        try {
            user.setUserName(request.getParameter("username"));
            Date date1 = Date.valueOf(expiry);
            user.setFullName(request.getParameter("fullname"));
            user.setPhoneNumber(request.getParameter("phone"));
            user.setEmailAddress(request.getParameter("email"));
            user.setAddress(request.getParameter("address"));
            user.setUseCount(Integer.parseInt(request.getParameter("usecount")));
            user.setExpiryDate(date1);
            //get connect to db
            Connection conn = MyUtils.getStoredConnection(request);
            if (submit.equals(Constant.update)) {
                DBUtils.updateUser(conn, user);
                System.out.println("updateeeeee");
            } else if (submit.equals(Constant.dele_Yes)) {
                db.delActiceUser(conn, user.getUserName());
                System.out.println("dele");
            }
            response.sendRedirect(request.getContextPath() + "/manager");
        } catch (Exception ex) {
            System.out.println(ex);
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
