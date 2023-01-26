<%-- 
    Document   : login
    Created on : Jan 26, 2023, 4:37:02 PM
    Author     : frei-
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login</h1>
        <form method="post" action="LoginServlet">
            <table>
                <tr>
                    <td>
                        <label>Email</label>
                    </td>
                    <td>
                        <input name="email">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Password</label>
                    </td>
                    <td>
                        <input name="password" type="password">
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <%
                            if (request.getAttribute("message") == null) {
                            } else {
                                %>
                                <%= request.getAttribute("message") %>
                                <%
                            }
                        %>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="submit" value="Login">
                        <input type="reset" value="Reset">
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
