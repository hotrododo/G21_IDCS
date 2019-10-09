<%-- 
    Document   : Registerr
    Created on : Oct 7, 2019, 1:37:13 PM
    Author     : macbook
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>IDCS Register</title>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" href="./css/register.css">
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
                        <div class="notif_div"><c:if test="${isValid eq false}">
                                <p>${notif} </p>
                            </c:if> 
                        </div>
                        <hr>
                        <hr>
                        <hr>
                        <form action="RegisterServlet" method="POST">
                            <c:choose>
                                <c:when test="${fullname ne null}">
                                    <div class="alert alert-success" style="padding: .3rem 1.1rem; float: left;" role="alert">
                                        ${notif}
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="alert alert-warning" style="padding: .3rem 1.1rem;float: left;" role="alert">
                                        ${notif}
                                    </div>
                                </c:otherwise>
                            </c:choose>
                            <div class="form-group input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                                </div>
                                <input value="${user.fullname}" name="fullname" class="form-control" placeholder="Full name" type="text" required>
                            </div> 
                            <div class="form-group input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"> <i class="fa fa-envelope"></i> </span>
                                </div>
                                <input value="${user.email}" name="email" class="form-control" placeholder="Email address" type="email" required>
                            </div>
                            <div class="form-group input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"> <i class="fa fa-phone"></i> </span>
                                </div>
                                <input value="${user.phone}" name="phone" class="form-control" placeholder="Phone number" type="text" required>
                            </div> 
                            <div class="form-group input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                                </div>
                                <input  name="password" class="form-control" placeholder="Password" type="password" required>
                            </div>
                            <div class="form-group input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                                </div>
                                <input class="form-control" name="repassword" placeholder="Repeat Password" type="password" required>
                            </div>  
                            <div class="form-group">
                                <input value="Create Account" type="submit" class="btn btn-primary btn-block">
                            </div>

                            <div class="mt-4" style="margin-bottom:15px !important;">
                                <div class="d-flex justify-content-center links">
                                    Have an account? <a href="login.jsp" class="ml-2">Log in</a>
                                </div>
                            </div>                                                                
                        </form>

                    </div>
                </div> 
            </div>
        </div>
    </body>
</html>