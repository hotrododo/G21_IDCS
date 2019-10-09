<%-- 
    Document   : Home
    Created on : Oct 6, 2019, 3:27:43 PM
    Author     : macbook
--%>
<%@ page import = "entities.UserBean" %>
<%@ page import = "java.io.*,java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IDCS Home</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            String currentUser = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("currentUser")) {
                        currentUser = cookie.getValue();
                    }
                }
            }
        %>
        <h1 style="font-style: oblique; color: red"><a href="LogoutServlet?logout=ok">Wellcome <%=currentUser%></a></h1>
        <h3></h3>
    </body>
</html>
