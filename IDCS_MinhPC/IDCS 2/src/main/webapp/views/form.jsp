<%-- 
    Document   : form
    Created on : Dec 21, 2019, 5:32:14 PM
    Author     : macbook
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="CSTCopyright.IDCS.model.UserAccount"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" type="image/png" href="../${pageContext.request.contextPath}/IMG/idcs_16x16.png">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- Add to homescreen for Chrome on Android -->
        <meta name="mobile-web-app-capable" content="yes">


        <!-- Add to homescreen for Safari on iOS -->
        <meta name="apple-mobile-web-app-capable" content="ye">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-title" content="IDCS Manager">

        <link href='https://fonts.googleapis.com/css?family=Roboto:400,500,300,100,700,900' rel='stylesheet'
              type='text/css'>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!-- inject:css -->
        <link rel="stylesheet" href="../${pageContext.request.contextPath}/CSS/lib/getmdl-select.min.css">
        <link rel="stylesheet" href="../${pageContext.request.contextPath}/CSS/lib/nv.d3.min.css">
        <link rel="stylesheet" href="../${pageContext.request.contextPath}/CSS/application.min.css">
        <link rel="stylesheet" href="../${pageContext.request.contextPath}/CSS/style.css">
        <!-- endinject -->
    </head>
    <body>
        <input type="text" id="username" name="username" hidden>
        <div class="mdl-card mdl-shadow--2dp employer-form" >
            <div class="mdl-card__title">
                <h2>User Infomation</h2>
                <span class="close closeBtn_form" onclick="hideDialog();">&times;</span>
            </div>
                
                
            <div class="mdl-card__supporting-text">
                <div class="form__article">
                    <h3>Personal data</h3>
                    <div class="mdl-grid">
                        <div class="mdl-cell mdl-cell--9-col input-group">
                            <i class="material-icons pull-left">account_circle</i>

                            <div class="mdl-textfield mdl-js-textfield pull-left">
                                <input class="mdl-textfield__input" name="fullname" type="text" id="fullname" value=""/>
                            </div>
                        </div>
                    </div>
                    <div class="mdl-grid">
                        <div class="mdl-cell mdl-cell--6-col mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                            <input class="mdl-textfield__input" type="text" id="birthday" value="birthday"/>
                        </div>

                        <div class="mdl-cell mdl-cell--6-col mdl-textfield mdl-js-textfield mdl-textfield--floating-label getmdl-select">
                            <input class="mdl-textfield__input" value="" name="gender" type="text" id="gender" readonly tabIndex="-1"/>

                            <ul class="mdl-menu mdl-menu--bottom-left mdl-js-menu dark_dropdown" for="gender">
                                <li class="mdl-menu__item">Male</li>
                                <li class="mdl-menu__item">Female</li>
                            </ul>

                            <label for="gender">
                                <i class="mdl-icon-toggle__label material-icons">arrow_drop_down</i>
                            </label>
                        </div>
                    </div>
                </div>

                <div class="form__article employer-form__contacts">
                    <h3>Contacts</h3>

                    <div class="mdl-grid">
                        <div class="mdl-cell mdl-cell--9-col input-group">
                            <i class="material-icons pull-left">call</i>

                            <div class="mdl-textfield mdl-js-textfield pull-left">
                                <input class="mdl-textfield__input" type="text" id="phone" name="phone">
                            </div>
                        </div>
                    </div>

                    <div class="mdl-grid">
                        <div class="mdl-cell mdl-cell--9-col input-group">
                            <i class="material-icons pull-left">mail_outline</i>

                            <div class="mdl-textfield mdl-js-textfield pull-left">
                                <input class="mdl-textfield__input" name="email" type="text" id="email" value=""/>
                            </div>
                        </div>
                    </div>

                    <div class="mdl-grid">
                        <div class="mdl-cell mdl-cell--9-col input-group">
                            <i class="material-icons pull-left">place</i>

                            <div class="mdl-textfield mdl-js-textfield pull-left">
                                <input class="mdl-textfield__input" type="text" id="address" name="address"/>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="form__article employer-form__contacts">
                    <h3>IDCS Member</h3>

                    <div class="mdl-grid">
                        <div class="mdl-cell mdl-cell--9-col input-group">
                            <i class="material-icons pull-left">update</i>

                            <div class="mdl-textfield mdl-js-textfield pull-left">
                                <input class="mdl-textfield__input" name="expiryDate" type="text" id="expiryDate"/>
                            </div>
                        </div>
                    </div>
                    <div class="mdl-grid">
                        <div class="mdl-cell mdl-cell--9-col input-group">
                            <i class="material-icons pull-left">memory</i>

                            <div class="mdl-textfield mdl-js-textfield pull-left">
                                <input class="mdl-textfield__input" type="text" id="usecount" name="usecount"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form__action">
                    <label class="mdl-checkbox mdl-js-checkbox mdl-js-ripple-effect" for="isInfoReliable">
                        <input type="checkbox" id="isInfoReliable" class="mdl-checkbox__input" required/>
                        <span class="mdl-checkbox__label">Entered information is reliable</span>
                    </label>
                    <button type="submit" value="update" name="btn" id="editBtn"class=" mdl-button mdl-js-button button--colored-light-blue">
                        <i class="material-icons">check_circle</i>
                        Update
                    </button>

                </div>
            </div>
        </div>
        <!-- inject:js -->
        <script src="../${pageContext.request.contextPath}/JS/d3.min.js"></script>
        <script src="../${pageContext.request.contextPath}/JS/getmdl-select.min.js"></script>
        <script src="../${pageContext.request.contextPath}/JS/material.min.js"></script>
        <script src="../${pageContext.request.contextPath}/JS/nv.d3.min.js"></script>
        <script src="../${pageContext.request.contextPath}/JS/layout.min.js"></script> 
        <script src="../${pageContext.request.contextPath}/JS/scroll.min.js"></script>
        <script src="../${pageContext.request.contextPath}/JS/table.min.js"></script> 
        <script src="../${pageContext.request.contextPath}/JS/javascript.js"></script> 
        <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.4.1.min.js"></script>
    </body>
</html>
