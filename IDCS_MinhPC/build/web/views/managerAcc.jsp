<%-- 
    Document   : managerAcc
    Created on : Dec 16, 2019, 3:38:34 PM
    Author     : macbook
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>ADMINSTRATOR PAGE</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" type="image/png" href="..${pageContext.request.contextPath}/IMG/favicon.png">
        <link rel="stylesheet" href="..${pageContext.request.contextPath}/CSS/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="..${pageContext.request.contextPath}/CSS/fontawesome-free/css/all.min.css">
        <link href="..${pageContext.request.contextPath}/CSS/common.css" rel="stylesheet">
        <link rel="stylesheet" href="..${pageContext.request.contextPath}/CSS/style.css">
        <link rel="stylesheet" href="..${pageContext.request.contextPath}/CSS/mobie-css.css">
        <link rel="stylesheet" href="..${pageContext.request.contextPath}/CSS/jquery-confirm.css">
    </head>

    <body>
        <header class="header">
            <jsp:include page="_header.jsp"></jsp:include>

            </header>
            <main>
                <form action="manager" method="POST" id="form">
                    <div class="box-page-manager">
                        <div class="container">
                            <div class="box-search-manager">
                                <input type="search" name="" placeholder="Enter Your ID or Email" id="search-mangager" class="search-mangager">
                                <button type="submit" class="btn-search-mangager"><i class="fas fa-search"></i> SEARCH</button>
                            </div>
                        </div>
                    </div>

                    <div class="box-content-manager">
                        <div class="container">
                            <h1 class="text-center"><i class="far fa-user-circle"></i> MANAGER USER</h1>
                            <div class="border-table">

                                <table class="table">
                                    <thead class="thead-dark">
                                        <tr>
                                            <th class="w-20">
                                                No.
                                            </th>
                                            <th>
                                                User Name
                                            </th>
                                            <th>
                                                Full Name
                                            </th>
                                            <th>
                                                Payment Date
                                            </th>
                                            <th>
                                                Phone Number
                                            </th>
                                            <th>
                                                Email
                                            </th>
                                            <th>
                                                Status
                                            </th>
                                        </tr>

                                    </thead>
                                    <tbody>

                                    <c:forEach var = "user" items="${listUser}" varStatus="num">
                                        <tr>
                                            <td>
                                                ${num.count}
                                            </td>
                                            <td>
                                                ${user.userName}
                                            </td>
                                            <td>
                                                ${user.fullName}
                                            </td>
                                            <td>
                                                ${user.expiryDate}
                                            </td>
                                            <td>
                                                ${user.phoneNumber}
                                            </td>
                                            <td>
                                                ${user.emailAddress}
                                            </td>
                                            <td>
                                                <input name="btndel" value="${user.userName}" type="submit" id="delUser"  onclick="del();">Delete</a>
                                            </td>
                                        </tr>
                                    </c:forEach>

                                </tbody>
                            </table>

                        </div>
                    </div>
                </div>
                <footer>
                    <jsp:include page="_footer.jsp"></jsp:include>
                    </footer>

                </form>
                <div id="confirmForm"></div>
            </main>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
        <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
        <script src="http://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
        <script>
            function del (){
              $(this).parent('form').attr('action', 'delUser');  
              return window.confirm('Are you really want to delete this Acc?');
            };
        </script>

    </body>

</html>
