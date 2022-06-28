<%-- 
    Document   : order_test
    Created on : Jun 15, 2022, 8:01:38 PM
    Author     : zz0da
--%>

<%@page import="model.Test"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./public/style/order_test.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="../header/header.jsp"></jsp:include>

            <div class="container">  
                <form class="contact" id="order_test" action="order_test?cus_id=${customer.id}" method="post">

                <div class="wd100">
                    <label name="">Chỉ định xét nghiệm</label>
                    <p><strong>Chọn các xét nghiệm cho bệnh nhân</strong></p>
                </div>

                <div class="wd100 question-checkbox">
                    <div class="wrapper">

                        <c:forEach var="type_test" items="${requestScope.list_type_test}">
                            <label>${type_test.type_name}</label>
                            <ul class="ib-list">
                                <c:forEach var="test" items="${list_test}">
                                    <c:if test="${test.type_id == type_test.type_id}">
                                        <li>
                                            <c:set var = "check" value = " ${test.id},"/>
                                            <label ><input type="checkbox" name="test" value="${test.id}" ${fn:contains(customer.list_test,check)?"checked":""}> ${test.name}</label>
                                        </li>
                                    </c:if>
                                </c:forEach>
                            </ul>

                        </c:forEach>

                    </div>

                </div>
                <div class="wd100">
                    <label name="">Notes</label>
                    <textarea  name="note" placeholder="Insert text here ..." required>${customer.note}</textarea>
                </div>
                <div class="wd100">
                    <button name="submit" type="submit" id="" data-submit="...Sending">Submit</button>
                </div>
            </form>

        </div>
    </body>
</html>
