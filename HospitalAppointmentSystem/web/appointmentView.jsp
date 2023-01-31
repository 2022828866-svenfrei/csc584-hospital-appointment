<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : appointmentView
    Created on : Jan 31, 2023, 8:04:00 PM
    Author     : harith
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="includes/header.jsp" %>
<sql:setDataSource var="myDatasource" 
                   driver="org.apache.derby.jdbc.ClientDriver" 
                   url="jdbc:derby://localhost:1527/HospitalAppointmentDB" user="app" 
                   password="app"/>
<!DOCTYPE html>
<html>
    <head>
        <title>Appointment View</title>
    </head>
    <body>
        <sql:query var="result" dataSource="${myDatasource}">
            SELECT * from Appointment
        </sql:query>

        <table class="table table-striped table-hover">
            <!-- column headers -->
            <thead>
                <tr>
                    <c:forEach var="columnName" items="${result.columnNames}">
                        <th><c:out value="${columnName}"/></th>
                        </c:forEach>
                    <th>Actions</th>
                </tr>
            </thead>
            <!-- column data -->
            <tbody class="table-group-divider">
                <c:forEach var="row" items="${result.rowsByIndex}">
                    <tr>
                        <c:forEach var="column" items="${row}">
                            <td><c:out value="${column}"/></td>
                        </c:forEach>
                        <td colspan="6">
                            <a class="btn btn-primary" href="appointmentEdit.jsp">Edit</a>
                            <a class="btn btn-danger" href="appointmentDelete.jsp">Delete</a>
                        </td>
                    </tr>
                </c:forEach>

            </tbody>
        </table>
        <%@include file="includes/footer.jsp" %>
    </body>
</html>
