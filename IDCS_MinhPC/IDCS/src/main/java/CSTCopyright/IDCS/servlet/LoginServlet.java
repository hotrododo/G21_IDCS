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
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

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
        String a = getServletContext().getRealPath("/");
        // Forward to /views/loginView.jsp
        // (Users can not access directly into JSP pages placed in views)
        RequestDispatcher dispatcher;
        dispatcher = this.getServletContext().getRequestDispatcher("/views/loginView.jsp");

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
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String pass = DataSecure.MD5Generate(password);
        
        String rememberMeStr = request.getParameter("rememberMe");
        boolean remember = "Y".equals(rememberMeStr);

        UserAccount user = null;
        boolean hasError = false;
        int userType;
        String errorString = request.getParameter("errorString");
        DBUtils db = new DBUtils();
        
        if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = Constant.errorlogin2;
        } else {
            Connection conn = MyUtils.getStoredConnection(request);
            // Find the user in the DB.
            user = db.findUser(conn, userName);
            if (user == null || !user.getPassword().equals(pass)) {
                hasError = true;
                errorString = Constant.errorlogin1;
            }
        }
        // If error, forward to /views/login.jsp
        if (hasError) {
            user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);

            // Store information in request attribute, before forward.
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);

            // Forward to views/loginView.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/views/loginView.jsp");

            dispatcher.forward(request, response);
        } // If no error
        // Store user information in Session
        // And redirect to userInfo page.
        else {
            HttpSession session = request.getSession();
            MyUtils.storeLoginedUser(session, user);

            // If user checked "Remember me".
            if (remember) {
                MyUtils.storeUserCookie(response, user);
            } // Else delete cookie.
            else {
                MyUtils.deleteUserCookie(response);
            }
            userType = user.getUserType();
            // Redirect to userInfo page.
            if (userType == Constant.Admin) {
                response.sendRedirect(request.getContextPath() + "/manager");
            } else {
                response.sendRedirect(request.getContextPath() + "/home");
            }
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
