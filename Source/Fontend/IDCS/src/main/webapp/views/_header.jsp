<%-- 
    Document   : _header
    Created on : Oct 9, 2019, 12:17:26 AM
    Author     : mac
--%>

<%@page import="CSTCopyright.IDCS.utils.MyUtils"%>
<%@page import="CSTCopyright.IDCS.model.UserAccount"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="menu_bar">
    <div class="menu-logo-header">
        <a href="/" title="IDCS">
            <img class="logo-header" src="../${pageContext.request.contextPath}/IMG/logo_idcs.png" alt="logo" srcset="">
        </a>
    </div>
    <div class="home-navi">
        <form method="POST" action="userinfo">
            <button class="btn-user-name">
                <img src="../${pageContext.request.contextPath}/IMG/premium-avatar.png">
                <p>${loginedUser.userName}</p>
            </button>
        </form>
        <div class="home-logout">
            <a href="${pageContext.request.contextPath}/signout">
                Logout
                <i class="fas fa-sign-out-alt"></i>
            </a>
        </div>
    </div>
</div>
