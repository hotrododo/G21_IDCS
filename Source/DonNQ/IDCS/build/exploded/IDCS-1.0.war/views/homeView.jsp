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
        <script src="../JS/javascript.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="../${pageContext.request.contextPath}/CSS/style.css">
        <link rel="stylesheet" href="../${pageContext.request.contextPath}/CSS/mobie-css.css">
        <link rel="icon" type="../${pageContext.request.contextPath}/IMG/png" href="./image/favicon.png">
        <link rel="stylesheet" href="../${pageContext.request.contextPath}/CSS/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="../${pageContext.request.contextPath}/CSS/fontawesome-free/css/all.min.css">
    </head>
    <body class="bg_home">

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
                            <div class="content-box">
                                <h3><i class="far fa-clock"></i> Last Check</h3>
                                <div class="cont-result">
                                    <a href="">${list[0].domain}</a>
                                    <p>${list[0].services}</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="content-box">
                                <h3><i class="fas fa-chart-line"></i> Top Check</h3>
                                <div class="cont-result">
                                    <a href="">${list[1].domain}</a>
                                    <p>${list[1].services}</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="content-box">
                                <h3><i class="fas fa-binoculars"></i> View Report</h3>
                                <div class="cont-result">

                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="content-box">
                                <h3><i class="fas fa-file-export"></i> Export Report</h3>
                                <div class="cont-result">

                                </div>

                            </div>
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
