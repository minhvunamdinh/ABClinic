<%-- 
    Document   : header.jsp
    Created on : Jun 26, 2022, 6:51:45 PM
    Author     : zz0da
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="./public/style/form.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>
        <%
            User user = (User) request.getSession().getAttribute("user");

        %>
        <div class="header">
            <a href="#default" class="logo"> ABClinic</a>
            <div class="header-right">
                <c:if test="<%= user.getRole() == "Boss"%>">
                    <a class="" href="AdminViewAccountController">Quản Lý Nhân Viên</a>
                    <a class="" href="adminviewtest">Quản Lý Xét Nghiệm</a>
                    <a><%= user.getRole()%> <%= user.getFullname()%></a>
                </c:if>
                <c:if test="<%= user.getRole() == "Bác Sĩ"%>">
                    <a class="${status == "Waiting"?"active":""}" href="customerlist?status=Waiting&recordsPerPage=3&currentPage=1">Danh sách chờ</a>
                    <a class="${status == "Doing"?"active":""}" href="customerlist?status=Doing&recordsPerPage=8&currentPage=1">Danh sách khám bệnh</a>
                    <a><%= user.getRole()%> <%= user.getFullname()%></a>
                </c:if>

            </div>
        </div>
    </body>
</html>
