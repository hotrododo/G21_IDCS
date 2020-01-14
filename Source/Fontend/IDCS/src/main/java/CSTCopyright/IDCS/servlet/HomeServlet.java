/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSTCopyright.IDCS.servlet;

import CSTCopyright.IDCS.controller.ScanModel;
import CSTCopyright.IDCS.controller.ServiceModel;
import CSTCopyright.IDCS.model.UserAccount;
import CSTCopyright.IDCS.controller.VultModel;
import CSTCopyright.IDCS.utils.DBUtils;
import CSTCopyright.IDCS.utils.MyUtils;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
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
        Connection conn = MyUtils.getStoredConnection(request);
        ScanModel latestRecord = DBUtils.getLatestScanRecord(conn, loginedUser.getUserName());
        ScanModel mostRecord = DBUtils.getMostScanRecord(conn, loginedUser.getUserName());
        request.setAttribute("latestRecord", latestRecord);
        request.setAttribute("mostRecord", mostRecord);
        //init error variable
//        String errorMess = new String();
//        errorMess = (String)request.getAttribute(errorMess);
//        request.set

        //scan history

        // Store info to the request attribute before forwarding.
        request.setAttribute("user", loginedUser);
        //init const
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
//        String target = request.getParameter("frmDomain");
//        HttpSession session = request.getSession();
//
//        // Check User has logged on
//        UserAccount loginedUser = MyUtils.getLoginedUser(session);
//        ScanServices scan = new ScanServices();
//        // Get socket has stored
//        String packet = "{\"target\":\"" + target +"\",\"port\":22,\"mode\":0}";
//        //Get host info
//        String data = scan.dataTransfer(packet, socket);
//        if (DataHandle.hostHasUp(data)) {
//            if (DataHandle.hostHasSign(data)) {
//                JsonServices ser = new JsonServices();
//                //Extract info from raw data
//                ScanModel host = ser.ExtractHostInfo(data, target, loginedUser);
//                Connection conn = MyUtils.getStoredConnection(request);
//                ScanModel scanstamp = DBUtils.findScanByDateScan(conn, host);
//                if (scanstamp.getS_ID() == null) {
//                    if (DBUtils.addScan(conn, host)) {
//                        System.out.println("Host data saved");
//                    } else {
//                        System.out.println("Data failed");
//                    }
//                } else {
//                    if (DBUtils.updateScan(conn, host)) {
//                        System.out.println("Host data updated");
//                    } else {
//                        System.out.println("Data change failed");
//                    }
//                }
//                ScanModel hostStamp = DBUtils.findScanByDateScan(conn, host);
//                //Get port info
//                packet = packet.replaceFirst("0", "1");
//                data = scan.dataTransfer(packet, socket);
//                List<PortModel> listPorts = ser.ExtractPortInfo(data, target, hostStamp.getS_ID());
//                listPorts.stream().filter((p) -> (p != null)).forEachOrdered((p) -> {
//                    PortModel portstamp = DBUtils.findPort(conn, p);
//                    if (portstamp.getS_ID() == null) {
//                        if (DBUtils.addPort(conn, p)) {
//                            System.out.println("Add a port: " + p.getPORTNUM());
//                        } else {
//                            System.out.println("Cannot add port " + p.getPORTNUM());
//                        }
//                    } else {
//                        System.out.println("Port have ready");
//                    }
//                });
//                //store host and port info to session
//                session.setAttribute("host", hostStamp);
//                session.setAttribute("ports", listPorts);
//
//                RequestDispatcher dispatcher //
//                        = this.getServletContext().getRequestDispatcher("/result");
//
//                dispatcher.forward(request, response);
//            } else {
//                String errorMess = "Host has not registered information";
//                request.setAttribute("errorMess", errorMess);
//                processRequest(request, response);
//            }
//        } else {
//            String errorMess = "Host seem down or not public!";
//            request.setAttribute("errorMess", errorMess);
//            processRequest(request, response);
//        }

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
