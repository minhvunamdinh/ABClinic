<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <div class="header">
            <a href="#default" class="logo"> ABClinic</a>
            <div class="header-right">
                <a class="${customer.status=="Waiting"?"active":""}" href="customerlist?status=Waiting&recordsPerPage=3&currentPage=1">Danh sách ch?</a>
                <a class="${customer.status=="Doing"?"active":""}" href="customerlist?status=Doing&recordsPerPage=8&currentPage=1">Danh sách khám b?nh</a>
                <a>${user.role} ${user.fullname}</a>
            </div>
    </div>
