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
    <title>IDCS Dashboard</title>

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
    <form action="headeradmin" method="POST">
        <header class="mdl-layout__header">
            <div class="mdl-layout__header-row">
                <div class="mdl-layout-spacer"></div>
                <div class="material-icons mdl-badge mdl-badge--overlap mdl-button--icon notification" id="notification"
                     data-badge="5">
                    notifications_active
                </div>
                <!-- Notifications dropdown-->
                <ul class="mdl-menu mdl-list mdl-js-menu mdl-js-ripple-effect mdl-menu--bottom-right mdl-shadow--2dp notifications-dropdown"
                    for="notification">
                    <li class="mdl-list__item">
                        You have 23 new notifications!
                    </li>
                    <li class="mdl-menu__item mdl-list__item list__item--border-top">
                        <span class="mdl-list__item-primary-content">
                            <span class="mdl-list__item-avatar background-color--primary">
                                <i class="material-icons">plus_one</i>
                            </span>
                            <span>3 order create new.</span>
                        </span>
                    </li>
                    <li class="mdl-menu__item mdl-list__item list__item--border-top">
                        <span class="mdl-list__item-primary-content">
                            <span class="mdl-list__item-avatar background-color--primary">
                                <i class="material-icons">new_releases</i>
                            </span>
                            <span>4 order reset password!</span>
                        </span>
                    </li>
                    <li class="mdl-list__item list__item--border-top">
                        <a href="userrequest" class="mdl-button mdl-js-button mdl-js-ripple-effect">ALL NOTIFICATIONS</a>
                    </li>
                </ul>

                <div class="avatar-dropdown" id="icon">
                    <span>Nguyen Van Anh Quan</span>
                    <img src="../${pageContext.request.contextPath}/IMG/Icon_header.png">
                </div>
                <!-- Account dropdawn-->
                <ul class="mdl-menu mdl-list mdl-menu--bottom-right mdl-js-menu mdl-js-ripple-effect mdl-shadow--2dp account-dropdown"
                    for="icon">
                    <li class="mdl-list__item mdl-list__item--two-line">
                        <span class="mdl-list__item-primary-content">
                            <span class="material-icons mdl-list__item-avatar">
                                <i class="material-icons">verified_user</i>
                            </span>
                            <span>Nguyen Van Anh Quan</span>
                            <span class="mdl-list__item-sub-title">Luke@skywalker.com</span>
                        </span>
                    </li>
                    <li class="list__item--border-top"></li>
                    <li class="mdl-menu__item mdl-list__item">
                        <span class="mdl-list__item-primary-content">
                            <i class="material-icons mdl-list__item-icon">account_circle</i>
                            My account
                        </span>
                    </li>
                    <li class="mdl-menu__item mdl-list__item">
                        <span class="mdl-list__item-primary-content">
                            <i class="material-icons mdl-list__item-icon">check_box</i>
                            My tasks
                        </span>
                        <span class="mdl-list__item-secondary-content">
                            <span class="label background-color--primary">3 new</span>
                        </span>
                    </li>
                    <li class="mdl-menu__item mdl-list__item">
                        <span class="mdl-list__item-primary-content">
                            <i class="material-icons mdl-list__item-icon">perm_contact_calendar</i>
                            My events
                        </span>
                    </li>
                    <li class="list__item--border-top"></li>
                    <li class="mdl-menu__item mdl-list__item">
                        <span class="mdl-list__item-primary-content">
                            <i class="material-icons mdl-list__item-icon">settings</i>
                            Settings
                        </span>
                    </li>
                    <a href="signout">
                        <li class="mdl-menu__item mdl-list__item">
                            <span class="mdl-list__item-primary-content">
                                <i class="material-icons mdl-list__item-icon text-color--secondary">exit_to_app</i>
                                Log out
                            </span>
                        </li>
                    </a>
                </ul>
            </div>
        </header>
    </form>
</body>
</html>
