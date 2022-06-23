<%-- 
    Document   : adminHeader
    Created on : Jun 22, 2022, 3:21:15 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet" id="bootstrap-css">
<!--        <script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>-->
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    </head>
    <body>
        <div style="border: 2px solid;height: 110px;width:90%;margin-left: 5%;background: #34e3a4;" >
                 <div class="span6"style="text-align: center">
                                    <h1 class="muted" style="color: red;margin-left: 120%;text-align: center;font-size: 2.5rem;    margin-bottom: 0.5rem;font-weight: 400;line-height: 1.2;">ABClinnic</h1>
                  </div>
            <div  style="margin: 5% 0 0 0;border: none;width: 35%">
                <ul class="nav" start="1" style="display: flex">
                                        <li style="padding-left: 1% ;">
                                            <a href="../admin/adminPage.jsp">Home</a>
                                        </li>

                                        <li style="padding-left: 5%;">
                                            <a href="../ABClinic/AdminViewAccountController">Account</a>
                                        </li>

                                        <li style="padding-left: 5%;">
                                            <a href="#">Revenue</a>
                                        </li>

                                        <li style="padding-left: 5%;">
                                            <a href="#">Management all</a>
                                        </li>
                                    </ul>
            </div>
                                    
            
         </div>
    </body>
</html>
