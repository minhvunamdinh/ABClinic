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
        <jsp:include page="header.jsp"/>
        <form action="addnewcus?id=<%=request.getAttribute("id")%>" method="POST">
            <table>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label>Họ và Tên :</label>
                        <input name="fullname"  required value='<%=request.getAttribute("fullname")%>' type="text" class="form-control"  placeholder="Họ và tên">
                    </div>
                    <div class="form-group col-md-6">
                        <label>Số điện thoại :</label>
                        <input type="number" class="form-control"  placeholder="Số điện thoại">
                    </div>
                    <div class="form-group col-md-6">
                        <label>Tuổi :</label>
                        <input name="age"  required value='<%=request.getAttribute("age")%>' type="number" class="form-control"  placeholder="Tuổi">
                    </div>
                    <div class="form-group col-md-6">
                        <label>Email :</label>
                        <input name="email" required value='<%=request.getAttribute("email")%>' type="email" class="form-control"  placeholder="Email">
                    </div>
                    <div class="form-group col-md-6">
                        <label>Quốc gia :</label>
                        <input  name="country" required value='<%=request.getAttribute("country")%>' type="Text" class="form-control"  placeholder="Email">
                    </div>
                    <div class="form-group col-md-6">
                        <label>Ngày sinh :</label>
                        <input name="dob" required value='<%=request.getAttribute("dob")%>' type="datetime" class="form-control" placeholder="Ngày Sinh">
                    </div>
                    <div class="form-group col-md-6">
                        <label>Công việc :</label>
                        <input name="job" required value='<%=request.getAttribute("job")%>' type="Text" class="form-control" placeholder="Công Việc">
                    </div>
                    <div class="form-group col-md-6">
                        <label>Chọn bác sĩ :</label>
                        <select name="doctor" id="inputState" class="form-control">
                            <c:forEach items="${sessionScope.listdoctor}" var="o">
                                <option value="${o.id}">${o.fullname}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group col-md-6">
                        <div class="row">
                            <legend class="col-form-label col-sm-2 pt-0">Giới tinh :</legend>
                            <div class="col-sm-10">
                                <div class="form-check">
                                    <input required class="form-check-input" type="radio" name="gender"  value="1" ${cus.gender=='Nam'?"checked":""}>
                                    <label class="form-check-label" for="gridRadios1">
                                        Nam ${cus.gender}
                                    </label>
                                </div>
                                <div class="form-check">
                                    <input required class="form-check-input" type="radio" name="gender"  value="0" ${cus.gender=='Nu'?"checked":""}>
                                    <label class="form-check-label" for="gridRadios2">
                                        Nữ
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group col-md-6"></div>
                    <div class="form-group col-md-6">
                        <div class="form-group">
                            <label required for="exampleFormControlTextarea1">Ghi chú :</label>
                            <textarea name="status" class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
                        </div> 
                    </div>
                    <div class="form-group col-md-6"></div>
                    <div class="form-group col-md-2"><button type="submit" class="btn btn-primary">Tạo mới</button></div>

            </table>
        </form>
        <script>
            function insertSuccess() {
                alert("Add new Successfull!");
            }
        </script> 
    </body>
</html>