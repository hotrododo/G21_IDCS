/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSTCopyright.IDCS.servlet;

import CSTCopyright.IDCS.controller.PortModel;
import CSTCopyright.IDCS.controller.ScanModel;
import CSTCopyright.IDCS.controller.ServiceModel;
import CSTCopyright.IDCS.controller.UserAccount;
import CSTCopyright.IDCS.utils.ConnectionUtils;
import CSTCopyright.IDCS.utils.DBUtils;
import CSTCopyright.IDCS.utils.MyUtils;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ViewHistoryServlet extends HttpServlet {

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
        Socket socket = new Socket();
        try {
            socket = ConnectionUtils.getSocket();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        MyUtils.storeSocketConnection(session, socket);

        String target = request.getParameter("history");
        Connection conn = MyUtils.getStoredConnection(request);
        ScanModel scan = new ScanModel();
        scan.setU_ID(loginedUser.getUserName());
        scan.setTARGET(target);
        //get target
        scan = DBUtils.findLatestScanByTarget(conn, scan);

        List<PortModel> ports = DBUtils.findListPorts(conn, scan.getS_ID());
        List<ServiceModel> services = DBUtils.findListService(conn, scan.getS_ID());
        request.setAttribute("host", scan);
        request.setAttribute("ports", ports);
        request.setAttribute("services", services);
        session.setAttribute("host", scan);
        session.setAttribute("ports", ports);
        session.setAttribute("services", services);
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/resultView.jsp");
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
