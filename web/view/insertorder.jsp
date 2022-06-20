<%-- 
    Document   : add_customer
    Created on : 16-Jun-2022, 00:51:34
    Author     : Alienware
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
        <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
        <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
    </head>
    <body>
        <form action="addnewcus" method="POST">
            <table>
                <tr>
                    <td>Họ và Tên :<input name="fullname"  type="text" placeholder="FullName" required value='<%=request.getAttribute("fullname")%>'></td>
                    <td>Số điện thoại :<input name="phone"  type="text" placeholder="Phone" required value='<%=request.getAttribute("phone")%>'></td>
                </tr>
                <tr>
                    <td>Tuổi :<input name="age"  type="text" placeholder="Age" required  value='<%=request.getAttribute("age")%>'></td>
                    <td>Email :<input name="email"  type="text" placeholder="Email" required value='<%=request.getAttribute("email")%>'></td>
                </tr>
                <tr>
                    <td>Quốc Gia :<input name="country"  type="text" placeholder="Country" required value='<%=request.getAttribute("country")%>'></td>
                    <td>Ngày sinh :<input name="dob"  type="date" placeholder="Date" required value='<%=request.getAttribute("dob")%>'></td>
                </tr>
                <tr>
                    <td>Công việc :<input name="job"  type="text" placeholder="Job" required value='<%=request.getAttribute("job")%>'></td>
                    <td>Chọn bác sĩ :<select name="doctor">
                            <c:forEach items="${sessionScope.listdoctor}" var="o">
                                <option>${o.fullname}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Giới tính :<input name="gender"  type="radio" placeholder="gender" required value="1"> Men<input name="gender"  type="radio" placeholder="gender" required value="0"> Woman</td>
                    <td></td>   
                </tr>
                <tr>
                    <td>Note: <textarea name="status"></textarea>
                    <td></td>   
                </tr>
            </table>
            <button type="submit" >Xác Nhận</button>
        </form>
    </body>
</html>