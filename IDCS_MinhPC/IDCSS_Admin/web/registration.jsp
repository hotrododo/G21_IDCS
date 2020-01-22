<%-- 
    Document   : registration
    Created on : Jan 22, 2020, 4:48:38 PM
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
                    <div
                        class="h-100 d-md-flex d-sm-block bg-white justify-content-center align-items-center col-md-12 col-lg-7">
                        <div class="mx-auto app-login-box col-sm-12 col-md-10 col-lg-9">
                            <div class="app-logo"></div>
                            <h4>
                                <div>Welcome,</div>
                                <h6>It only takes a <span class="text-success">few seconds</span> to create your
                                    account</h6>
                            </h4>
                            <div>
                                <form class="">
                                    <div class="form-row">
                                        <div class="col-md-6">
                                            <div class="position-relative form-group"><label for="Email" class=""><span
                                                        class="text-danger">*</span> Email</label><input name="email"
                                                    id="Email" placeholder="Email here..." type="email"
                                                    class="form-control"></div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="position-relative form-group"><label for="Name" class=""><span
                                                        class="text-danger">*</span> Name</label><input name="text"
                                                    id="Name" placeholder="Name here..." type="text"
                                                    class="form-control">
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="position-relative form-group"><label for="Account"
                                                    class=""><span class="text-danger">*</span> Account</label><input
                                                    name="Account" id="Account" placeholder="Account here..."
                                                    type="text" class="form-control"></div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="position-relative form-group">
                                                <label for="phoneNum" class=""><span class="text-danger">*</span>
                                                    Phone</label>
                                                <div class="input-group">
                                                    <div class="input-group-prepend"><span
                                                            class="input-group-text">+84</span></div>
                                                    <input name="phoneNumDialog" id="phoneNum"
                                                        placeholder="Phone Number" type="text" class="form-control">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="position-relative form-group"><label for="Password"
                                                    class=""><span class="text-danger">*</span> Password</label><input
                                                    name="password" id="Password" placeholder="Password here..."
                                                    type="password" class="form-control"></div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="position-relative form-group"><label for="repeatPass"
                                                    class=""></label>
                                                Repeat Password</label>
                                                <input name="repeatPass" id="repeatPass"
                                                    placeholder="Repeat password here..." type="password"
                                                    class="form-control">
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="position-relative form-group"><label for="Address"
                                                    class=""><span class="text-danger">*</span> Address</label><input
                                                    name="email" id="Address" placeholder="Address here..." type="text"
                                                    class="form-control"></div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="position-relative form-group">
                                                <label for="Select" class="">Select</label>
                                                <select name="select" id="Select" class="form-control">
                                                    <option>Male</option>
                                                    <option>Female</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="mt-3 position-relative form-check"><input name="check" id="Check"
                                            type="checkbox" class="form-check-input"><label for="Check"
                                            class="form-check-label">Accept our <a href="javascript:void(0);">Terms
                                                and Conditions</a>.</label></div>
                                    <div class="mt-4 d-flex align-items-center">
                                        <h5 class="mb-0">Already have an account? <a href="login.jsp"
                                                class="text-primary">Sign in</a></h5>
                                        <div class="ml-auto">
                                            <button
                                                class="btn-wide btn-pill btn-shadow btn-hover-shine btn btn-primary btn-lg">Create
                                                Account</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="bgLogin d-lg-flex d-xs-none col-lg-5"></div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="./assets/scripts/main.js"></script>
</body>

</html>
