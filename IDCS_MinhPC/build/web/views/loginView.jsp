<%-- 
    Document   : loginView
    Created on : Oct 9, 2019, 2:52:36 AM
    Author     : mac
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login - Internet-connected Checking System</title>
        <link rel="stylesheet" type="text/css" href="..${pageContext.request.contextPath}/CSS/common.css"/>
        <script src="https://kit.fontawesome.com/1956e41869.js" crossorigin="anonymous"></script>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>-->
        <link rel="stylesheet" href="..${pageContext.request.contextPath}/CSS/login.css">
        <link rel="stylesheet" href="..${pageContext.request.contextPath}/CSS/style.css">
        <link rel="stylesheet" href="..${pageContext.request.contextPath}/CSS/mobie-css.css">
        <link rel="icon" type="image/png" href="..${pageContext.request.contextPath}/IMG/favicon.png">
        <link rel="stylesheet" href="..${pageContext.request.contextPath}/CSS/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="..${pageContext.request.contextPath}/CSS/fontawesome-free/css/all.min.css">
    </head>
    <body class="page_login">
            <main>
                <div class="login_form">
                    <div class="logo">
                        <a href="/" title="IDCS">
                            <img src="..${pageContext.request.contextPath}/IMG/logo_idcs.png" alt="logo" srcset="">
                    </a>
                </div>
                <div class="title-login">
                    <h1>LOGIN TO IDCS</h1>
                </div>
                <form action="login" method="POST" class="needs-validation">
                    <div class="form-group">
                        <label class="login-input" for="uname"><i class="fas fa-user-circle"></i></label>
                        <input type="text" class="form-control" id="uname" placeholder="Enter username" value="${user.userName}" name="userName" required>
                        <div class="notifi-input valid-feedback">Valid.</div>
                        <div class="notifi-input invalid-feedback">Please fill out this field.</div>
                    </div>
                    <div class="form-group">
                        <label class="login-input" for="pwd"><i class="fas fa-lock"></i></label>
                        <input type="password" class="form-control" value="${user.password}" name="password" placeholder="Enter password" id="password" required>

                        <div class="notifi-input valid-feedback">Valid.</div>
                        <div class="notifi-input invalid-feedback">Please fill out this field.</div>
                    </div>
                    <p style="color: red;">${errorString}</p>
                    <div class="form-group form-check">
                        <label class="form-check-label">
                            <input class="form-check-input" type="checkbox" name="remember" value= "Y"> Remember me
                        </label>
                        <a href="forgot-password.html" class="forgot" title="Forgot Password">Forgot Password ?</a>
                    </div>
                    <div>
                        <p><i class="fas fa-info-circle"></i> Don't have an account? <a href="registration.html">Sign Up</a></p>
                    </div>
                    <button type="submit" class="btn btn-primary submit_login">LOGIN</button>
                </form>
            </div>

        </main>
    </body>
</html>
