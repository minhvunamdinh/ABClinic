<%-- 
    Document   : customer_detail
    Created on : Jun 12, 2022, 6:56:43 PM
    Author     : zz0da
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Customer Detail</title>

        <link href="./public/style/form.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>

        <div class="header">
            <a href="#default" class="logo"> ABClinic</a>
            <div class="header-right">
                <a class="${customer.status=="Waiting"?"active":""}" href="customerlist?status=Waiting&recordsPerPage=3&currentPage=1">Danh sách chờ</a>
                <a class="${customer.status=="Doing"?"active":""}" href="customerlist?status=Doing&recordsPerPage=8&currentPage=1">Danh sách khám bệnh</a>
                <a>${user.role} ${user.fullname}</a>
            </div>
        </div>
        <div class="container">
            <div class="row " style="display: table-row;">
                <table style="width:100%; ">
                    <tr>
                        <th>Mã bệnh nhân</th>
                        <th>Họ và tên</th>
                        <th>Bác sĩ</th>
                        <th>Ngày khám</th>
                        <th>Giới tính</th>
                        <th>Công việc</th>
                        <th>Địa chỉ </th>
                        <th>Ngày sinh </th>
                        <th>Quê Quán </th>
                        <th>Tình trạng </th>
                        <th>Trạng thái </th>
                        <th>Kết quả Khám </th>
                        <th>Kết quả Xét Nghiệm </th>
                        <th>Lịch khám lại</th>
                        <th>Thao tác</th>
                    </tr>
                    <tr>
                        <td>${customer.code}</td>
                        <td>${customer.fullname}</td>
                        <td>Bác Sĩ Hoàng</td>
                        <td>${customer.created_at} </td>
                        <td>${customer.gender}</td>
                        <td>${customer.job}</td>
                        <td>${customer.address}</td>
                        <td>${customer.dob}</td>
                        <td>${customer.country}</td>
                        <td>${customer.description}</td>
                        <td style="color: green">${customer.status}</td>
                        <td>${customer.examination_card}</td>
                        <td>${customer.test_result}</td>
                        <td>${customer.time_return}</td>
                        <td>
                            <c:if test="${status != 'Waiting'}">
                                <a href="accept_customer?id=${customer.id}&status=Doing"> Thêm vào khám </a> 
                            </c:if>
                            <c:if test="${status == 'Doing'}">
                                <div style="width: 50%"><a href="update_customer?id=${customer.id}"></a></div>
                                <div><a href="accept_customer?id=${customer.id}&status=Done"> Hoàn thành khám </a> </div>    
                            </c:if>
                        </td>

                    </tr>

                </table>
            </div>
        </div>


    </body>
</html>
