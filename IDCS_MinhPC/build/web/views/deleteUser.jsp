<%-- 
    Document   : deleteUser
    Created on : Dec 18, 2019, 1:35:01 AM
    Author     : macbook
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="..${pageContext.request.contextPath}/CSS/bootstrap/bootstrap.min.css">
    </head>
    <body>
        <form action="">
            <div class="modal fade" id="Modal_delUser">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">

                        <!-- Modal Header -->
                        <div class="modal-header">
                            <h4 class="modal-title"><i class="fas fa-trash-alt"></i> DELETE USER ?</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>

                        <!-- Modal body -->
                        <div class="modal-body">
                            <p>Are you Sure Delete this User ?</p>
                        </div>

                        <!-- Modal footer -->
                        <div class="modal-footer">
                            <button type="button" class="btn btn-danger">DELETE</button>
                            <button type="button" class="btn" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </body>
</html>
