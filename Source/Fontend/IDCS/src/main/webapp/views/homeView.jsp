<%-- 
    Document   : homeView
    Created on : Oct 7, 2019, 2:37:22 AM
    Author     : mac
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Internet-connected Checking System</title>
        <link rel="stylesheet" type="text/css" href="../${pageContext.request.contextPath}/CSS/common.css"/>
        <script src="https://kit.fontawesome.com/1956e41869.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="../${pageContext.request.contextPath}/CSS/style.css">
        <link rel="stylesheet" href="../${pageContext.request.contextPath}/CSS/mobie-css.css">
        <link rel="icon" type="../${pageContext.request.contextPath}/IMG/png" href="./image/favicon.png">
        <link rel="stylesheet" href="../${pageContext.request.contextPath}/CSS/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="../${pageContext.request.contextPath}/CSS/fontawesome-free/css/all.min.css">
        <script src="../${pageContext.request.contextPath}/JS/javascript.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    </head>
    <%
        String errorMess = new String();
        errorMess = (String) request.getAttribute("errorMess");
    %>
    <body class="bg_home" onload="hasErrorAlert('${errorMess}')">
        <jsp:include page="_header.jsp"></jsp:include>
            <div class="content_layout">
                <div class="execute_content">
                    <form method="POST" action="home" onsubmit="return frmValidate()">
                        <div class="input-area">
                            <div class="txt_input_domain">
                                <input class="txt_input" name="frmDomain" id="frmDomain" placeholder="Enter your site..."/>
                                <button class="btn-domain" type="submit" value="Submit">
                                    <img style="max-height: 100%" src="../${pageContext.request.contextPath}/IMG/btn_search.gif" alt="Search" />
                            </button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="view-result">
                <div class="container">
                    <div class="row navi-link">
                        <div class="col-md-3">
                            <form method="POST" action="viewHistory">
                                <div class="content-box">
                                    <h3><i class="far fa-clock"></i> Last Check</h3>
                                    <div class="col-md-12 cont-result">
                                        <button class="row" type="submit" name="history" value="">
                                            <a>Test A</a>
                                        </button>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <p>DDNS:</p>
                                                <p>AAAAAAAAA</p>
                                                <p>Net Name:</p>
                                                <p>AAAAAAAAA</p>
                                                <p>Scan Date:</p>
                                                <p>AAAAAAAAA</p>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="col-md-3">
                            <form method="POST" action="viewHistory">
                                <div class="content-box">
                                    <h3><i class="fas fa-chart-line"></i> Top Check</h3>
                                    <div class="col-md-12 cont-result">
                                        <button class="row" type="submit" name="history" value="">
                                            <a>Test B</a>
                                        </button>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <p class="row">DDNS:</p>
                                                <p class="row">BBBBBBBBBB</p>
                                                <p class="row">Net Name:</p>
                                                <p class="row">BBBBBBBBBB</p>
                                                <p class="row">Scan Date:</p>
                                                <p class="row">BBBBBBBBBB</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>

                        <div class="col-md-3">
                            <form method="POST" action="poor">
                                <div class="content-box">
                                    <h3><i class="fas fa-binoculars"></i> View Report</h3>
                                    <div class="col-md-12 cont-result">
                                        <button class="row" type="submit" name="history" value="">
                                            <a>Test C</a>
                                        </button>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <p class="row">DDNS:</p>
                                                <p class="row">CCCCCCCCCCC</p>
                                                <p class="row">Net Name:</p>
                                                <p class="row">CCCCCCCCCCC</p>
                                                <p class="row">Scan Date:</p>
                                                <p class="row">CCCCCCCCCCC</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>


                        <div class="col-md-3">
                            <form method="POST" action="poor">
                                <div class="content-box">
                                    <h3><i class="fas fa-file-export"></i> Export Report</h3>
                                    <div class="cont-result">
                                        <button class="row" type="submit" name="history" value="">
                                            <a>Test D</a>
                                        </button>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <p class="row">DDNS:</p>
                                                <p class="row">DDDDDDDDDDDD</p>
                                                <p class="row">Net Name:</p>
                                                <p class="row">DDDDDDDDDDDD</p>
                                                <p class="row">Scan Date:</p>
                                                <p class="row">DDDDDDDDDDDD</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <footer>
        <jsp:include page="_footer.jsp"></jsp:include>
    </footer>
</html>
