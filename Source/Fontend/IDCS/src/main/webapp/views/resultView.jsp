<%-- 
    Document   : resultView
    Created on : Oct 9, 2019, 10:34:00 PM
    Author     : mac
--%>


<%@page import="java.util.List"%>
<%@page import="org.json.JSONObject"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Home - Internet-connected Devices Checking System</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" type="image/png" href="../${pageContext.request.contextPath}/IMG/favicon.png">
        <link rel="stylesheet" href="../${pageContext.request.contextPath}/CSS/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="../${pageContext.request.contextPath}/CSS/fontawesome-free/css/all.min.css">
        <link href="../${pageContext.request.contextPath}/CSS/common.css" rel="stylesheet">
        <link rel="stylesheet" href="../${pageContext.request.contextPath}/CSS/style.css">
        <link rel="stylesheet" href="../${pageContext.request.contextPath}/CSS/mobie-css.css">
    </head>
    <body class="page_result">
        <header class="header">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-sm-6 col-md-6">
                        <div class="logo">
                            <a href="${pageContext.request.contextPath}/home" title="IDCS">
                                <img src="../${pageContext.request.contextPath}/IMG/logo_idcs.png" alt="logo" srcset=""/>
                            </a>
                        </div>
                    </div>
                    <div class="col-sm-4 col-md-4 text-center">
                        <div class="box_search_result">
                            <input type="search" placeholder="Find in the text..." name="" id=""/>
                            <label><i class="fa fa-search" aria-hidden="true"></i></label>
                        </div>
                    </div>
                    <div class="col-sm-2 col-md-2 text-right">
                        <a href="${pageContext.request.contextPath}/home" class="home_page"><i class="fas fa-home"></i> HOME PAGE</a>
                    </div>
                </div>
            </div>

        </header>
        <main>
            <%
                JSONObject host = (JSONObject) request.getAttribute("host");
                JSONObject ports = host.getJSONObject("ports");
                List<String> list_port = (List<String>) request.getAttribute("list_port");
            %>
            <div class="box-result">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-3">
                            <div class="child-box-result">
                                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d29793.98038436799!2d105.81945407847766!3d21.022778763266096!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3135ab9bd9861ca1%3A0xe7887f7b72ca17a9!2zSMOgIE7hu5lpLCBIb8OgbiBLaeG6v20sIEjDoCBO4buZaSwgVmnhu4d0IE5hbQ!5e0!3m2!1svi!2s!4v1574326595861!5m2!1svi!2s"
                                        width="100%" height="250" frameborder="0" style="border:0;" allowfullscreen=""></iframe>
                                <h4><i class="fas fa-globe-americas"></i><%=host.getString("dns")%></h4>
                                <div class="host-info-tab">
                                    <div class="row">
                                        <p class="col-md-4">IPv4</p>
                                        <p class="col-md-7"><%=host.getString("ipv4")%></p>
                                    </div>
                                    <div class="row">
                                        <p class="col-md-4">Country</p>
                                        <p class="col-md-7"><%=host.getString("country")%></p>
                                    </div>
                                    <div class="row">
                                        <p class="col-md-4">Orgranzation</p>
                                        <p class="col-md-7"><%=host.getString("oraganization")%></p>
                                    </div>
                                    <div class="row">
                                        <p class="col-md-4">ISP</p>
                                        <p class="col-md-7"><%=host.getString("net_name")%></p>
                                    </div>
                                    <div class="row">
                                        <p class="col-md-4">Last Update</p>
                                        <p class="col-md-7"><%=host.getString("last_updated")%></p>
                                    </div>
                                    <div class="row">
                                        <p class="col-md-4">IPv6</p>
                                        <p class="col-md-7"><%=host.getString("ipv6")%></p>
                                    </div>
                                </div>
                                <h4>
                                    WEB TECHNOLOGIES
                                </h4>
                                <ul>
                                    <li>
                                        Bootstrap
                                    </li>
                                    <li>
                                        Google font API
                                    </li>
                                    <li>
                                        Jquery
                                    </li>
                                </ul>
                            </div>

                        </div>
                        <div class="col-md-9">

                            <div class="child-box-result">
                                <h4><i class="fas fa-exclamation-triangle"></i> PORT</h4>
                                <form class="list_port" id="form1" >
                                    <%
                                        for (String port : list_port) {
                                    %>
                                    <button type="submit" name="port" form="form1" id="port" value="<%= port%>"><%= port%></button>
                                    <%
                                        }
                                    %>
                                </form>

                            </div>
                            <div>
                                <h4><i class="fas fa-list"></i> SERVICE</h4>
                                <div class="services-panel">


                                    <%
                                        for (String port : list_port) {
                                            String version = "";
                                            String cpe = "";
                                            String service_name = ports.getJSONObject(port).getString("service_name");
                                            String status = ports.getJSONObject(port).getString("status");
                                            version = ports.getJSONObject(port).getString("version");
                                            cpe = ports.getJSONObject(port).getString("cpe");
                                            String os = "";
                                            String app = "";
                                            String hp = "";
                                            if(cpe != null && cpe != ""){
                                                String[] s_part = cpe.split(":");
                                                if(s_part[1] == "/o"){
                                                    os = s_part[2] + " " + s_part[3];
                                                } else if(s_part[1] == "/a"){
                                                    app = s_part[2] + " " + s_part[3];
                                                } else{
                                                    hp = s_part[2] + " " + s_part[3];
                                                }
                                            }
                                    %>
                                    <div class="service-line">
                                        <h4><%=ports.getJSONObject(port).getInt("port_num")%></h4>
                                        <div class="row" style="max-width: 98%;">
                                            <div>
                                                <div class="col-6">Service</div>
                                                <div class="col-6">
                                                    <p><%=service_name%></p>
                                                </div>
                                            </div>
                                            <div>
                                                <div class="col-6">Status</div>
                                                <div class="col-6">
                                                    <p><%=status%></p>
                                                </div>
                                            </div>
                                            <%
                                                if(version != null && version != ""){
                                            %>
                                            <div>
                                                <div class="col-6">Version</div>
                                                <div class="col-6">
                                                    <p><%=version%></p>
                                                </div>
                                            </div>
                                            <%}%>
                                            <%
                                                if(os != null && os != ""){
                                            %>
                                            <div>
                                                <div class="col-6">Operating System</div>
                                                <div class="col-6">
                                                    <p><%=os%></p>
                                                </div>
                                            </div>
                                            <%}%>
                                            <%
                                                if(app != null && app != ""){
                                            %>
                                            <div>
                                                <div class="col-6">Application</div>
                                                <div class="col-6">
                                                    <p><%=app%></p>
                                                </div>
                                            </div>
                                            <%}%>
                                            <%
                                                if(hp != null && hp != ""){
                                            %>
                                            <div>
                                                <div class="col-6">Hardware Platforms</div>
                                                <div class="col-6">
                                                    <p><%=hp%></p>
                                                </div>
                                            </div>
                                            <%}%>
                                        </div>
                                        <div class="services-result">
                                            <%
                                                JSONObject vuln = ports.getJSONObject(port).getJSONObject("vuln");
                                                if (vuln != null && vuln.keySet() != null) {
                                                    for (String cve_num : vuln.keySet()) {
                                                        String cve_dest = vuln.getJSONObject(cve_num).getString("cve_desc");
                                            %>
                                            <div class="col-md-3 services-tab">
                                                <p style="color: red;"><%=cve_num%></p>
                                            </div>
                                            <div class="col-md-9 suggestion-tab">
                                                <p style="color: white;"><%=cve_dest%></p>
                                            </div>
                                            <%}
                                                    }%>
                                        </div>
                                    </div>
                                    <%}%>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>


        </main>
        <footer>
            <p class="text-center info_admin">© 2019, All Rights Reserved - IDSC/FPT University</p>

        </footer>

        <script src="js/jquery-3.4.1.min.js"></script>
        <script src="js/bootstrap/bootstrap.min.js"></script>


    </body>

</html>
