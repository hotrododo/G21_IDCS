<%-- 
    Document   : userInfoView
    Created on : Dec 21, 2019, 4:36:29 AM
    Author     : mac
--%>
<%@page import="java.util.concurrent.TimeUnit"%>
<%@page import="java.sql.Date"%>
<%@page import="CSTCopyright.IDCS.model.UserAccount"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User self-manage </title>
        <link rel="stylesheet" href="../${pageContext.request.contextPath}/CSS/userInfo.css">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <link rel="stylesheet" href="../${pageContext.request.contextPath}/CSS/bootstrap.min.css">
        <link rel="stylesheet" href="../${pageContext.request.contextPath}/CSS/bootstrap.css">
        <script src="https://kit.fontawesome.com/a03e6bb007.js" crossorigin="anonymous"></script>
        <script src="../${pageContext.request.contextPath}/JS/javascript.js"></script>
        <!--        <script src="https://code.jquery.com/jquery-3.4.1.min.js" ></script>-->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    </head>
    <%
        UserAccount user = (UserAccount) request.getAttribute("user");
        String uds = (String) request.getAttribute("uds");
        String mess = (String) request.getAttribute("mess");
    %>
    <body onload="blackColor(${uds},${mess})">
        <div class="container-fluid">
            <div class="background-layout">
                <div class="row">
                    <div class="col-xs-6 col-md-3 min-vh-100 left-menu-layout">
                        <div class="left-menu">
                            <div class="left-menu-header text-center">
                                <div class="left-menu-logo">
                                    <form action="/home" method="POST">
                                        <button>
                                            <img src="../${pageContext.request.contextPath}/IMG/logo_idcs.png">
                                        </button>
                                    </form>
                                </div>
                                <div class="left-menu-profiles">
                                    <img src="../${pageContext.request.contextPath}/IMG/premium-avatar.png">
                                </div>
                                <div class="left-menu-user-full-names">
                                    <p><%=user.getFullName()%></p>
                                </div>
                            </div>
                            <div class="left-menu-body">
                                <div id="navShow" class="left-menu-navi">
                                    <p>User Dasboard</p>
                                    <a value="1" onclick="userinfo()">User Info</a>
                                    <a value="2" onclick="payment()">Payment</a>
                                    <a value="3" onclick="contact()">Contact</a>
                                    <a value="4" onclick="help()">Help</a>
                                </div>
                            </div>
                            <div class="left-menu-footer text-center">
                                <p>©IDCS 2019</p>
                                <p>IDCS Web tool 1.0</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-9 user-body-layout">
                        <div class="row header-bar">
                            <div class="header-bar-accessible">
                                <form method="POST" action="/signout">
                                    <button class="btn-hide-side-menu"><i class="fas fa-eye-slash"></i></button>
                                    <button type="submit" class="col-md-2 btn-header-logout"><i class="fas fa-sign-out-alt"></i>Logout</i></button>
                                </form>
                            </div>
                        </div>
                        <div class="row user-label-direct">
                            <div class="col-md-6 user-label-menu">
                                <div class="row dirShow" id="dirShow">
                                    <h4>User Info</h4>
                                    <p>Change Password</p>
                                </div>
                            </div>
                            <div class="col-md-6 user-edit-profile">
                                <button id="btn-disabled-fiels"><i class="fas fa-edit"></i>Edit Profile</button>
                            </div>
                        </div>
                        <div class="row user-content-layout">
                            <%
                                if (mess != null || !mess.equals("")) {
                            %>
                            <p class="label-message"><%=mess%></p>
                            <%
                                }
                            %>

                            <div class="user-content-form">
                                <div class="col-md-4 user-profiles">
                                    <img src="../${pageContext.request.contextPath}/IMG/premium-avatar.png">
                                    <p><%=user.getFullName()%></p>
                                </div>
                                <div class="col-md-7">
                                    <!----FORM STAY HERE---->
                                    <form method="POST" action="userupdate" class="needs-validation dis-1 layer">
                                        <div class="row user-infomation-tab dis-1">
                                            <div class="col-md-12 dis-1">
                                                <div class="row user-info-tab-row dis-1">
                                                    <div class="col-md-4 dis-1">
                                                        <p>UserID:</p>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <p><%=user.getUserName()%></p>
                                                    </div>
                                                </div>
                                                <div class="row user-info-tab-row">
                                                    <div class="col-md-4">
                                                        <p>Email:</p>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input id="txt1" name="txt1" value="<%=user.getEmailAddress()%>" disabled="true" required>
                                                    </div>
                                                </div>
                                                <div class="row user-info-tab-row">
                                                    <div class="col-md-4">
                                                        <p>Full Name:</p>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input name="txt2" value="<%=user.getFullName()%>" disabled="true" required>
                                                    </div>
                                                </div>
                                                <div class="row user-info-tab-row">
                                                    <div class="col-md-4">
                                                        <p>Phone Number:</p>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input name="txt3" value="<%=user.getPhoneNumber()%>" disabled="true" required>
                                                    </div>
                                                </div>
                                                <div class="row user-info-tab-row">
                                                    <div class="col-md-4">
                                                        <p>Address:</p>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input name="txt4" value="<%=user.getAddress()%>" disabled="true" required>
                                                    </div>
                                                </div>
                                                <div class="row user-info-tab-row">
                                                    <div class="col-md-4">
                                                        <p>Gender</p>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <%
                                                            if (user.getGender() == 1) {
                                                        %>
                                                        <p>Male</p>
                                                        <%
                                                        } else {
                                                        %>
                                                        <p>Female</p>
                                                        <%}%>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row user-usage-tab">
                                            <div class="col-md-12">
                                                <div class="row user-info-tab-row">
                                                    <div class="col-md-4">
                                                        <p>Credits:</p>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <p>You have <%=user.getCredits()%> time per month</p>
                                                    </div>
                                                </div>
                                                <div class="row user-info-tab-row">
                                                    <div class="col-md-4">
                                                        <p>User Type:</p>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <%
//                                                            long millis = System.currentTimeMillis();
//                                                            long expDate = user.getExpiryDate().getTime();
//                                                            long dateScan = TimeUnit.DAYS.convert(expDate - millis, TimeUnit.MILLISECONDS);
//                                                        %>
                                                        <p><%=user.getUserType()%> days left</p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row btn-update">
                                            <button type="submit">Save</button>
                                        </div>
                                    </form>
                                    <!----FORM GONE!---->
                                    <!----FORM 5---->
                                    <form method="POST" action="passwordupdate" class="change-password-form dis-5 layer">
                                        <div class="change-password-form">
                                            <div class="row text-user-name th-35">
                                                <a class="col-md-6">User Name:</a>
                                                <a class="col-md-6">Admin</a>
                                            </div>
                                            <div class="row text-user-old-pass th-35">
                                                <a class="col-md-6">Old Password:</a>
                                                <input name="txt-old-pass" type="password" class="col-md-6">
                                            </div>
                                            <div class="row text-user-new-pass th-35">
                                                <a class="col-md-6">New Password:</a>
                                                <input name="txt-new-pass" type="password" class="col-md-6" value="">

                                            </div>
                                            <div class="row text-user-re-pass th-35">
                                                <a class="col-md-6">ReEnter New Password:</a>
                                                <input name="txt-re-pass" type="password" class="col-md-6">
                                            </div>
                                        </div>
                                        <button type="submit">Change Password</button>
                                    </form> 
                                    <!----END FORM 5---->
                                    <!----FORM 2---->
                                    <div class="payment dis-2 layer">
                                        <div class="row for-sale">
                                            <div class="col-md-12">
                                                <div class="row for-sale-title">
                                                    <p class="col-md-6">Cost</p>
                                                    <p class="col-md-6">Expiry Days</p>
                                                </div>
                                                <div class="row">
                                                    <p class="col-md-6">$1.99</p>
                                                    <p class="col-md-6">10 times/3 days</p>
                                                </div>
                                                <div class="row">
                                                    <p class="col-md-6">$9.99</p>
                                                    <p class="col-md-6">100 times/30 days</p>
                                                </div>
                                                <div class="row">
                                                    <p class="col-md-6">$49.99</p>
                                                    <p class="col-md-6">3000 times/1 year</p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row sale-info">
                                            <div>
                                                <div class="row">
                                                    <p>If you pay by bank transfer, please use one of the following accounts:</p>
                                                </div>
                                                <div class="row VISA-Account">
                                                    <div class="col-md-12">
                                                        <div class="row">
                                                            <p class="col-md-4">Account Number</p>
                                                            <p class="col-md-4">Account Name</p>
                                                            <p class="col-md-4">Bank</p>
                                                        </div>
                                                        <div class="row">
                                                            <p class="col-md-4">101001223460</p>
                                                            <p class="col-md-4">IDCS Company</p>
                                                            <p class="col-md-4">Vietinbank</p>
                                                        </div>
                                                        <div class="row">
                                                            <p class="col-md-4">102200330440</p>
                                                            <p class="col-md-4">IDCS Company</p>
                                                            <p class="col-md-4">Paypal</p>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row payment-guide">
                                                    <a>Please transfer with the message:</a>
                                                    <br>
                                                    <a>IDCS-[your account] for example: IDCS-johnny12</a>
                                                    <br>
                                                    <a>Best regards!</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!----END FORM 2---->
                                    <!----FORM 3---->
                                    <div class="contact dis-3 layer">
                                        <p>Internet-connected Devices Checking System - is a security tool for early detection of vulnerabilities inside your server system.</p>
                                        <p>IDCS is designed to provide security benefits with powerful scanning technologies within a simple interface. Our tool will help system administrators to detect security holes early, providing solutions to troubleshoot system problems.</p>
                                        <p>Any suggestions, questions, or problem reports, please send mail to the email address: support@idcs.com</p>
                                        <p>Hotline phone number: +84 35800 8888</p>
                                        <p>Office address: Hoa Lac Hi-Tech Park, Thang Long Highways, Hanoi, Vietnam</p>
                                    </div>
                                    <!----END FORM 3---->
                                    <!----FORM 4---->
                                    <div class="help dis-4 layer">
                                        <!--HELP-->
                                    </div>
                                    <!----END FORM 4---->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
