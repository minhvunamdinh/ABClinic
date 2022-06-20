<%-- 
    Document   : listsearchcustomer
    Created on : 20-Jun-2022, 14:31:40
    Author     : Alienware
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="./public/style/form.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <form style="margin: 0 10px;" action="CustomerSearchController" method="POST">
            Tìm kiếm :<input name="fullname" type="text" placeholder="Searching..."> <button type="submit">Tìm kiếm</button>
            <table style="width:100%; ">
                <tr>
                    <th>Mã bệnh nhân</th>
                    <th>Họ và tên</th>
                    <th>Tuổi</th>
                    <th>Quốc Gia</th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach items="${sessionScope.listcustomer}" var="o">
                    <tr>
                        <td>${o.id}</td>
                        <td>${o.fullname}</td>
                        <td>${o.dob}</td>
                        <td>${o.country}</td>
                        <td><a>View Detail</a></td>
                        <td><a href="addnewcus?id=${o.id}" >Create Order</a></td>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </body>
</html>
