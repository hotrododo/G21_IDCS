<%-- 
    Document   : Informationuser
    Created on : Dec 18, 2019, 6:04:01 PM
    Author     : macbook
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>USER INFOMATION</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" type="image/png" href="..${pageContext.request.contextPath}/IMG/favicon.png">
    <link rel="stylesheet" href="..${pageContext.request.contextPath}/CSS/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="..${pageContext.request.contextPath}/CSS/fontawesome-free/css/all.min.css">
    <link href="..${pageContext.request.contextPath}/CSS/common.css" rel="stylesheet">
    <link rel="stylesheet" href="..${pageContext.request.contextPath}/CSS/style.css">
    <link rel="stylesheet" href="..${pageContext.request.contextPath}/CSS/mobie-css.css">
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
                <h1>USER INFOMATION</h1>
            </div>
            <form action="/action_page.php" class="needs-validation" novalidate>
                <div class="form-group">
                    <label class="login-input" for="uname"><i class="fas fa-user-circle"></i></label>
                    <input type="text" class="form-control" id="uname" placeholder="Full Name" name="uname" required>
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
                </div>
                <div class="form-group">
                    <label class="login-input" for="pwd"><i class="fas fa-envelope"></i></label>
                    <input type="email" class="form-control" id="pwd" placeholder="Email" name="pswd" required>

                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
                </div>

                <div class="form-group">
                    <label class="login-input" for="psphone"><i class="fas fa-mobile-alt"></i></label>
                    <input type="tel" class="form-control" id="psphone" placeholder="Phone number" name="psphone" required>

                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
                </div>

                <div class="form-group">
                    <label class="login-input" for="passw"><i class="fas fa-lock"></i></label>
                    <input type="password" class="form-control" id="passw" placeholder="Password" name="passw" required>

                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
                </div>
                <div class="form-group">
                    <label class="login-input" for="newpassw"><i class="fas fa-pencil-alt"></i></label>
                    <input type="password" class="form-control" id="newpassw" placeholder="New Password ( If you want )" name="newpassw" required>

                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
                </div>


                <div class="text-center">
                    <button type="submit" class="btn btn-primary btn_update_info"><i class="fas fa-sync"></i> UPDATE</button>
                    <a href="#" data-toggle="modal" data-target="#Modal_delUser" class="del_user"><i class="fas fa-trash-alt"></i> Delete User</a>
                </div>
            </form>
        </div>

    </main>
    <script src="..${pageContext.request.contextPath}/JS/jquery-confirm"></script>


</body>

</html>
