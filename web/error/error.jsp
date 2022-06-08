<%-- 
    Document   : error
    Created on : Feb 24, 2022, 4:10:18 PM
    Author     : t.manh
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="./public/style/error.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="body-404">
            <div class="title">
                <p class="error404">404</p>
            </div>
            <div class="title1">
                <p class="description">Ooops!!</p>
            </div>
            <div class="title2">
                <p class="description-page">${exception.getMessage()}</p>
            </div>
            <div class="title1">
                <button onclick="history.back()" class="callBack">Go Back to Home</button>
            </div>
        </div>
    </body>
</html>
