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
    </head>
    <body>
 
        <jsp:include page="../header/adminHeader.jsp"></jsp:include>

            <div class="row" >
                <div class="col-md-8" style="margin-left: 40%;margin-top: 10%;">
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
