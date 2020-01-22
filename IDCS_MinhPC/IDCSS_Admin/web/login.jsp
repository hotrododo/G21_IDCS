<%-- 
    Document   : login
    Created on : Jan 22, 2020, 4:45:04 PM
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
    <title>IDCS - LOGIN </title>
    <meta name="viewport"
        content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no" />
    <meta name="msapplication-tap-highlight" content="no">
    <link href="../${pageContext.request.contextPath}/main.css" rel="stylesheet">
</head>

<body>
    <div class="app-container app-theme-white body-tabs-shadow">
        <div class="app-container">
            <div class="h-100">
                <div class="h-100 no-gutters row">
                    <div class="bgLogin d-none d-lg-block col-lg-4">
                    </div>
                    <div class="h-100 d-flex bg-white justify-content-center align-items-center col-md-12 col-lg-8">
                        <div class="mx-auto app-login-box col-sm-12 col-md-10 col-lg-9">
                            <div class="app-logo"></div>
                            <h3 class="mb-0">
                                <span class="d-block">Welcome back,</span>
                                <h6>Please sign in to your account.</h6></h3>
                            <h6 class="mt-3">No account? <a href="registration.jsp" class="text-primary">Sign up
                                    now</a></h6>
                            <div class="divider row"></div>
                            <div>
                                <form action="login" method="POST">
                                    <div class="form-row">
                                        <div class="col-md-6">
                                            <div class="position-relative form-group"><label for="exampleEmail"
                                                    class="">Email</label><input value="${user.userName}" name="userName" id="exampleEmail"
                                                    placeholder="Email here..." type="email" class="form-control"></div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="position-relative form-group"><label for="examplePassword"
                                                    class="">Password</label><input value="${user.password}" name="password" id="examplePassword"
                                                    placeholder="Password here..." type="password" class="form-control">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="position-relative form-check"><input name="check" id="exampleCheck"
                                            type="checkbox" class="form-check-input"><label for="exampleCheck"
                                            class="form-check-label">Keep me logged in</label></div>
                                    <div class="divider row"></div>
                                    <div class="d-flex align-items-center">
                                        <div class="ml-auto"><a href="forgotpass.jsp"
                                                class="btn-lg btn btn-link">Recover Password</a>
                                                <button type="submit" class="btn btn-primary btn-lg">Login to Dashboard</button>
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
