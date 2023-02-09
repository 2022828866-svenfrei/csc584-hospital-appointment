<%-- 
    Document   : editAccount
    Created on : Jan 31, 2023, 7:27:25 AM
    Author     : frei-
--%>

<%@page import="bean.ExpertiseBean"%>
<%@page import="bean.AbsenceBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="includes/header.jsp" %>

<jsp:useBean id="expertises" scope="request" class="java.util.List<bean.ExpertiseBean>" />
<jsp:useBean id="absences" scope="request" class="java.util.List<bean.AbsenceBean>" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
        <link href="styles/base.css" rel="stylesheet">
        <title>Edit Your Information</title>
    </head>
    <body>
         <div class="content">
            <div class="container">
                <form method="post" action="EditAccountServlet">
                    <div class="row text-center">
                        <h1>Edit Account</h1>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col"></div>
                        <div class="col">
                            <label>Name</label>
                        </div>
                        <div class="col">
                            <input name="name" value="<%= account.getFullName()%>">
                        </div>
                        <div class="col"></div>
                    </div>
                    <div class="row">
                        <div class="col"></div>
                        <div class="col">
                            <label>Email</label>
                        </div>
                        <div class="col">
                            <input name="email" value="<%= account.getEmail()%>">
                        </div>
                        <div class="col"></div>
                    </div>
                    <div class="row">
                        <div class="col"></div>
                        <div class="col">
                            <label>Birthdate</label>
                        </div>
                        <div class="col">
                            <input name="birthdate" type="date" value="<%= account.getBirthDate()%>">
                        </div>
                        <div class="col"></div>
                    </div>
                    <div class="row">
                        <div class="col"></div>
                        <div class="col">
                            <label>Password</label>
                        </div>
                        <div class="col">
                            <input name="password" type="password" value="<%= account.getPassword()%>">
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


                    <% if (account != null && account.isIsDoctor()) {
                        %>
                        <div class="row">
                        <div class="col"></div>
                        <div class="col">
                            <label>Expertise</label>
                        </div>
                        <div class="col">
                            <select name="expertise">
                                <% for (ExpertiseBean e : expertises) {
                                    %>
                                    <option class="hover-text" value="<%= e.getExpertiseId()%>"
                                            <%= account.getExpertiseIdFK() == e.getExpertiseId() ? "selected" : ""%>>
                                        <%= e.getName()%>
                                    </option>
                                    <%
                                }
                                %>
                            </select>
                        </div>
                        <div class="col"></div>
                    </div>
                        <%
                    }
                    %>

                    <br>

                    <div class="row">
                        <div class="col"></div>
                        <div class="col-6">
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
                            <input class="btn btn-primary" type="submit" value="Update Account">
                        </div>
                        <div class="col"></div>
                    </div>
                </form>

                <br>
                <br>
                <br>

                <form method="post" action="AbsenceServlet">
                    <div class="row text-center">
                        <h2>Manage Absences</h2>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col"></div>
                        <div class="col">
                            <label>From Date</label>
                        </div>
                        <div class="col">
                            <input name="fromDate" type="date">
                        </div>
                        <div class="col"></div>
                    </div>
                    <div class="row">
                        <div class="col"></div>
                        <div class="col">
                            <label>To Date</label>
                        </div>
                        <div class="col">
                            <input name="toDate" type="date">
                        </div>
                        <div class="col"></div>
                    </div>

                    <br>
                    <div class="row">
                        <div class="col"></div>
                        <div class="col-6">
                        <%
                            if (request.getAttribute("absenceMessage") == null) {
                            } else {
                                %>
                                <%= request.getAttribute("absenceMessage") %>
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
                            <input class="btn btn-primary" type="submit" value="Add Absence">
                        </div>
                        <div class="col"></div>
                    </div>
                </form>
                     
                <br>

                <div class="row">
                    <div class="col"></div>
                    <div class="col-6">
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th scope="col">From Date</th>
                                    <th scope="col">To Date</th>
                                    <th scope="col" colspan="2">Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for (AbsenceBean a : absences) {
                                        %>
                                        <tr>
                                            <form method="post" action="AbsenceUpdateServlet">
                                                <td>
                                                    <input name="fromDate" type="date" value="<%= a.getFromDate() %>">
                                                </td>
                                                <td>
                                                    <input name="toDate" type="date" value="<%= a.getToDate()%>"></td>
                                                <td>
                                                    <input name="absenceId" type="hidden"  value="<%= a.getAbsenceId()%>">
                                                    <input class="btn btn-primary" type="submit" value="Update">
                                                </td>
                                            </form>
                                            <form method="post" action="AbsenceDeleteServlet">
                                                <td>
                                                    <input name="absenceId" type="hidden"  value="<%= a.getAbsenceId()%>">
                                                    <input class="btn btn-danger" type="submit" value="Delete">
                                                </td>
                                            </form>
                                        </tr>
                                        <%
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                    <div class="col"></div>
                </div>
            </div>
        </div>
        <br>
        <br>
        
        <%@include file="includes/footer.jsp" %>
    </body>
</html>
