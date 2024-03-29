<%-- 
    Document   : adminViewAccount
    Created on : Jun 22, 2022, 3:20:33 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.Account"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Account</title>
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
        <!--        style="float: right;margin-right: 5%">Đăng nhập-->
        <style>
            .dropbtn {
                background-color: #04AA6D;
                color: white;
                padding: 16px;
                font-size: 12px;
                border: none;
            }

            .dropdown {
                position: relative;
                display: inline-block;
            }

            .dropdown-content {
                display: none;
                position: absolute;
                background-color: #f1f1f1;
                min-width: 100px;
                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 1;
            }

            .dropdown-content a {
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
            }

            .dropdown-content a:hover {background-color: #ddd;}

            .dropdown:hover .dropdown-content {display: block;}

            .dropdown:hover .dropbtn {background-color: #3e8e41;}
        </style>

    </head>

    <body id="page-top" >
       <jsp:include page="../header/header.jsp"></jsp:include>

            <!-- Page Wrapper -->
            <div id="wrapper" style="width: 100%; margin-top: 5%">


                <!-- Content Wrapper -->
                <div id="content-wrapper" class="d-flex flex-column">

                    <!-- Main Content -->
                    <div id="content">

                        <!-- Topbar -->
                        <!-- End of Topbar -->

                        <!-- Begin Page Content -->
                        <div class="container-fluid">

                            <!-- Page Heading -->
                            <!-- DataTales Example -->
                            <div class="card shadow mb-4">

                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                            <thead>
                                                <tr>
                                                    <th>ID</th>
                                                    <th>Tài khoản</th>
                                                    <th>Hoạt động</th>
                                                    <th>Họ và tên</th>
                                                    <th>Ngày sinh</th>
                                                    <th>Địa chỉ</th>
                                                    <th>Chức năng</th>
                                                </tr>

                                            </thead>
                                            <tbody>
                                            <c:forEach var="rpt" items="${requestScope.listPage}">
                                                <tr>
                                                    <td>${rpt.getId()}</td>
                                                    <td>${rpt.getUsername()}</td>
                                                    <c:if test="${rpt.getIs_active() == '1'}">
                                                        <td><label style="color: green">Kích hoạt</label></td>
                                                    </c:if>
                                                    <c:if test="${rpt.getIs_active() == '0'}">
                                                        <td><label style="color: Red">Chưa kích hoạt</label></td>
                                                    </c:if>
                                                    <td>${rpt.getFullname()}</td>
                                                    <td>${rpt.getDob()}</td>
                                                    <td>${rpt.getAddress()}</td>
                                                    <td>
                                                        <div class="dropdown">
                                                            <button class="dropbtn">Chức năng</button>
                                                            <div class="dropdown-content">
                                                                <a href="../ABClinic/AdminViewAccountDetailController?aid=${rpt.getId()}">Chi tiết</a>
                                                                <a href="../ABClinic/AdminEditAccount?aid=${rpt.getId()}">Cập nhật</a>
                                                                <a href="../ABClinic/AdminAcAccountController?aid=${rpt.getId()}&active=${rpt.getIs_active()}">Trạng thái</a>
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </c:forEach>

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>

                    </div>
                    <!-- /.container-fluid -->

                </div>

            </div>
            <!-- End of Content Wrapper -->

        </div>


    </body>
</html>

