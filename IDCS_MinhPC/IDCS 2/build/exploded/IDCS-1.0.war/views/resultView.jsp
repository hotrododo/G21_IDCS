<%-- 
    Document   : resultView
    Created on : Oct 9, 2019, 10:34:00 PM
    Author     : mac
--%>

<%@page import="CSTCopyright.IDCS.controller.VultModel"%>
<%@page import="CSTCopyright.IDCS.controller.ServiceModel"%>
<%@page import="java.util.List"%>
<%@page import="CSTCopyright.IDCS.controller.PortModel"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>REGISTRATION</title>
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
                            <a href="../${pageContext.request.contextPath}/login" title="IDCS">
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
                        <a href="homepage.html" class="home_page"><i class="fas fa-home"></i> HOME PAGE</a>
                    </div>
                </div>
            </div>

        </header>
        <main>

            <div class="box-result">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-3">
                            <div class="child-box-result">
                                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d29793.98038436799!2d105.81945407847766!3d21.022778763266096!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3135ab9bd9861ca1%3A0xe7887f7b72ca17a9!2zSMOgIE7hu5lpLCBIb8OgbiBLaeG6v20sIEjDoCBO4buZaSwgVmnhu4d0IE5hbQ!5e0!3m2!1svi!2s!4v1574326595861!5m2!1svi!2s"
                                        width="100%" height="250" frameborder="0" style="border:0;" allowfullscreen=""></iframe>
                                <h4><i class="fas fa-globe-americas"></i>${host.getDDNS()}</h4>
                                <div class="host-info-tab">
                                    <ul class="width-45-percen">
                                        <li>
                                            IPv4
                                        </li>
                                        <li>
                                            Country
                                        </li>
                                        <li>
                                            Orgranzation
                                        </li>
                                        <li>
                                            ISP
                                        </li>
                                        <li>
                                            Last Update
                                        </li>
                                        <li>
                                            ASN
                                        </li>
                                    </ul>
                                    <ul class="host-info-fiels">
                                        <li>${host.getIPv4()}</li>
                                        <li>${host.getCOUNTRY()}</li>
                                        <li>${host.getNETNAME()}</li>
                                        <li>${host.getDESCR()}</li>
                                        <li>${host.getDATESCAN()}</li>
                                        <li>${host.getPERSON()}</li>
                                    </ul>
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
                                <form class="list_port" method="POST" id="form1" action="result">
                                    <%
                                        List<PortModel> ports = (List<PortModel>) request.getAttribute("ports");
                                        for (PortModel p : ports) {
                                    %>
                                    <button type="submit" name="port" form="form1" id="port" value="<%= p.getPORTNUM()%>"><%= p.getPORTNUM()%></button>
                                    <%
                                        }
                                    %>
                                </form>

                            </div>
                            <div>
                                <h4><i class="fas fa-list"></i> SERVICE</h4>
                                <div class="services-panel">


                                    <%
                                        List<ServiceModel> services = (List<ServiceModel>) request.getAttribute("services");
                                        if (services != null) {
                                            for (ServiceModel serv : services) {
                                                if (serv != null) {
                                    %>
                                    <div class="service-line">
                                        <h4><%=serv.getPORTNUM()%></h4>
                                        <%
                                            for (PortModel port : ports) {
                                                if (port.getPORTNUM().equals(serv.getPORTNUM())) {
                                        %>
                                        <p><%=port.getNAME()%></p>
                                        <p><%=port.getCPE()%></p>
                                        <p><%=port.getEXTRALINFO()%></p>
                                        <p><%=port.getPRODUCT()%></p>
                                        <p><%=port.getVERSION()%></p>

                                        <div class="services-result">
                                            <div class="services-tab">
                                                <p style="color: white;"><%=serv.getSCRIPT()%></p>

                                            </div>
                                            <div class="suggestion-tab">
                                                <%
                                                    List<VultModel> list = (List<VultModel>) request.getAttribute("listVult");
                                                    if (list != null) {
                                                        for (VultModel vult : list) {
                                                           if (port.getNAME().equals(vult.getVultObj())
                                                                   && serv.getSCRIPT().toLowerCase().contains(vult.getVultDes().toLowerCase())) {%>
                                                <p style="color: red;"><%=vult.getVultRem()%></p>
                                                <%}
                                                    }
                                                }%> 
                                            </div>
                                        </div>
                                        <%}
                                                        }%>
                                    </div>
                                    <%}
                                                            }
                                                        }%>

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
