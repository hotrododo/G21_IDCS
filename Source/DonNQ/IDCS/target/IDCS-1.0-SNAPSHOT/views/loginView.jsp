<%-- 
    Document   : loginView
    Created on : Oct 9, 2019, 2:52:36 AM
    Author     : mac
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login - Internet-connected Checking System</title>
        <link rel="stylesheet" type="text/css" href="../${pageContext.request.contextPath}/CSS/common.css"/>
        <script src="https://kit.fontawesome.com/1956e41869.js" crossorigin="anonymous"></script>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" href="../${pageContext.request.contextPath}/CSS/login.css">
    </head>
    <body class="bg">
        <jsp:include page="_header.jsp"></jsp:include>
        <div class="content_layout">
            <div class="container h-100">
                <div class="d-flex justify-content-center h-100">
                    <div class="d-flex justify-content-center form_container" >
                        <div class="user_card">
                            <form method="POST" action="login">
                                <div class="form-group input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                                    </div>
                                    <input value="${user.userName}" name="userName" class="form-control" placeholder="username" type="text" required>
                                </div> <!-- form-group// -->
                                <div class="form-group input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                                    </div>
                                    <input value="${user.password}" name="password"class="form-control" placeholder="password" type="password" required>
                                </div>
                                

                                <div class="form-group">
                                    <input value="Log in" type="submit" value= "Submit" class="btn btn-primary btn-block">
                                </div>    
                                <div class="mt-4">
                                    <div class="d-flex justify-content-center links">
                                        Don't have an account? <a href="Register.jsp" class="ml-2">Sign Up</a>
                                    </div>
                                    <div class="d-flex justify-content-center links">
                                        <a href="#">Forgot your password?</a>
                                    </div>
                                    <input type="checkbox" name="rememberMe" value= "Y" />
                                </div>                                                                
                            </form>

                        </div>
                    </div> 
                </div>
            </div>

        </div>

    </body>
</html>
