<%-- 
    Document   : editAccount
    Created on : Jun 23, 2022, 11:15:10 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.Account"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

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

        <jsp:include page="../header/header.jsp"></jsp:include>

            <div class="row" >
                <div class="col-md-8" style="margin-left: 20%;margin-top: 10%;">
                    <h2>Bảng hiển thị thông tin tài khoản</h2>
                    <form action="AdminEditAccount" method="POST">
                        <div class=" col-md-9 col-lg-9 " >
                            <table class="table table-user-information">
                                <tbody>

                                <c:forEach var="rpt" items="${requestScope.listPage}">
                                    <tr>
                                        <td>ID</td>
                                        <td><input type="text"  name="uid" value="${rpt.getId()}" readonly=""/></td>
                                    </tr>
                                    <tr>
                                        <td>Tài khoản</td>
                                        <td>${rpt.getUsername()}</td>
                                    </tr>
                                    <tr>
                                        <td>Họ và tên</td>
                                        <td><input type="text"  name="fullname" value="${rpt.getFullname()}"/></td>
                                    </tr>
                                    <tr>
                                        <td>Ngày Sinh</td>

                                        <td> 
                                            <input type="date" id="birthday" name="dob" value="${rpt.getDob()}"></td>
                                    </tr>


                                    <tr>
                                        <td>Giới tính</td>
                                        <td>
                                            <select name="gender" id="cars">
                                                <c:if test="${rpt.getGender() == '1'}">

                                                    <option value="1" selected="selected">Nam</option>
                                                    <option value="0">Nữ</option>

                                                </c:if>
                                                <c:if test="${rpt.getGender() == '0'}">

                                                    <option value="1" >Nam</option>
                                                    <option value="0"selected="selected">Nữ</option>

                                                </c:if>    

                                            </select>
                                        </td>

                                    </tr>
                                    <tr>
                                        <td>Địa chỉ</td>
                                        <td><input type="text"  name="address" value="${rpt.getAddress()}"></td>
                                    </tr>
                                    <tr>
                                        <td>Email</td>
                                        <td><input type="text"  name="email" value="${rpt.getEmail()}"></td>
                                    </tr>
                                    <tr>
                                        <td>Số điện thoại</td>
                                        <td><input type="text"  name="phone" value="${rpt.getPhone()}"></td>
                                    </tr>
                                <td><button type="submit" style="margin-left: 25%" value="save" >Save</button></td>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <label style="color: red">${message}</label>
                </form>
            </div>
        </div>
        <div class="backPage" style="margin-left: 20%">
            <button onclick="history.back()">Back</button>
        </div>
    </body>
</html>
