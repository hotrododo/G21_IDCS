<%-- 
    Document   : resultView
    Created on : Oct 9, 2019, 10:34:00 PM
    Author     : mac
--%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <title>REGISTRATION</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" type="image/png" href="..${pageContext.request.contextPath}/IMG/favicon.png">
        <link rel="stylesheet" href="..${pageContext.request.contextPath}/CSS/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="..${pageContext.request.contextPath}/CSS/fontawesome-free/css/all.min.css">
        <link href="..${pageContext.request.contextPath}/CSS/common.css" rel="stylesheet">
        <link rel="stylesheet" href="..${pageContext.request.contextPath}/CSS/style.css">
        <link rel="stylesheet" href="..${pageContext.request.contextPath}/CSS/mobie-css.css">
    </head>

    <body class="page_result">
        <header class="header">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-sm-6 col-md-6">
                        <div class="logo">
                            <a href="/" title="IDCS">
                                <img src="..${pageContext.request.contextPath}/IMG/logo_idcsng" alt="logo" srcset=""/>
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

            <div class="box-result">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-3">
                            <div class="child-box-result">
                                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d29793.98038436799!2d105.81945407847766!3d21.022778763266096!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3135ab9bd9861ca1%3A0xe7887f7b72ca17a9!2zSMOgIE7hu5lpLCBIb8OgbiBLaeG6v20sIEjDoCBO4buZaSwgVmnhu4d0IE5hbQ!5e0!3m2!1svi!2s!4v1574326595861!5m2!1svi!2s"
                                        width="100%" height="250" frameborder="0" style="border:0;" allowfullscreen=""></iframe>
                                <h4><i class="fas fa-globe-americas"></i> 129.168.1.1</h4>
                                <ul>
                                    <li>
                                        City
                                    </li>
                                    <li>
                                        Country
                                    </li>
                                    <li>
                                        Orgranzation
                                    </li>
                                    <li>
                                        ISP
                                    </li>
                                    <li>
                                        Last Update
                                    </li>
                                    <li>
                                        ASN
                                    </li>
                                </ul>
                                <h4>
                                    WEB TECHNOLOGIES
                                </h4>
                                <ul>
                                    <li>
                                        Bootstrap
                                    </li>
                                    <li>
                                        Google font API
                                    </li>
                                    <li>
                                        Jquery
                                    </li>
                                </ul>
                            </div>

                        </div>
                        <div class="col-md-9">
                            <div class="box_search_result">
                                <input type="search" placeholder="Find in the text..." name="" id=""/>
                                <label><i class="fa fa-search" aria-hidden="true"></i></label>
                            </div>
                            <div class="child-box-result">
                                <h4><i class="fas fa-exclamation-triangle"></i> PORT</h4>
                                <div class="list_port">
                                    <a>22</a>
                                    <a>21</a>
                                    <a>80</a>
                                    <a>389</a>
                                    <a>433</a>
                                    <a>1883</a>
                                    <a>8080</a>
                                </div>

                            </div>
                            <div>
                                <h4><i class="fas fa-list"></i> SERVICE</h4>
                                <p style="color: red;">${info}</p>
                            </div>
                        </div>
                    </div>

                </div>
            </div>


        </main>
        <footer>
            <jsp:include page="_footer.jsp"></jsp:include>
        </footer>

        <script src="js/jquery-3.4.1.min.js"></script>
        <script src="js/bootstrap/bootstrap.min.js"></script>


    </body>

</html>
