<%-- 
    Document   : adminViewADetail
    Created on : Jun 23, 2022, 4:34:19 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.Account"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="../header/adminHeader.jsp"></jsp:include>
            
            <div class="row" >
                <div class="col-md-8" style="margin-left: 40%;margin-top: 10%;">
                    <h2>Bảng hiển thị thông tin tài khoản</h2>
                    <div class=" col-md-9 col-lg-9 " >
                        <table class="table table-user-information">
                            <tbody>
                                <c:forEach var="rpt" items="${requestScope.listPage}">
                                <tr>
                                    <td>Tài khoản</td>
                                    <td>${rpt.getUsername()}</td>
                                </tr>
                                <tr>
                                    <td>Họ và tên</td>
                                    <td>${rpt.getFullname()}</td>
                                </tr>
                                <tr>
                                    <td>Ngày Sinh</td>
                                    <td>${rpt.getDob()}</td>
                                </tr>

                                
                                <tr>
                                    <td>Giới tính</td>
                                
                                        <c:if test="${rpt.getGender() == '1'}">
                                                        <td><label >Nam</label></td>
                                                    </c:if>
                                                    <c:if test="${rpt.getGender() == '0'}">
                                                        <td><td><label >Nữ</label></td></td>
                                         </c:if>
                                </tr>
                                <tr>
                                    <td>Địa chỉ</td>
                                    <td>${rpt.getAddress()}</td>
                                </tr>
                                <tr>
                                    <td>Email</td>
                                    <td>${rpt.getEmail()}</td>
                                </tr>
                                <tr>
                                    <td>Số điện thoại</td>
                                    <td>${rpt.getPhone()}</td>
                                </tr>
</c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        <div class="backPage" style="margin-left: 20%">
                <button onclick="history.back()">Back</button>
            </div>
    </body>
</html>
