<%-- 
    Document   : register
    Created on : Jun 7, 2022, 8:24:35 AM
    Author     : vudm
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="public/style/register.css" rel="stylesheet" type="text/css"/>
        
    </head>
    <body>
        
        <script>
            function check_pass() {
                if (document.getElementById("password").value !==
                        document.getElementById("confirm_password").value) {
                         alert("Re-password again!")
                }
            }
        </script>
        
            <div class="container register-form">
                <div class="form">
                    <form class="form" action="register" method="POST">
                    <div class="note">
                        <p>Register Form</p>
                    </div>

                    <div class="form-content">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <input type="text" class="form-control" placeholder="FullName *" name="fullname" 
                                           minlength="2" maxlength="250"
                                           pattern="[a-zA-Z ]{2,250}" 
                                           title="Fullname can not contain number,nulll and not over 250 characters"
                                           required
                                           />
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" placeholder="UserName *" name="username"
                                           pattern="[a-z][a-zA-Z0-9]{5,20}" 
                                           title="Username should only contain lowercase,start equals 5 letters and not over 250 characters"
                                           required
                                           />
                                </div>
                                <div class="form-group">
                                    <input type="password" class="form-control" id="password"  placeholder="Password *" name="password"

                                           minlength="6" required
                                           />
                                </div>
                                <div class="form-group">
                                    <input type="password" class="form-control" placeholder="Confirm Password *" id="confirm_password" name="repassword" 
                                           required
                                           />
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <input type="text" class="form-control" placeholder="Phone Number *" name="phone"
                                           pattern="[0][0-9]{9}"
                                           title="Phone number must have 10 numbers and start by 0"
                                           required
                                           />
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" placeholder="Email *" name="email"/>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" placeholder="Address *" name="address"/>
                                </div>
                                <div class="form-group">
                                    <label>Gender:</label>
                                    <select class="form-group" name="gender">
                                        <option value="1">Nam</option>
                                        <option value="0">Nữ</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <label>Role Number:</label>
                                <select class="form-group" name="role">
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                </select>
                                
                            </div>
                            <div class="col-md-6">
                                <label for="birthday">DOB:</label>
                                <input type="date" id="birthday" name="dob">
                            </div>
                            <div style="color: red">${message}</div>
                        </div>
                        <button type="submit" class="btnSubmit" id="btnSubmit" onclick="check_pass()">Submit</button>
                    </div>
                        </form>
                </div>
            </div>
    </body>
</html>
