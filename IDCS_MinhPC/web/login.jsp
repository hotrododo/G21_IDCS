<%-- 
    Document   : login
    Created on : Oct 29, 2019, 10:31:59 AM
    Author     : macbook
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <title>LOGIN FORM</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" type="image/png" href="../${pageContext.request.contextPath}/image/favicon.png">
        <link rel="stylesheet" href="../${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="../${pageContext.request.contextPath}/css/fontawesome-free/css/all.min.css">
        <link href="../${pageContext.request.contextPath}/css/common.css" rel="stylesheet">
        <link rel="stylesheet" href="../${pageContext.request.contextPath}/css/style.css">
        <link rel="stylesheet" href="css/mobie-css.css">
        <script src="../${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
        <script src="../${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
    </head>
    <body class="page_login">
        <h1 id="test"></h1>
        <main>
            <div class="login_form">
                <div class="logo">
                    <a href="/" title="FPT University">
                        <img src="image/logo_fpt.png" alt="logo" srcset="">
                    </a>
                </div>
                <div class="title-login">
                    <h1>LOGIN TO IDCS</h1>
                </div>
                <form id="loginForm" action="loginServlet" class="needs-validation" novalidate method="POST">
                    <div class="form-group">
                        <label class="login-input" for="uname"><i class="fas fa-user-circle"></i></label>
                        <input type="text" class="form-control" id="txtusername" placeholder="Enter username" name="uname" required>
                        <!--<div class="notifi-input valid-feedback">Valid.</div>!-->
                        <div class="notifi-input invalid-feedback">Please fill out this field.</div>
                    </div>
                    <div class="form-group">
                        <label class="login-input" for="pwd"><i class="fas fa-lock"></i></label>
                        <input type="password" class="form-control" id="txtpassword" placeholder="Enter password" name="pswd" required>

                        <!--<div class="notifi-input valid-feedback">Valid.</div>!-->
                        <div class="notifi-input invalid-feedback">Please fill out this field.</div>
                    </div>
                    <div class="form-group form-check">
                        <label class="form-check-label">
                            <input class="form-check-input" type="checkbox" name="remember" required> Remember me
                            <span class="checkmark"></span>
                            <!--<div class="valid-feedback">Valid.</div>!-->
                            <div class="invalid-feedback">Check this checkbox to continue.</div>
                        </label>
                        <a href="forgot-password.html" class="forgot" title="Forgot Password">Forgot Password ?</a>
                    </div>
                    <div>
                        <p><i class="fas fa-info-circle"></i> Don't have an account? <a href="registration.html">Sign Up</a></p>
                    </div>
                    <input id = "btnlogin" type="submit" class="btn btn-primary submit_login" onclick="login()" value="LOGIN"></input>
                </form>
            </div>

        </main>
        <footer>

        </footer>
    </body>
</html>
