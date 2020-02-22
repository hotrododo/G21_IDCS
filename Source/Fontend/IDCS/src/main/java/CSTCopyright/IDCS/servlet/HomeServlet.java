/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSTCopyright.IDCS.servlet;

import CSTCopyright.IDCS.model.UserAccount;
import CSTCopyright.IDCS.model.MyObject;
import CSTCopyright.IDCS.utils.DBUtils;
import CSTCopyright.IDCS.utils.MyUtils;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

/**
 *
 * @author mac
 */
public class HomeServlet extends HttpServlet {


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
//        ScanModel latestRecord = DBUtils.getLatestScanRecord(conn, loginedUser.getUserName());
//        ScanModel mostRecord = DBUtils.getMostScanRecord(conn, loginedUser.getUserName());
//        request.setAttribute("latestRecord", latestRecord);
//        request.setAttribute("mostRecord", mostRecord);
        //init error variable
//        String errorMess = new String();
//        errorMess = (String)request.getAttribute(errorMess);
//        request.set

        //scan history
        // Store info to the request attribute before forwarding.
        request.setAttribute("user", loginedUser);
        //init const
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
        // Not logged in
        if (loginedUser == null) {
            // Redirect to login page.
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        MyObject mobj = DBUtils.GetHost(target, loginedUser.getUserName());
        if (mobj.getCode() == 201) {
//            201 mean task created. Display not direct, show dialog

        } else {
            JSONObject host = mobj.getObj();
            JSONObject ports = host.getJSONObject("ports");
            List<Integer> int_list = ports.keySet().stream().map(Integer::parseInt).collect(Collectors.toList());
            Collections.sort(int_list);
            List<String> list_port = int_list.stream().map(Object::toString).collect(Collectors.toList());
            request.setAttribute("host", host);
            request.setAttribute("list_port", list_port);
//            RequestDispatcher dispatcher //
//                        = this.getServletContext().getRequestDispatcher("/result");
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/resultView.jsp");
            dispatcher.forward(request, response);
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
