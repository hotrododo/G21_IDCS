/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSTCopyright.IDCS.servlet;

import CSTCopyright.IDCS.controller.DomainScan;
import CSTCopyright.IDCS.controller.JsonServices;
import CSTCopyright.IDCS.data.ForgeData;
import CSTCopyright.IDCS.model.PortModel;
import CSTCopyright.IDCS.model.ScanModel;
import CSTCopyright.IDCS.model.ServiceModel;
import CSTCopyright.IDCS.model.UserAccount;
import CSTCopyright.IDCS.model.VultModel;
import CSTCopyright.IDCS.services.ScanServices;
import CSTCopyright.IDCS.utils.ConnectionUtils;
//import CSTCopyright.IDCS.utils.FBUtils;
import CSTCopyright.IDCS.utils.MyUtils;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class HomeServlet extends HttpServlet {
    
     private boolean checkPort = false;

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
        //scan history
        ArrayList<DomainScan> list = (ArrayList)ForgeData.getDomainList();

        // Store info to the request attribute before forwarding.
        request.setAttribute("user", loginedUser);
        request.setAttribute("list", list);
        List<ServiceModel> services = new ArrayList<>();
        session.setAttribute("services", services);
        List<VultModel> listVult = new ArrayList<>();
        session.setAttribute("listVult", listVult);
        // If the user has logged in, then forward to the page
        // /WEB-INF/views/userInfoView.jsp
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/homeView.jsp");
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
        String target = request.getParameter("frmDomain");
        HttpSession session = request.getSession();

        // Check User has logged on
        UserAccount loginedUser = MyUtils.getLoginedUser(session);
        ScanServices scan = new ScanServices();
        // Get socket has stored
        Socket socket = MyUtils.getSocketConnection(session);
        //create socket
        if(socket==null){
            try {
                socket = ConnectionUtils.getSocket();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
            }            
            MyUtils.storeSocketConnection(session, socket);
        }
        //Get host info
        String data = scan.dataTransfer(target, socket);
        JsonServices ser = new JsonServices();
        //Extract info from raw data
        ScanModel host = ser.ExtractHostInfo(data, target, loginedUser);
        //Get port info
        data = scan.dataTransfer("+", socket);
        List<PortModel> listPorts = ser.ExtractPortInfo(data, target, "admin");
        //store host and port info to session
        session.setAttribute("host", host);
        session.setAttribute("ports", listPorts);
        
        RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/result");
        
                dispatcher.forward(request, response);
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
