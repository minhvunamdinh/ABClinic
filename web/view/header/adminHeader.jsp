<%-- 
    Document   : adminHeader
    Created on : Jun 22, 2022, 3:21:15 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
<!--        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>-->


<link href="./view/bootstrap/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css"/>
        <!-- Custom fonts for this template -->
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet"/>

        <!-- Custom styles for this template -->
        <link href="./view/bootstrap/css/sb-admin-2.min.css" rel="stylesheet"/>

        <!-- Custom styles for this page -->
        <link href="./view/bootstrap/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet"/>

        <!-- Bootstrap core JavaScript-->
        <script src="./view/bootstrap/vendor/jquery/jquery.min.js"></script>
        <script src="./view/bootstrap/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="./view/bootstrap/vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="./view/bootstrap/js/sb-admin-2.min.js"></script>

        <!-- Page level plugins -->
        <script src="./view/bootstrap/vendor/datatables/jquery.dataTables.min.js"></script>
        <script src="./view/bootstrap/vendor/datatables/dataTables.bootstrap4.min.js"></script>

        <!-- Page level custom scripts -->
        <script src="./view/bootstrap/js/demo/datatables-demo.js"></script>
        
        <script src="./view/bootstrap/vendor/jquery/jquery.min.js"></script>
        <script src="./view/bootstrap/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="./view/bootstrap/js/sb-admin-2.min.js"></script>

        <!-- Page level plugins -->
        <script src="./view/bootstrap/vendor/datatables/jquery.dataTables.min.js"></script>
        <script src="./view/bootstrap/vendor/datatables/dataTables.bootstrap4.min.js"></script>

        <!-- Page level custom scripts -->
        <script src="./view/bootstrap/js/demo/datatables-demo.js"></script>
    </head>
    <body>
<!--        <div style="border: 2px solid;height: 110px;width:90%;margin-left: 5%;background: #34e3a4;" >
                 <div class="span6"style="text-align: center">
                                    <h1 class="muted" style="color: red;margin-left: 120%;text-align: center;font-size: 2.5rem;    margin-bottom: 0.5rem;font-weight: 400;line-height: 1.2;">ABClinnic</h1>
                  </div>
            <div  style="margin: 5% 0 0 0;border: none;width: 35%">
                <ul class="nav" start="1" style="display: flex">
                                        <li style="padding-left: 1% ;">
                                            <a href="../admin/adminPage.jsp">Home</a>
                                        </li>

                                        <li style="padding-left: 5%;">
                                            <a href="../ABClinic/AdminViewAccountController">Account</a>
                                        </li>

                                        <li style="padding-left: 5%;">
                                            <a href="#">Revenue</a>
                                        </li>

                                        <li style="padding-left: 5%;">
                                            <a href="#">Management all</a>
                                        </li>
                                    </ul>
            </div>
                                    
            
         </div>-->

<div style="border: 2px solid; width: 90%;text-align: center;margin-left: 5%;background: #34e3a4" >
                 <div class="span6">
                                    <h1 class="muted" style="color: red">ABClinnic</h1>
                                     <c:if test="${null!=sessionScope.account}">
                                         
                                         <p style="float: right;margin-right: 5%">Đăng xuất</p>
                                         <p style="float: right;margin-right: 10%">Hello ${sessionScope.account.getUsername()}</p>
                                         
                                    </c:if>
                                     <c:if test="${null ==sessionScope.account}">
                                         
                                         <p style="float: right;margin-right: 5%">Đăng nhập</p>
                                         
                                         
                                    </c:if>
                  </div>
                                    <ul class="nav" start="1">
                                        <li style="padding-left: 5%">
                                            <a href="../admin/adminPage.jsp">Home</a>
                                        </li>

                                        <li style="padding-left: 5%">
                                            <a href="../ABClinic/AdminViewAccountController">Account</a>
                                        </li>

                                        <li style="padding-left: 5%">
                                            <a href="#">Revenue</a>
                                        </li>

                                        <li style="padding-left: 5%">
                                            <a href="#">Management all</a>
                                        </li>
                                    </ul>
            
         </div>
    </body>
</html>
