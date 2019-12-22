/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSTCopyright.IDCS.servlet;

//import CSTCopyright.IDCS.controller.GoogleResults;
import CSTCopyright.IDCS.controller.JsonServices;
import CSTCopyright.IDCS.model.PortModel;
import CSTCopyright.IDCS.model.ScanModel;
import CSTCopyright.IDCS.model.ServiceModel;
import CSTCopyright.IDCS.model.UserAccount;
import CSTCopyright.IDCS.model.VultModel;
import CSTCopyright.IDCS.services.ScanServices;
import CSTCopyright.IDCS.utils.DBUtils;
import CSTCopyright.IDCS.utils.MyUtils;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mac
 */
@WebServlet(name = "result", urlPatterns = {"/result"})
public class ResultServlet extends HttpServlet {

    private final boolean checkPort = false;

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
        //load data from session
        ScanModel host = (ScanModel) session.getAttribute("host");
        List<PortModel> ports = (List<PortModel>) session.getAttribute("ports");
        Socket socket = MyUtils.getSocketConnection(session);
        ScanServices scan = new ScanServices();
        // Store info to the request attribute before forwarding.
        request.setAttribute("user", loginedUser);
        request.setAttribute("ports", ports);
        request.setAttribute("host", host);
        //try to get services from session
        List<ServiceModel> services = (List<ServiceModel>) session.getAttribute("services");
        List<VultModel> list = (List<VultModel>) session.getAttribute("listVult");
        String port = (String) request.getParameter("port");
        if (port != null) {
            JsonServices ser = new JsonServices();

            String data = scan.dataTransfer(port, socket);
            ServiceModel serv = ser.ExtractServiceInfo(data, host.getTARGET(), port, "admin");
            if (serv != null) {
                boolean hasService = false;
                for (int i = 0; i < services.size(); i++) {
                    if (services.get(i).getPORTNUM() == null ? serv.getPORTNUM() == null : services.get(i).getPORTNUM().equals(serv.getPORTNUM())) {
                        services.set(i, serv);
                        hasService = true;
                    }
                }
                if (!hasService) {
                    services.add(serv);
                }

                request.setAttribute("services", services);
                session.setAttribute("services", services);
                for (PortModel p : ports) {
                    if (p.getPORTNUM().equals(port)) {
                        Connection conn = MyUtils.getStoredConnection(request);
                        list.addAll(DBUtils.GetVultData(conn, p.getNAME()));
                        list = duplicateRepair(list);
                        session.setAttribute("listVult", list);
                        request.setAttribute("listVult", list);
                    }
                }
            }
        }

        // If the user has logged in, then forward to the page
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

    private List<VultModel> duplicateRepair(List<VultModel> list) {
        int listSize = list.size();
        for (int i = 0; i < listSize; i++) {
            for (int j = i + 1; j < listSize; j++) {
                boolean b = list.get(i).equals(list.get(j));
                if (list.get(i).equals(list.get(j))) {
                    list.remove(j);
                    listSize -= 1;
                    j -= 1;
                }
            }
        }
        return list;
    }

    public static void GoogleSearch() {
        String google = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
        String search = "stackoverflow";
        String charset = "UTF-8";

        URL url = null;
        try {
            url = new URL(google + URLEncoder.encode(search, charset));
        } catch (UnsupportedEncodingException | MalformedURLException ex) {
            Logger.getLogger(ResultServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        Reader reader = null;
        try {
            reader = new InputStreamReader(url.openStream(), charset);
        } catch (IOException ex) {
            Logger.getLogger(ResultServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
//        GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);

        // Show title and URL of 1st result.
//        System.out.println(results.getResponseData());
    }
}
