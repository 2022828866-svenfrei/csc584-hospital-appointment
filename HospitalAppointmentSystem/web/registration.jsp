<%-- 
    Document   : register
    Created on : Jan 26, 2023, 2:56:04 PM
    Author     : frei-
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="includes/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
        <link href="styles/base.css" rel="stylesheet">
        <title>Registration</title>
    </head>
    <body>
        
        <div class="content">
            <form method="post" action="RegistrationServlet">
                <div class="container">
                    <div class="row text-center">
                        <h1>Registration</h1>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col"></div>
                        <div class="col">
                            <label>Name</label>
                        </div>
                        <div class="col">
                            <input name="name">
                        </div>
                        <div class="col"></div>
                    </div>
                    <div class="row">
                        <div class="col"></div>
                        <div class="col">
                            <label>Email</label>
                        </div>
                        <div class="col">
                            <input name="email">
                        </div>
                        <div class="col"></div>
                    </div>
                    <div class="row">
                        <div class="col"></div>
                        <div class="col">
                            <label>Birthdate</label>
                        </div>
                        <div class="col">
                            <input name="birthdate" type="date">
                        </div>
                        <div class="col"></div>
                    </div>
                    <div class="row">
                        <div class="col"></div>
                        <div class="col">
                            <label>Password</label>
                        </div>
                        <div class="col">
                            <input name="password" type="password">
                        </div>
                        <div class="col"></div>
                    </div>
                    <div class="row">
                        <div class="col"></div>
                        <div class="col">
                            <label>Confirm Password</label>
                        </div>
                        <div class="col">
                            <input name="confirmPassword" type="password">
                        </div>
                        <div class="col"></div>
                    </div>
                    <div class="row">
                        <div class="col"></div>
                        <div class="col-2 text-danger">
                        <%
                            if (request.getAttribute("message") == null) {
                            } else {
                                %>
                                <%= request.getAttribute("message") %>
                                <%
                            }
                        %>
                        </div>
                        <div class="col"></div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col"></div>
                        <div class="col-2">
                            <input class="btn btn-primary" type="submit" value="Register">
                        </div>
                        <div class="col"></div>
                    </div>
                </div>
            </form>
        </div>
        <%@include file="includes/footer.jsp" %>
    </body>
</html>
