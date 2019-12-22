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
        <div class="mdl-layout mdl-js-layout mdl-layout--fixed-drawer mdl-layout--fixed-header is-small-screen">
            <jsp:include page="_headerAdmin.jsp"></jsp:include>
                <div class="mdl-layout__drawer">
                    <header>IDCS Admin</header>
                    <div class="scroll__wrapper" id="scroll__wrapper">
                        <div class="scroller" id="scroller">
                            <div class="scroll__container" id="scroll__container">
                                <nav class="mdl-navigation">
                                    <a class="mdl-navigation__link mdl-navigation__link--current" href="manager">
                                        <i class="material-icons" role="presentation">home</i>
                                        Active Account
                                    </a>

                                    <a class="mdl-navigation__link " href="userrequest">
                                        <i class="material-icons">add_alert</i>
                                        Account Request
                                    </a>

                                    <a class="mdl-navigation__link " href="./form.jsp">
                                        <i class="material-icons">add_alert</i>
                                        Form
                                    </a>
                                    <div class="mdl-layout-spacer"></div>
                                    <hr>
                                    <a class="mdl-navigation__link " href="./form.jsp" disable>
                                        <i class="material-icons">add_alert</i>
                                        Form
                                    </a>

                                    <!-- <div class="mdl-layout-spacer"></div>
                                    <hr>
                                    <a class="mdl-navigation__link" href="https://github.com/CreativeIT/getmdl-dashboard">
                                        <i class="material-icons" role="presentation">link</i>
                                        GitHub
                                    </a> -->
                                </nav>
                            </div>
                        </div>
                        <div class='scroller__bar' id="scroller__bar"></div>
                    </div>
                </div>
                <main class="mdl-layout__content mdl-color--grey-100 ">
                    <div id="myPopup" class="popup">
                        <!-- Modal content -->
                        <div class="popup-content">
                        <jsp:include page="form.jsp"></jsp:include>
                        </div>
                    </div>

                    <div id="userList" class="mdl-grid ui-tables">
                        <form action="manager" method="POST" class="admin_form">
                            <div style="width: 100%" class="mdl-cell mdl-cell--6-col-desktop mdl-cell--6-col-tablet mdl-cell--4-col-phone">
                                <div class="mdl-card mdl-shadow--2dp">
                                    <div class="mdl-card__title">
                                        <h1 class="mdl-card__title-text">Manager Account</h1>
                                    </div>
                                    <div >
                                        <input class="searhInput" value="" type="text" name="searchUser"/>
                                        <button class="mdl-button mdl-js-button mdl-button--icon mdl-js-ripple-effect button--colored-teal">
                                            <i class="material-icons">search</i>
                                        </button>
                                    </div>
                                    <br/>
                                    <div class="mdl-card__supporting-text no-padding">
                                        <table class="mdl-data-table mdl-js-data-table stripped-table">
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
                                                <td class="mdl-data-table__cell--non-numeric"><%= index++ %></td>
                                                <td class="mdl-data-table__cell--non-numeric"><%= p.getFullName() %></td>
                                                <td class="mdl-data-table__cell--non-numeric"><%= p.getUserName() %></td>
                                                <td class="mdl-data-table__cell--non-numeric"><% if(p.isGender()){ %> Male  <%}else{ %>Female <%}%></td>
                                                <td class="mdl-data-table__cell--non-numeric"><%= p.getEmailAddress() %></td>
                                                <td class="mdl-data-table__cell--non-numeric"><%= p.getAddress() %></td>
                                                <td class="mdl-data-table__cell--non-numeric"><%= p.getPhoneNumber() %></td>
                                                <td class="mdl-data-table__cell--non-numeric"><%= p.getExpiryDate() %></td>
                                                <td class="mdl-data-table__cell--non-numeric"><%= p.getUseCount() %></td>
                                                <td class="mdl-data-table__cell--non-numeric">
                                                    <button name="<%= p.getUserName() %>" class="mdl-button mdl-js-button mdl-js-ripple-effect color-text--red">
                                                        <i class="material-icons">delete</i>
                                                        Delete
                                                    </button>
                                                    <button name="<%= p.getUserName() %>" onclick="showDialog();" class="mdl-button mdl-js-button mdl-js-ripple-effect color-text--green">
                                                        <i class="material-icons">create</i>
                                                        Edit
                                                    </button>
                                                </td>
                                            </tr>
                                            <%}%>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </form>
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
