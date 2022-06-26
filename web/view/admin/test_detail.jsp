<%-- 
    Document   : test_detail
    Created on : Jun 26, 2022, 4:15:05 PM
    Author     : zz0da
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
        <c:if test="<%=request.getParameter("id") != null %>">
            <h1>Cập nhật Xét Nghiệm <%= request.getParameter("id")%></h1>
        </c:if> 
        <c:if test="<%=request.getParameter("id") == null %>">
            <h1>Tạo mới Xét Nghiệm</h1>
        </c:if> 
            <div class="row" >
                <div class="col-md-8" style="margin-left: 20%;margin-top: 10%;">
                    <h2>Chi tiết Xét Nghiệm</h2>
                    <form action="admin_test_detail" method="POST">
                        <div class=" col-md-9 col-lg-9 " >
                            <table class="table table-user-information">
                                <tbody>

                                    <tr>
                                        <td>ID</td>
                                        <td><input type="text"  name="id" value="${test.id}" readonly=""/></td>
                                </tr>
                                <tr>
                                    <td>Loại Xét Nghiệm</td>
                                    <td>
                                        <select  style=" width: 200px;" name="type_id">
                                            <c:forEach  var="type_test" items="${list_type_test}">
                                                <option value="${type_test.type_id}" ${type_test.type_id == test.type_id?"selected":""}>${type_test.type_name}</option>
                                            </c:forEach>
                                        </select>
                                    </td>

                                </tr>
                                <tr>
                                    <td>Tên Xét Nghiệm</td>
                                    <td><input type="text"  name="name" value="${test.name}"/></td>
                                </tr>
                                <tr> 
                                    <td>Giá gốc</td>
                                    <td>
                                        <input type="text" name="cost_price" value="${test.cost_price}"/>
                                        <c:if test="${not empty cost_price_fail}">
                                        <p style="color:red">${cost_price_fail}</p>
                                    </c:if>
                                    </td>

                                </tr>

                                <tr>
                                    <td>Giá bán</td>
                                    <td><input type="text"  name="sell_price" value="${test.sell_price}"/></td>

                                    <c:if test="${not empty sell_price_fail}">
                                        <p style="color:red">${sell_price_fail}</p>
                                    </c:if>
                                </tr>
                                <tr>
                                    <td>Trạng thái</td>
                                    <td>
                                        <select style=" width: 200px;" name="is_active">
                                            <option value="1" ${type_test.is_active == true ?"selected":""}>Hoạt Động</option>
                                            <option value="0" ${type_test.is_active == false ?"selected":""}>Ngưng Hoạt Động</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Form Kết quả</td>
                                    <td><input type="text"  name="form" value="${test.form}"></td>
                                </tr>
                            <td><button type="submit" style="margin-left: 25%" value="save" >Cập nhật</button></td>
                            <c:if test="${not empty success}">
                                <p style="color: green; font-size: 20px">${success}</p>
                            </c:if>
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
