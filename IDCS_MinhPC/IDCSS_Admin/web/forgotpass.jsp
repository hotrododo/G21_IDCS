<%-- 
    Document   : forgotpass
    Created on : Jan 22, 2020, 4:47:40 PM
    Author     : macbook
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Language" content="en">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Form Validation - Inline validation is very easy to implement using the Architect Framework.</title>
    <meta name="viewport"
        content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no" />
    <meta name="description" content="Inline validation is very easy to implement using the Architect Framework.">
    <meta name="msapplication-tap-highlight" content="no">
    <link href="./main.css" rel="stylesheet">
</head>

<body>
    <div class="app-container app-theme-white body-tabs-shadow">
        <div class="app-container">
            <div class="h-100">
                <div class="h-100 no-gutters row">
                    <div class="bgLogin d-none d-lg-block col-lg-4">
                    </div>
                    <div class="h-100 d-flex bg-white justify-content-center align-items-center col-md-12 col-lg-8">
                        <div class="mx-auto app-login-box col-sm-12 col-md-8 col-lg-6">
                            <!-- <div class="app-logo"></div> -->
                            <h4>
                                <div>Forgot your Password?</div>
                                <h6>Use the form below to recover it.</h6>
                            </h4>
                            <br/>
                            <div>
                                <form action="forgot" method="POST">
                                    <div class="form-row">
                                        <div class="col-md-12">
                                            <div class="position-relative form-group"><label for="exampleEmail"
                                                    class="">Email</label><input name="email" id="exampleEmail"
                                                    placeholder="Email here..." type="text" class="form-control" required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="mt-4 d-flex align-items-center">
                                        <h6 class="mb-0"><a href="login.jsp" class="text-primary">Sign in
                                                existing account</a>
                                        </h6>
                                        <div class="ml-auto">
                                            <button class="btn btn-primary btn-lg">Recover Password</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="./assets/scripts/main.js"></script>
</body>

</html>