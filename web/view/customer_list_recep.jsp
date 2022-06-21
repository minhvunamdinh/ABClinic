<%-- 
    Document   : customer_list_recep
    Created on : 21-Jun-2022, 10:07:41
    Author     : Alienware
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Customer list recep</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="./public/style/form.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
        <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
    </head>
    <body>
        <form style="margin: 0 10px;" action="CustomerSearchController?searching=2" method="POST">
            Tìm kiếm :<input name="search" type="text" placeholder="Searching..."> <button type="submit">Tìm kiếm</button>
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
                <c:forEach items="${sessionScope.listcustomer}" var="o">
                    <tr>
                        <td><input type="text" class="form-control" name="code" readonly="" value="${o.id}"/></td>
                        <td><input type="text" class="form-control" name="fullname" value=" ${o.fullname}"/></td>
                        <td>${o.created_by}</td>
                        <td>${o.created_at} </td>
                        <td>
                            <select name="gender">
                                <option value="0" ${o.gender=='0'?"selected":""}>Nữ</option>
                                <option value="1" ${o.gender=='1'?"selected":""}>Nam</option>
                            </select>
                        </td>
                        <td><input type="text" class="form-control" name="job" value=" ${o.job}"/></td>
                        <td><input type="text" class="form-control" name="address" value=" ${o.address}"/></td>
                        <td> 
                            <input type="date" id="birthday" name="dob" value="${o.dob}">
                        </td>
                        <td><input type="text" class="form-control" name="country" value=" ${o.country}"/></td>
                        <td><input type="text" class="form-control" name="description" value=" ${o.description}"/></td>
                        <td style="color: green">${o.status}</td>
                        <td><input type="text" class="form-control" name="examination_card" value=" ${o.examination_card}" ${o.status.trim() == 'Đang khám'?"":"readonly"} /></td>
                        <td><input type="text" class="form-control" name="test_result" value=" ${o.test_result}"${o.status.trim() == 'Đang khám'?"":"readonly"}/></td>
                        <td>
                            <input type="date" id="time_return" name="time_return" value="${o.time_return}"${o.status.trim() == 'Đang khám'?"":"readonly"}></td>
                        <td>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </body>
</html>
