<%-- 
    Document   : _header
    Created on : Oct 9, 2019, 12:17:26 AM
    Author     : mac
--%>

<%@page import="CSTCopyright.IDCS.utils.MyUtils"%>
<%@page import="CSTCopyright.IDCS.controller.UserAccount"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="menu_bar">
    <div class="logo">
        <h1>This is Logo area</h1>
    </div>
    <!-- Insert these scripts at the bottom of the HTML, but before you use any Firebase services -->

    <!-- Firebase App (the core Firebase SDK) is always required and must be listed first -->
    <script src="/__/firebase/7.2.3/firebase-app.js"></script>
    <script defer src="https://www.gstatic.com/firebasejs/7.2.3/firebase-app.js"></script>
    <!-- If you enabled Analytics in your project, add the Firebase SDK for Analytics -->
    <script src="/__/firebase/7.2.3/firebase-analytics.js"></script>

    <!-- Add Firebase products that you want to use -->
    <script src="/__/firebase/7.2.3/firebase-auth.js"></script>
    <script defer src="https://www.gstatic.com/firebasejs/7.2.3/firebase-auth.js"></script>

    <script src="/__/firebase/7.2.3/firebase-firestore.js"></script>
    <script defer src="https://www.gstatic.com/firebasejs/7.2.3/firebase-firestore.js"></script>

    <%
        HttpSession ss = request.getSession();
        UserAccount loginedUser = MyUtils.getLoginedUser(session);
        if (loginedUser != null) {%>
    <div class="user_content">
        <div class="icon_user">
            <img class="icon_user" src="../${pageContext.request.contextPath}/IMG/avatar.png" alt="User Avatar">
        </div>
        <div class="user_name">
            <div class="dropdown">
                <button class="dropbtn">${loginedUser.userName} 
                    <i class="fa fa-caret-down"></i>
                </button>
                <div class="dropdown-content">
                    <a href="#"><i class="fab fa-paypal"></i>Payment</a>
                    <a href="#"><i class="fas fa-address-book"></i>Contact</a>
                    <a href="${pageContext.request.contextPath}/signout"><i class="fas fa-sign-out-alt"></i>Sign Out</a>
                </div>
            </div>
        </div>
    </div>
    <%}%>

</div>
