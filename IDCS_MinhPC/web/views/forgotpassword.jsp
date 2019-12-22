<%-- 
    Document   : forgotpassword
    Created on : Dec 16, 2019, 3:34:39 PM
    Author     : macbook
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>LOGIN FORM</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" type="image/png" href="./image/favicon.png">
        <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="css/fontawesome-free/css/all.min.css">
        <link href="css/common.css" rel="stylesheet">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/mobie-css.css">
    </head>

    <body class="page_login">
        <header class="header">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-sm-6 col-md-6">
                        <div class="logo">
                            <a href="/" title="FPT University">
                                <img src="image/logo_fpt.png" alt="logo" srcset="">
                            </a>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-6 text-right">
                        <a href="homepage.html" class="home_page"><i class="fas fa-home"></i> HOME PAGE</a>
                    </div>
                </div>
            </div>

        </header>
        <main>
            <div class="login_form">
                <div class="title-login">
                    <h1><i class="fas fa-key"></i> FORGOT PASSWORD ?</h1>
                </div>
                <form action="/action_page.php" class="needs-validation" novalidate>
                    <div class="form-group">
                        <label class="login-input" for="uname"><i class="fas fa-user-circle"></i></label>
                        <input type="text" class="form-control" id="uname" placeholder="Enter Your Name" name="uname" required>
                        <div class="valid-feedback">Valid.</div>
                        <div class="invalid-feedback">Please fill out this field.</div>
                    </div>
                    <div class="form-group">
                        <label class="login-input" for="pwd"><i class="fas fa-envelope"></i></label>
                        <input type="email" class="form-control" id="pwd" placeholder="Enter Your Email" name="pswd" required>

                        <div class="valid-feedback">Valid.</div>
                        <div class="invalid-feedback">Please fill out this field.</div>
                    </div>
                    <div class="notification">
                        <p>We will send new password to your email.</p>
                        <p>Please check your email in 24 hours.</p>
                    </div>

                    <button type="submit" class="btn btn-primary submit_login">SUBMIT</button>
                </form>
            </div>

        </main>

        <footer>
            <jsp:include page="_footer.jsp"></jsp:include>
        </footer>


        <script src="js/jquery-3.4.1.min.js"></script>
        <script src="js/bootstrap/bootstrap.min.js"></script>

    </body>

</html>