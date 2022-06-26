<%-- 
    Document   : watting_list
    Created on : Jun 11, 2022, 11:05:49 PM
    Author     : zz0da
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Customer List</title>

        <link href="./public/style/form.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>

       
        <jsp:include page="../header/header.jsp"></jsp:include>
            <div class="container">
                <div class="row">
                    <table style="width:100%">
                        <tr>
                            <th>Mã bệnh nhân</th>
                            <th>Họ và tên</th>
                            <th>Bác sĩ</th>
                            <th>Ngày khám</th>
                            <th> Trạng Thái </th>
                            <th>Mô tả</th>
                            <th>Thao tác</th>
                        </tr>
                    <c:forEach var="customer" items="${requestScope.customer_list}">
                        <tr>
                            <td>${customer.code}</td>
                            <td>${customer.fullname}</td>
                            <td>Bác Sĩ Hoàng</td>
                            <td>${customer.created_at} </td>
                            <td style="color: yellowgreen">${customer.status}</td>
                            <td>${customer.description}</td>
                            <td style=" display: flex;flex-wrap: wrap; text-align: center;">
                                <div style="width: 50%"><a  href="customer_detail?id=${customer.id}">Chi tiết</a></div>
                                <c:if test="${status == 'Waiting'}">
                                    <div> <a href="accept_customer?id=${customer.id}&status=Doing"> Thêm vào khám </a> </div>
                                </c:if>
                                <c:if test="${status == 'Doing'}">
                                    <div> <a href="accept_customer?id=${customer.id}&status=Done"> Hoàn thành khám </a> </div>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="pagination">
                <c:if test="${currentPage != 1}">
                    <a href="ListCourseController?recordsPerPage=${recordsPerPage}&&currentPage=${currentPage-1}">Previous</a>

                </c:if>

                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <a style="background-color:  blueviolet">${i}<span></span></a>
                                </c:when>
                                <c:otherwise>
                                    <c:if test="${status == 'Waiting'}">
                                <a href="customerlist?status=Waiting&recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                            </c:if>
                            <c:if test="${status == 'Doing'}">
                                <a href="customerlist?status=Doing&recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>

                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${currentPage lt noOfPages}">
                    <c:if test="${status == 'Waiting'}">
                        <a href="customerlist?status=Waiting&recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}">Next</a>

                    </c:if>

                    <c:if test="${status == 'Doing'}">
                        <a href="customerlist?status=Doing&recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}">Next</a>
                    </c:if>
                </c:if>
            </div>
        </div>


    </body>
</html>
