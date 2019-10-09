<%-- 
    Document   : Home
    Created on : Oct 6, 2019, 3:27:43 PM
    Author     : macbook
--%>
<%@ page import = "entities.*, java.util.*, java.io.*" %>
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
        <form action="LogoutServlet" method="POST">
            <h1 style="font-style: oblique; color: red">Wellcome <%=currentUser%></a></h1>
            <input type="submit" name="Logout" value="Logout">
        </form>

    </body>
</html>
