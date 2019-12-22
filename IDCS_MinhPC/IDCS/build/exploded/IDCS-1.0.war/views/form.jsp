<%-- 
    Document   : form
    Created on : Dec 21, 2019, 5:32:14 PM
    Author     : macbook
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" type="image/png" href="../IMG/idcs_16x16.png">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- Add to homescreen for Chrome on Android -->
        <meta name="mobile-web-app-capable" content="yes">


        <!-- Add to homescreen for Safari on iOS -->
        <meta name="apple-mobile-web-app-capable" content="ye">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-title" content="IDCS Manager">
        <!-- SEO: If your mobile URL is different from the desktop URL, add a canonical link to the desktop page https://developers.google.com/webmasters/smartphone-sites/feature-phones -->
        <!--
        <link rel="canonical" href="http://www.example.com/">
        -->

        <link href='https://fonts.googleapis.com/css?family=Roboto:400,500,300,100,700,900' rel='stylesheet'
              type='text/css'>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!-- inject:css -->
        <link rel="stylesheet" href="../CSS/lib/getmdl-select.min.css">
        <link rel="stylesheet" href="../CSS/lib/nv.d3.min.css">
        <link rel="stylesheet" href="../CSS/application.min.css">
        <link rel="stylesheet" href="../CSS/style.css">
        <!-- endinject -->
    </head>
    <body>
        <div class="mdl-card mdl-shadow--2dp employer-form" action="#">
            <div class="mdl-card__title">
                <h2>User Infomation</h2>
                <span class="close closeBtn_form" onclick="hideDialog();">&times;</span>
            </div>

            <div class="mdl-card__supporting-text">
                <form action="#" class="form">
                    <div class="form__article">
                        <h3>Personal data</h3>

                        <div class="mdl-grid">
                            <div class="mdl-cell mdl-cell--6-col mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                                <input class="mdl-textfield__input" type="text" id="firstName" value="Luke"/>
                                <label class="mdl-textfield__label" for="firstName">First name</label>
                            </div>

                            <div class="mdl-cell mdl-cell--6-col mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                                <input class="mdl-textfield__input" type="text" id="secondName" value="Skywalker"/>
                                <label class="mdl-textfield__label" for="secondName">Second name</label>
                            </div>
                        </div>

                        <div class="mdl-grid">
                            <div class="mdl-cell mdl-cell--6-col mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                                <input class="mdl-textfield__input" type="text" id="birthday" value="25 May, 1977"/>
                                <label class="mdl-textfield__label" for="birthday">Birthday</label>
                            </div>

                            <div class="mdl-cell mdl-cell--6-col mdl-textfield mdl-js-textfield mdl-textfield--floating-label getmdl-select">
                                <input class="mdl-textfield__input" value="Male" type="text" id="gender" readonly tabIndex="-1"/>

                                <label class="mdl-textfield__label" for="gender">Gender</label>

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
                                    <input class="mdl-textfield__input" type="text" id="phone">
                                    <label class="mdl-textfield__label" for="phone">XXX-XX-XX</label>
                                </div>
                            </div>
                        </div>

                        <div class="mdl-grid">
                            <div class="mdl-cell mdl-cell--9-col input-group">
                                <i class="material-icons pull-left">mail_outline</i>

                                <div class="mdl-textfield mdl-js-textfield pull-left">
                                    <input class="mdl-textfield__input" type="text" id="email" value="luke@skywalker.com"/>
                                    <label class="mdl-textfield__label" for="email">Email</label>
                                </div>
                            </div>
                        </div>

                        <div class="mdl-grid">
                            <div class="mdl-cell mdl-cell--9-col input-group">
                                <i class="material-icons pull-left">place</i>

                                <div class="mdl-textfield mdl-js-textfield pull-left">
                                    <input class="mdl-textfield__input" type="text" id="address"/>
                                    <label class="mdl-textfield__label" for="address">Address</label>
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
                                    <input class="mdl-textfield__input" type="text" id="expiryDate"/>
                                    <label class="mdl-textfield__label" for="expiryDate">Expiry Date</label>
                                </div>
                            </div>
                        </div>
                        <div class="mdl-grid">
                            <div class="mdl-cell mdl-cell--9-col input-group">
                                <i class="material-icons pull-left">attach_money</i>

                                <div class="mdl-textfield mdl-js-textfield pull-left">
                                    <input class="mdl-textfield__input" type="text" id="money"/>
                                    <label class="mdl-textfield__label" for="money">Total Money</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--
                                                <div class="form__article employer-form__general_skills">
                                                    <h3>Other</h3>
                    
                                                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                                                        <textarea class="mdl-textfield__input" type="text" rows="3" id="Note"></textarea>
                                                        <label class="mdl-textfield__label" for="Note">Note</label>
                                                    </div>
                                                </div>-->

                    <div class="form__action">
                        <label class="mdl-checkbox mdl-js-checkbox mdl-js-ripple-effect" for="isInfoReliable">
                            <input type="checkbox" id="isInfoReliable" class="mdl-checkbox__input" required/>
                            <span class="mdl-checkbox__label">Entered information is reliable</span>
                        </label>
                        <button id="delBtn" class="mdl-button mdl-js-button button--colored-red">
                            <i class="material-icons">delete</i>
                            Delete
                        </button>
                        <button id="editBtn"class=" mdl-button mdl-js-button button--colored-light-blue">
                            <i class="material-icons">check_circle</i>
                            Update
                        </button>

                    </div>
                </form>
            </div>
        </div>
        <script src="../JS/d3.min.js"></script>
        <script src="../JS/getmdl-select.min.js"></script>
        <script src="../JS/material.min.js"></script>
        <script src="../JS/nv.d3.min.js"></script>
        <script src="../JS/layout.min.js"></script> 
        <script src="../JS/scroll.min.js"></script>
        <script src="../JS/table.min.js"></script> 
        <script src="../JS/js.js"></script> 
        <script src="../JS/employer-form.min.js"></script> 
    </body>
</html>
