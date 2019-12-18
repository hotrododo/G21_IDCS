<%-- 
    Document   : payment
    Created on : Dec 16, 2019, 3:39:18 PM
    Author     : macbook
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>PAYMENT INFORMATION</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" type="image/png" href="..${pageContext.request.contextPath}/IMG/favicon.png">
        <link rel="stylesheet" href="..${pageContext.request.contextPath}/CSS/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="..${pageContext.request.contextPath}/CSS/fontawesome-free/css/all.min.css">
        <link href="..${pageContext.request.contextPath}/CSS/common.css" rel="stylesheet">
        <link rel="stylesheet" href="..${pageContext.request.contextPath}/CSS/style.css">
        <link rel="stylesheet" href="..${pageContext.request.contextPath}/CSS/mobie-css.css">
    </head>

    <body class="page_login">
        <header class="header">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-sm-6 col-md-6">
                        <div class="logo">
                            <a href="/" title="FPT University">
                                <img src="IMG/logo_fpt.png" alt="logo" srcset="">
                            </a>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-6 text-right">
                        <a href="homepage.html" class="home_page"><i class="fas fa-home"></i> HOME PAGE</a>
                    </div>
                </div>
            </div>

        </header>
        <main>
            <div class="login_form payment_form">
                <div class="title-login">
                    <h1>PAYMENT INFOMATION</h1>
                </div>
                <div>
                    <p>We accept Visa, Master Card. Please pay for us and let me know to check.</p>
                    <h4><i class="fas fa-home"></i> IDCS Company ,Lts</h4>
                    <h4><i class="fab fa-cc-mastercard"></i> 10xxxxxxxxxxxxxx</h4>
                    <h4><i class="fas fa-university"></i> ABC bank</h4>
                    <h4><i class="fas fa-map-marked-alt"></i> Trung Hoa Street, Ha Noi.</h4>
                    <h4><i class="fas fa-phone-alt"></i> (+84) 9xxxxxxxx</h4>
                    <h4><i class="fas fa-envelope"></i> IDCSLts@gmail.com</h4>
                </div>
            </div>

        </main>

        <footer>
            <jsp:include page="_footer.jsp"></jsp:include>
        </footer>
        <script src="..${pageContext.request.contextPath}/JS/jquery-confirm.js"></script>


    </body>

</html>
