<%-- 
    Document   : ForgotPassword
    Created on : Oct 7, 2019, 10:47:32 PM
    Author     : macbook
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>IDCS Register</title>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" href="./css/forgotpass.css">
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
                        <hr>
                        <form method="POST" action="ForgotPassServlet">
                            <div class="form-group input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                                </div>
                                <input value="${user.username}" name="username" class="form-control" placeholder="username" type="text" required>
                            </div>
                            <div class="form-group input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"> <i class="fa fa-envelope"></i> </span>
                                </div>
                                <input value="${user.email}" name="email" class="form-control" placeholder="Email address" type="text" required>
                            </div> 

                            <div class="alert alert-danger" role="alert">
                                ${notif}
                            </div>

                            <div class="form-group">
                                <input value="Log in" type="submit" class="btn btn-primary btn-block">
                            </div>    

                        </form>

                    </div>
                </div> 
            </div>
        </div>
    </body>
</html>