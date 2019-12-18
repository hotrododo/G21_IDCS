<%-- 
    Document   : _header
    Created on : Oct 9, 2019, 12:17:26 AM
    Author     : mac
--%>

<%@page import="CSTCopyright.IDCS.model.Constant"%>
<%@page import="CSTCopyright.IDCS.model.UserAccount"%>
<%@page import="CSTCopyright.IDCS.utils.MyUtils"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="menu_bar">
    <%
        HttpSession ss = request.getSession();
        UserAccount loginedUser = MyUtils.getLoginedUser(session);
    %>
    <div class="col-sm-6 col-md-6">
        <div class="menu-logo-header">
            <%if (loginedUser.getUserType() == Constant.Admin) {%>
            <a href="..${pageContext.request.contextPath}/views/managerAcc.jsp" title="IDCS">
                <img class="logo-header" src="..${pageContext.request.contextPath}/IMG/logo_idcs.png" alt="logo" srcset="">
            </a>
            <%} else {%>
            <a href="..${pageContext.request.contextPath}/views/homeView.jsp" title="IDCS">
                <img class="logo-header" src="..${pageContext.request.contextPath}/IMG/logo_idcs.png" alt="logo" srcset="">
            </a>
        </div>
    </div>

    <div class="user_content">
        <div class="icon_user">
            <img class="icon_user" src="..${pageContext.request.contextPath}/IMG/avatar.png" alt="User Avatar">
        </div>
        <div class="user_name">
            <div class="dropdown">
                <button class="dropbtn">${loginedUser.userName} 
                    <i class="fa fa-caret-down"></i>
                </button>
                <div class="dropdown-content">
                    <a href="..${pageContext.request.contextPath}/views/payment.jsp"><i class="fab fa-paypal"></i>Payment</a>
                    <a href="..${pageContext.request.contextPath}/views/#"><i class="fas fa-address-book"></i>Contact</a>
                    <a href="${pageContext.request.contextPath}/signout"><i class="fas fa-sign-out-alt"></i>Sign Out</a>
                </div>
            </div>
        </div>
    </div>

    <%}%>
</div>
