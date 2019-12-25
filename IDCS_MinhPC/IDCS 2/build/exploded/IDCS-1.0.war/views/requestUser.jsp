<%-- 
    Document   : index
    Created on : Dec 21, 2019, 2:48:25 PM
    Author     : macbook
--%>

<%@page import="java.util.List"%>
<%@page import="CSTCopyright.IDCS.model.UserAccount"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <link rel="icon" type="image/png" href="../${pageContext.request.contextPath}/IMG/idcs_16x16.png">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>IDCS Request</title>

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
        <div class="mdl-layout mdl-js-layout mdl-layout--fixed-drawer mdl-layout--fixed-header is-small-screen">
            <jsp:include page="_headerAdmin.jsp"></jsp:include>
                <div class="mdl-layout__drawer">
                    <header>IDCS Admin</header>
                    <div class="scroll__wrapper" id="scroll__wrapper">
                        <div class="scroller" id="scroller">
                            <div class="scroll__container" id="scroll__container">
                                <nav class="mdl-navigation">
                                    <a class="mdl-navigation__link" href="manager">
                                        <i class="material-icons" role="presentation">home</i>
                                        Active Account
                                    </a>

                                    <a class="mdl-navigation__link mdl-navigation__link--current" >
                                        <i class="material-icons">add_alert</i>
                                        Account Request
                                    </a>
                                </nav>
                            </div>
                        </div>
                        <div class='scroller__bar' id="scroller__bar"></div>
                    </div>
                </div>
                <main class="mdl-layout__content ">
                    <div class="mdl-grid ui-tables">
                        <div style="width: calc(100% - 32px);" class="mdl-cell mdl-cell--6-col-desktop mdl-cell--6-col-tablet mdl-cell--4-col-phone">
                            <div class="mdl-card mdl-shadow--2dp">
                                <div class="mdl-card__title">
                                    <h1 class="mdl-card__title-text">User Request</h1>
                                </div>
                                <div class="mdl-card__supporting-text no-padding">
                                    <form action="userrequest" method="POST">
                                        <input type="text" id="username" name="username" hidden/>
                                        <table id="table" class="mdl-data-table mdl-js-data-table stripped-table">
                                            <thead>
                                                <tr>
                                                    <th class="mdl-data-table__cell--non-numeric">.No</th>
                                                    <th class="mdl-data-table__cell--non-numeric">Full Name</th>
                                                    <th class="mdl-data-table__cell--non-numeric">Account</th>
                                                    <th class="mdl-data-table__cell--non-numeric">Gender</th>
                                                    <th class="mdl-data-table__cell--non-numeric">Email</th>
                                                    <th class="mdl-data-table__cell--non-numeric">Address</th>
                                                    <th class="mdl-data-table__cell--non-numeric">Phone</th>
                                                    <th class="mdl-data-table__cell--non-numeric">Expiry Date</th>
                                                    <th class="mdl-data-table__cell--non-numeric">UseCount</th>
                                                    <th class="mdl-data-table__cell--non-numeric">Status</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <%
                                                List<UserAccount> user = (List<UserAccount>) request.getAttribute("listUser");
                                                int index = 1;
                                                for (UserAccount p : user) {
                                            %>
                                            <tr>
                                                <td class="mdl-data-table__cell--non-numeric"><%= index++%></td>
                                                <td class="mdl-data-table__cell--non-numeric"><%= p.getFullName()%></td>
                                                <td class="mdl-data-table__cell--non-numeric"><%= p.getUserName()%></td>
                                                <td class="mdl-data-table__cell--non-numeric"><% if (p.isGender()) { %> Male  <%} else { %>Female <%}%></td>
                                                <td class="mdl-data-table__cell--non-numeric"><%= p.getEmailAddress()%></td>
                                                <td class="mdl-data-table__cell--non-numeric"><%= p.getAddress()%></td>
                                                <td class="mdl-data-table__cell--non-numeric"><%= p.getPhoneNumber()%></td>
                                                <td class="mdl-data-table__cell--non-numeric"><%= p.getExpiryDate()%></td>
                                                <td class="mdl-data-table__cell--non-numeric"><%= p.getUseCount()%></td>

                                                
                                                <td class="mdl-data-table__cell--non-numeric" style="width: 20%">
                                                    <button onclick="checkConfirmCreate()" id="btn" value="accept"  name="btn" class="mdl-button mdl-js-button mdl-js-ripple-effect color-text--green">
                                                        <i class="material-icons">check_circle</i>
                                                        Accept
                                                    </button>
                                                    <button onclick="checkConfirmCreate()" id="btn" value="reject" name="btn" class="mdl-button mdl-js-button mdl-js-ripple-effect color-text--red">
                                                        <i class="material-icons">do_not_disturb</i>
                                                        Reject
                                                    </button>
                                                </td>
                                            </tr>
                                            <%}%>
                                        </tbody>
                                    </table>
                                </form>
                            </div>
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
        <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.4.1.min.js"></script>
        <!-- endinject -->

    </body>
</html>
