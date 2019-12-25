<%-- 
    Document   : _header
    Created on : Dec 21, 2019, 5:19:27 PM
    Author     : macbook
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <link rel="icon" type="image/png" href="../${pageContext.request.contextPath}/IMG/idcs_16x16.png">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>IDCS Sign In</title>

    <!-- Add to homescreen for Chrome on Android -->
    <meta name="mobile-web-app-capable" content="yes">


    <!-- Add to homescreen for Safari on iOS -->
    <meta name="apple-mobile-web-app-capable" content="ye">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-title" content="IDCS Manager">

    <link href='https://fonts.googleapis.com/css?family=Roboto:400,500,300,100,700,900' rel='stylesheet'
          type='text/css'>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!-- inject:css -->
    <link rel="stylesheet" href="../${pageContext.request.contextPath}/CSS/lib/getmdl-select.min.css">
    <link rel="stylesheet" href="../${pageContext.request.contextPath}/CSS/lib/nv.d3.min.css">
    <link rel="stylesheet" href="../${pageContext.request.contextPath}/CSS/application.min.css">
    <link rel="stylesheet" href="../${pageContext.request.contextPath}/CSS/style.css">
    <!-- endinject -->
</head>
<body>
    <div class="mdl-layout mdl-js-layout color--gray is-small-screen login">
        <main class="mdl-layout__content">
            <div class="mdl-card mdl-card__login mdl-shadow--2dp">
                <div class="mdl-card__supporting-text color--dark-gray">
                    <div class="mdl-grid">
                        <div class="mdl-cell mdl-cell--12-col mdl-cell--4-col-phone">
                            <span class="mdl-card__title-text text-color--smooth-gray">IDCS Company</span>
                        </div>
                        <div class="mdl-cell mdl-cell--12-col mdl-cell--4-col-phone">
                            <span class="login-name text-color--white">Sign in</span>
                            <span class="login-secondary-text text-color--smoke">Enter fields to sign in to IDCS</span>
                        </div>
                        <form action="login" method="POST">
                            <div class="mdl-cell mdl-cell--12-col mdl-cell--4-col-phone">
                                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label full-size">
                                    <input class="mdl-textfield__input" type="text" id="e-mail" value="${user.userName}" name="userName" required>
                                    <label class="mdl-textfield__label" for="e-mail">Account</label>
                                </div>
                                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label full-size">
                                    <input class="mdl-textfield__input" type="password" id="password" value="${user.password}" name="password" required >
                                    <label class="mdl-textfield__label" for="password">Password</label>
                                </div>
                                <p class="color-text--red">${errorString}</p>
                                <a href="forgot" class="login-link">Forgot password?</a>
                            </div>
                            <div class="mdl-cell mdl-cell--12-col mdl-cell--4-col-phone submit-cell">
                                <a href="register" class="login-link">Don't have account?</a>
                                <div class="mdl-layout-spacer"></div>
                                <button type="submit" class="mdl-button mdl-js-button mdl-button--raised color--light-blue">
                                    SIGN IN
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </main>
    </div>
    <!-- inject:js -->
    <script src="../${pageContext.request.contextPath}/JS/d3.min.js"></script>
    <script src="../${pageContext.request.contextPath}/JS/getmdl-select.min.js"></script>
    <script src="../${pageContext.request.contextPath}/JS/material.min.js"></script>
    <script src="../${pageContext.request.contextPath}/JS/nv.d3.min.js"></script>
    <script src="../${pageContext.request.contextPath}/JS/layout.min.js"></script> 
    <script src="../${pageContext.request.contextPath}/JS/scroll.min.js"></script>
    <script src="../${pageContext.request.contextPath}/JS/table.min.js"></script> 
</body>
</html>
