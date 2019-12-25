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
    <title>IDCS Registratioin</title>

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
                            <span class="mdl-card__title-text text-color--smooth-gray">IDCS</span>
                        </div>
                        <div class="mdl-cell mdl-cell--12-col mdl-cell--4-col-phone">
                            <span class="login-name text-color--white">Sign up</span>
                        </div>
                        <form action="register" method="POST">
                            <div class="mdl-cell mdl-cell--12-col mdl-cell--4-col-phone">
                                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label full-size">
                                    <input class="mdl-textfield__input" name="uname" type="text" id="name" value="${uname}" required>
                                    <label class="mdl-textfield__label" for="name">Username</label>
                                </div>
                                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label full-size">
                                    <input class="mdl-textfield__input" name="fullname" type="text" id="fullname" required>
                                    <label class="mdl-textfield__label" for="fullname">Full Name</label>
                                </div>
                                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label full-size">
                                    <label class="mdl-radio mdl-js-radio mdl-js-ripple-effect" for="male">
                                        <input type="radio" id="male" class="mdl-radio__button" name="gender" value="1"/>
                                        <span class="mdl-radio__label">Male</span>
                                    </label>
                                    <label style="margin-left: 80px" class="mdl-radio mdl-js-radio mdl-js-ripple-effect" for="female">
                                        <input type="radio" id="female" class="mdl-radio__button" name="gender" value="0"/>
                                        <span class="mdl-radio__label">Female</span>
                                    </label>
                                </div>
                                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label full-size">
                                    <input class="mdl-textfield__input" name="email" type="text" id="e-mail" required>
                                    <label class="mdl-textfield__label" for="e-mail">Email</label>
                                </div>
                                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label full-size">
                                    <input class="mdl-textfield__input" name="psphone" type="text" id="phone" required>
                                    <label class="mdl-textfield__label" for="phone">Phone</label>
                                </div>
                                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label full-size">
                                    <input class="mdl-textfield__input" name="address" type="text" id="address" required>
                                    <label class="mdl-textfield__label" for="address">Address</label>
                                </div>
                                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label full-size">
                                    <input class="mdl-textfield__input" name="passw" type="password" id="password" required>
                                    <label class="mdl-textfield__label" for="password">Password</label>
                                </div>
                                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label full-size">
                                    <input class="mdl-textfield__input" name="repassw" type="password" id="repass" required>
                                    <label class="mdl-textfield__label" for="repass">Confirm Password</label>
                                </div>
                                <p class="color-text--red" id="notif" >${Notif}</p>
                                <p class="color-text--green" id="notifSuc" >${NotifSuc}</p>
                                <label class="mdl-checkbox mdl-js-checkbox mdl-js-ripple-effect checkbox--colored-light-blue checkbox--inline" for="checkbox-1">
                                    <input type="checkbox" id="checkbox-1" class="mdl-checkbox__input" >

                                </label>
                                <span class="login-link">I agree all statements in <a href="#" class="underlined">terms of service</a></span>
                            </div>
                            <div class="mdl-cell mdl-cell--12-col mdl-cell--4-col-phone submit-cell">
                                <a href="login" class="login-link">I have already signed up!</a>
                                <div class="mdl-layout-spacer"></div>
                                <button id="submit" type="submit" class="mdl-button mdl-js-button mdl-button--raised button--colored-light-blue" onclick="return checkConfirmPass()">
                                    SIGN UP
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
    <script src="../${pageContext.request.contextPath}/JS/javascript.js"></script> 
</body>
</html>
