<%-- 
    Document   : _header
    Created on : Oct 9, 2019, 12:17:26 AM
    Author     : mac
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="menu_bar">
    <div class="logo">
        <h1>This is Logo area</h1>
    </div>
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
</div>
