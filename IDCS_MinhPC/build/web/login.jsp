<%-- 
    Document   : login
    Created on : Oct 5, 2019, 5:41:19 PM
    Author     : macbook
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.UserBean" %>
<!DOCTYPE html>
<html>
    <head>
        <title>IDCS Register</title>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" href="./css/login.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div class="container h-100">
            <div class="d-flex justify-content-center h-100">
                <div class="d-flex justify-content-center form_container" >
                    <div class="user_card">
                        <div class="d-flex justify-content-center">
                            <div class="brand_logo_container">
                                <img src="https://cdn.freebiesupply.com/logos/large/2x/pinterest-circle-logo-png-transparent.png" class="brand_logo" alt="Logo">
                            </div>
                        </div>
                        <hr><hr>
                        <hr>
                        <form method="POST" action="loginServlet">

                            <div class="form-group input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                                </div>
                                <input value="${user.username}" name="username" class="form-control" placeholder="username" type="text" required>
                            </div> <!-- form-group// -->
                            <div class="form-group input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                                </div>
                                <input value="${user.password}" name="password"class="form-control" placeholder="password" type="password" required>
                            </div>
                                <%--<c:if test="${isValid eq false}">
                                <div class="alert alert-danger" role="alert">
                                    ${notif}
                                </div>
                            </c:if>--%>

                            <div class="form-group">
                                <input value="Log in" type="submit" class="btn btn-primary btn-block">
                            </div>    
                            <div class="mt-4">
                                <div class="d-flex justify-content-center links">
                                    Don't have an account? <a href="Register.jsp" class="ml-2">Sign Up</a>
                                </div>
                                <div class="d-flex justify-content-center links">
                                    <a href="#">Forgot your password?</a>
                                </div>
                            </div>                                                                
                        </form>

                    </div>
                </div> 
            </div>
        </div>
    </body>
</html>