<%-- 
    Document   : resultView
    Created on : Oct 9, 2019, 10:34:00 PM
    Author     : mac
--%>

<%@page import="CSTCopyright.IDCS.utils.MyUtils"%>
<%@page import="CSTCopyright.IDCS.controller.UserAccount"%>
<%@page import="CSTCopyright.IDCS.utils.FBUtils"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            UserAccount loginedUser = MyUtils.getLoginedUser(session);
            if (FBUtils.addAccount(loginedUser)) {
        %>
        <h1>Add Successed!</h1>
        <%
            }
        %>

    </body>
</html>
