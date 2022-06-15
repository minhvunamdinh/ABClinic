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
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
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

        <form style="margin: 0 10px;" action="customer_detail?id=${customer.id}" method="POST">

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
                    <th>Mô tả </th>
                    <th>Trạng thái </th>
                    <th>Kết quả Khám </th>
                    <th>Kết quả Xét Nghiệm </th>
                    <th>Lịch khám lại</th>
                    <th>Thao tác</th>
                </tr>
                <tr>
                    <td><input type="text" class="form-control" name="code" readonly="" value=" ${customer.code}"/></td>
                    <td><input type="text" class="form-control" name="fullname" value=" ${customer.fullname}"/></td>
                    <td>Bác Sĩ Hoàng</td>
                    <td>${customer.created_at} </td>
                    <td>
                        <select name="gender">

                            <option value="0" ${customer.gender=='Nu'?"selected":""}>Nữ</option>
                            <option value="1" ${customer.gender=='Nam'?"selected":""}>Nam</option>
                        </select>
                    </td>
                    <td><input type="text" class="form-control" name="job" value=" ${customer.job}"/></td>
                    <td><input type="text" class="form-control" name="address" value=" ${customer.address}"/></td>
                    <td> 
                        <input type="date" id="birthday" name="dob" value="${customer.dob}">
                    </td>
                    <td><input type="text" class="form-control" name="country" value=" ${customer.country}"/></td>
                    <td><input type="text" class="form-control" name="description" value=" ${customer.description}"/></td>
                    <td style="color: green">${customer.status}</td>
                    <td><input type="text" class="form-control" name="examination_card" value=" ${customer.examination_card}" ${customer.status.trim() == 'Đang khám'?"":"readonly"} /></td>
                    <td><input type="text" class="form-control" name="test_result" value=" ${customer.test_result}"${customer.status.trim() == 'Đang khám'?"":"readonly"}/></td>
                    <td>
                        <input type="date" id="time_return" name="time_return" value="${customer.time_return}"${customer.status.trim() == 'Đang khám'?"":"readonly"}></td>
                    <td>
                        <c:if test="${customer.status.trim() == 'Đang chờ'}">
                            <a href="accept_customer?id=${customer.id}&status=Doing"> Thêm vào khám </a> 
                        </c:if>
                        <c:if test="${customer.status.trim() == 'Đang khám'}">
                            <div><a href="accept_customer?id=${customer.id}&status=Done"> Hoàn thành khám </a> </div>    
                        </c:if>

                    </td>

                </tr>

            </table>
            <c:if test="${customer.status.trim() == 'Đang khám'}">
                <button><a href="order_test?cus_id=${customer.id}">Chỉ định xét nghiệm</a></button>
            </c:if>
            <button  value="submit" onclick="updateOk()">Lưu thông tin</button>
        </form>

        <script>
            function updateOk() {
                alert("Update Successfull!");
            }
        </script>                   

    </body>
</html>
