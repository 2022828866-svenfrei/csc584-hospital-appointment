<%-- 
    Document   : register
    Created on : Jan 26, 2023, 2:56:04 PM
    Author     : frei-
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
    </head>
    <body><h1>Registration</h1>
        <form method="post" action="RegistrationServlet">
            <ul>
                <li>
                    <label for="name">Name</label><br>
                    <input name="name">
                </li>
                <li>
                    <label for="email">Email</label><br>
                    <input name="email">
                </li>
                <li>
                    <label for="birthdate">Birthdate</label><br>
                    <input name="birthdate" type="date">
                </li>
                <li>
                    <label for="password">Password</label><br>
                    <input name="password" type="password">
                </li>
                <li>
                    <label for="confirmPassword">Confirm Password</label><br>
                    <input name="confirmPassword" type="password">
                </li>
            </ul>
            <p>
                <%
                    if (request.getAttribute("message") == null) {
                    } else {
                        %>
                        <%= request.getAttribute("message") %>
                        <%
                    }
                %>
            </p>
            <input type="submit" value="Register"> <input type="reset" value="Reset">
        </form>
    </body>
</html>
