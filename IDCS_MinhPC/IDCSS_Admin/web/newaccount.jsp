<%-- 
    Document   : newaccount
    Created on : Jan 22, 2020, 4:48:04 PM
    Author     : macbook
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Language" content="en">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>DashBoards</title>
    <meta name="viewport"
        content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no" />
    <meta name="description" content="Manager Member Of IDCS">
    <meta name="msapplication-tap-highlight" content="no">
    <link href="../${pageContext.request.contextPath}/main.css" rel="stylesheet">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
</head>

<body>
    <div class="app-container app-theme-white body-tabs-shadow fixed-sidebar fixed-header">
        <div class="app-header header-shadow">
            <div class="app-header__logo">
                <div class="logo-src"></div>
                <div class="header__pane ml-auto">
                    <div>
                        <button type="button" class="hamburger close-sidebar-btn hamburger--elastic"
                            data-class="closed-sidebar">
                            <span class="hamburger-box">
                                <span class="hamburger-inner"></span>
                            </span>
                        </button>
                    </div>
                </div>
            </div>
            <div class="app-header__mobile-menu">
                <div>
                    <button type="button" class="hamburger hamburger--elastic mobile-toggle-nav">
                        <span class="hamburger-box">
                            <span class="hamburger-inner"></span>
                        </span>
                    </button>
                </div>
            </div>
            <div class="app-header__menu">
                <span>
                    <button type="button"
                        class="btn-icon btn-icon-only btn btn-primary btn-sm mobile-toggle-header-nav">
                        <span class="btn-icon-wrapper">
                            <i class="fa fa-ellipsis-v fa-w-6"></i>
                        </span>
                    </button>
                </span>
            </div>
            <div class="app-header__content">
                <div class="app-header-left">
                    <div class="search-wrapper">
                        <div class="input-holder">
                            <input id="search" type="text" class="search-input" placeholder="Type to search">
                            <button class="search-icon"><span></span></button>
                        </div>
                        <button class="close"></button>
                    </div>
                </div>
                <div class="app-header-right">
                    <div class="header-btn-lg pr-0">
                        <div class="widget-content p-0">
                            <div class="widget-content-wrapper">
                                <div class="widget-content-left">
                                    <div class="btn-group">
                                        <a data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
                                            class="p-0 btn">
                                            <img width="42" class="rounded-circle" src="assets/images/avatars/1.jpg"
                                                alt="">
                                            <i class="fa fa-angle-down ml-2 opacity-8"></i>
                                        </a>
                                        <div tabindex="-1" role="menu" aria-hidden="true"
                                            class="dropdown-menu dropdown-menu-right">
                                            <button type="button" tabindex="0" class="dropdown-item">User
                                                Account</button>
                                            <button type="button" tabindex="0" class="dropdown-item">Settings
                                                <span class="badgeComingSoon badge badge-pill badge-warning">Coming
                                                    soon</span>
                                            </button>
                                            <button type="button" tabindex="0" class="dropdown-item">Actions</button>
                                            <div tabindex="-1" class="dropdown-divider"></div>
                                            <button type="button" tabindex="0" class="dropdown-item">
                                                <a href="login.jsp">Log Out</a>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <div class="widget-content-left  ml-3 header-user-info">
                                    <div class="widget-heading">
                                        Andy Pham
                                    </div>
                                    <div class="widget-subheading">
                                        IDCS Manager
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui-theme-settings">
            <button type="button" id="TooltipDemo" class="btn-open-options btn btn-warning"
                style="pointer-events: none;">
                <i class="fa fa-cog fa-w-16 fa-spin fa-2x"></i>
            </button>
            <div class="theme-settings__inner"></div>
        </div>
        <div class="app-main">
            <div class="app-sidebar sidebar-shadow">
                <div class="app-header__logo">
                    <div class="logo-src"></div>
                    <div class="header__pane ml-auto">
                        <div>
                            <button type="button" class="hamburger close-sidebar-btn hamburger--elastic"
                                data-class="closed-sidebar">
                                <span class="hamburger-box">
                                    <span class="hamburger-inner"></span>
                                </span>
                            </button>
                        </div>
                    </div>
                </div>
                <div class="app-header__mobile-menu">
                    <div>
                        <button type="button" class="hamburger hamburger--elastic mobile-toggle-nav">
                            <span class="hamburger-box">
                                <span class="hamburger-inner"></span>
                            </span>
                        </button>
                    </div>
                </div>
                <div class="app-header__menu">
                    <span>
                        <button type="button"
                            class="btn-icon btn-icon-only btn btn-primary btn-sm mobile-toggle-header-nav">
                            <span class="btn-icon-wrapper">
                                <i class="fa fa-ellipsis-v fa-w-6"></i>
                            </span>
                        </button>
                    </span>
                </div>
                <div class="scrollbar-sidebar">
                    <div class="app-sidebar__inner">
                        <ul class="vertical-nav-menu">
                            <li class="app-sidebar__heading">Dashboards</li>
                            <li>
                                <a href="home.jsp">
                                    <i class="metismenu-icon pe-7s-rocket">
                                    </i>Customers Manager
                                </a>
                            </li>
                            <li>
                                <a href="newaccount.jsp" class="mm-active">
                                    <i class="metismenu-icon pe-7s-plus">
                                    </i>New Account
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="app-main__outer">
                <div class="app-main__inner">
                    <div class="app-page-title">
                        <div class="page-title-wrapper">
                            <div class="page-title-heading">
                                <div class="page-title-icon">
                                    <i class="pe-7s-bell icon-gradient bg-happy-itmeo">
                                    </i>
                                </div>
                                <div>New Customers
                                    <div class="page-title-subheading">Someone want join with us!
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="tab-content">
                        <div class="tab-pane tabs-animation fade show active" role="tabpanel">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="main-card mb-3 card">
                                        <div class="card-body">
                                            <h3 class="card-title">IDCS Member</h3>
                                            <table id="myTable" class="mb-0 table table-hover">
                                                <thead>
                                                    <tr>
                                                        <th>.No</th>
                                                        <th class="text-center">Username</th>
                                                        <th class="text-center">Full Name</th>
                                                        <th class="text-center">Email</th>
                                                        <th class="text-center">Phone</th>
                                                        <th class="text-center">Address</th>
                                                        <th class="text-center">Credit</th>
                                                        <th class="text-center">Gender</th>
                                                        <th class="text-center">Actions</th>
                                                    </tr>
                                                </thead>
                                                <tbody id="mytable">
                                                    <tr>
                                                        <td scope="row">1</td>
                                                        <td class="text-center">@mdo</td>
                                                        <td class="text-center">Mark</td>
                                                        <td class="text-center">Otto</td>
                                                        <td class="text-center">@mdo</td>
                                                        <td class="text-center">Mark</td>
                                                        <td class="text-center">Otto</td>
                                                        <td class="text-center">@mdo</td>
                                                        <td class="text-center">
                                                            <button type="button"
                                                                class="btn mr-2 mb-2 btn-danger">Reject</button>
                                                            <button type="button" class="btn mr-2 mb-2 btn-primary">
                                                                Accept
                                                            </button>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td scope="row">2</td>
                                                        <td class="text-center">@mdo</td>
                                                        <td class="text-center">Mark</td>
                                                        <td class="text-center">Otto</td>
                                                        <td class="text-center">@mdo</td>
                                                        <td class="text-center">Mark</td>
                                                        <td class="text-center">Otto</td>
                                                        <td class="text-center">@mdo</td>
                                                        <td class="text-center">
                                                            <button type="button"
                                                                class="btn mr-2 mb-2 btn-danger">Reject</button>
                                                            <button type="button" class="btn mr-2 mb-2 btn-primary">
                                                                Accept
                                                            </button>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td scope="row">3</td>
                                                        <td class="text-center">@mdo</td>
                                                        <td class="text-center">Mark</td>
                                                        <td class="text-center">Otto</td>
                                                        <td class="text-center">@mdo</td>
                                                        <td class="text-center">Mark</td>
                                                        <td class="text-center">Otto</td>
                                                        <td class="text-center">@mdo</td>
                                                        <td class="text-center">
                                                            <button type="button"
                                                                class="btn mr-2 mb-2 btn-danger">Reject</button>
                                                            <button type="button" class="btn mr-2 mb-2 btn-primary">
                                                                Accept
                                                            </button>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>

                    </div>

                </div>
                <div class="app-wrapper-footer">
                    <div class="app-footer">
                        <div class="app-footer__inner">
                            <div class="app-footer-right">
                                <ul class="nav">
                                    <li class="nav-item">
                                        <p class="nav-link">
                                            Copyright of IDCS .Ltd
                                        </p>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>
<script type="text/javascript" src="./assets/scripts/main.js"></script>
<script type="text/javascript" src="./assets/scripts/script.js"></script>

</html>
<!-- Modal -->
<div class="modal fade" id="dialogModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modalLabel">Confirm create new account!</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p class="mb-0">Do you want create this account?</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>
