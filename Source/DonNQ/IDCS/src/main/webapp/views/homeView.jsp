<%-- 
    Document   : homeView
    Created on : Oct 7, 2019, 2:37:22 AM
    Author     : mac
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Internet-connected Checking System</title>
        <link rel="stylesheet" type="text/css" href="../${pageContext.request.contextPath}/CSS/common.css"/>
        <script src="https://kit.fontawesome.com/1956e41869.js" crossorigin="anonymous"></script>
    </head>
    <body class="bg">
        <jsp:include page="_header.jsp"></jsp:include>
        <div class="content_layout">
            <div class="execute_content">
                <div class="input-area">
                    <div class="txt_input_domain">
                        <input class="txt_input" placeholder="Enter your site..."/>
                        <button class="btn-domain"></button>
                    </div>
                </div>
            </div>
            <div class="ext_func">
                <table class="table-func">
                    <tr>
                        <th>Last check</th>
                        <th>Top Check</th>
                        <th>View Report</th>
                        <th>Export Report</th>
                    </tr>
                    <tr>
                        <td class="td-domain-name"><a href="">${list[0].domain}</a></td>
                        <td class="td-domain-name"><a href="">${list[1].domain}</a></td>
                        <td class="td-domain-name"><a href="">Yahoo.com</a></td>
                        <td class="td-domain-name"><a href="">Twitter.com</a></td>
                    </tr>
                    <tr>
                        <td class="td-domain-service">${list[0].services}</td>
                        <td class="td-domain-service">${list[1].services}</td>
                        <td class="td-domain-service">Lois</td>
                        <td class="td-domain-service">Griffin</td>
                    </tr>
                </table>
            </div>
        </div>

    </body>
</body>
</html>
