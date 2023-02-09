<%-- 
    Document   : CreateAppointment
    Created on : Feb 7, 2023, 1:55:10 AM
    Author     : HYUGA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="bean.AccountBean"%>
<%@include file="includes/header.jsp" %>

<jsp:useBean id="appointment" scope="request" class="bean.AppointmentBean" />
<jsp:useBean id="doctors" scope="request" class="java.util.List<bean.AccountBean>" />
<jsp:useBean id="patients" scope="request" class="java.util.List<bean.AccountBean>" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
        <link href="styles/base.css" rel="stylesheet">
        <title>Appointment</title>
    </head>
    <body> 
        <div class="content">
            <form method="post" action="AppointmentServlet">
                <div class="container">
                    <div class="row text-center">
                        <h1>Appointment</h1>
                    </div>
                    
                    <br>
                    
                    <% if (account.isIsDoctor()) {
                        %>
                        <div class="row">
                            <div class="col"></div>
                            <div class="col">
                                <label>Patient</label>
                            </div>
                            <div class="col">
                                <select name="doctor">
                                    <% for (AccountBean patient : patients) {
                                        %>
                                        <option class="hover-text" value="<%= patient.getAccountId()%>"
                                                <%= patient.getAccountId()== appointment.getAccountPatientIdFK()? "selected" : ""%>>
                                            <%= patient.getFullName() %>
                                        </option>
                                        <%
                                    }
                                    %>
                                </select>
                            </div>
                            <div class="col"></div>
                        </div>
                                
                        <input hidden name="patient" value="<%= account.getAccountId() %>">
                        <%
                    } else {
                        %>
                        <div class="row">
                            <div class="col"></div>
                            <div class="col">
                                <label>Doctor:</label>
                            </div>
                            <div class="col">
                                <select name="doctor">
                                    <% for (AccountBean doctor : doctors) {
                                        %>
                                        <option class="hover-text" value="<%= doctor.getAccountId()%>"
                                                <%= doctor.getAccountId()== appointment.getAccountDoctorIdFK()? "selected" : ""%>>
                                            <%= doctor.getFullName() %>
                                        </option>
                                        <%
                                    }
                                    %>
                                </select>
                            </div>
                            <div class="col"></div>
                        </div>
                                
                        <input hidden name="patient" value="<%= account.getAccountId() %>">
                        <%
                    }
                    %>
                    
                    <br>
                    
                    <div class="row">
                        <div class="col"></div>
                        <div class="col">
                            <label>Date</label>
                        </div>
                        <div class="col">
                            <input name="date" type="date" value="<%= appointment.getDate() %>">
                        </div>
                        <div class="col"></div>
                    </div>
                    
                    <br>
                    
                    <div class="row">
                        <div class="col"></div>
                        <div class="col">
                            <label>Start Time: </label>
                        </div>
                        <div class="col">
                            <input name="startTime" type="time" value="<%= appointment.getStartTime() %>">
                        </div>
                        <div class="col"></div>
                    </div>
                    
                    <br>
                    
                    <div class="row">
                        <div class="col"></div>
                        <div class="col">
                            <label>Duration: </label>
                        </div>
                        <div class="col">
                            <input name="duration" type="time" value="<%= appointment.getDuration() %>">
                        </div>
                        <div class="col"></div>
                    </div>
                    
                    <br>
                    
                    <div class="row">
                        <div class="col"></div>
                        <div class="col-2">
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
                            <input class="btn btn-primary" type="submit"  value="<%= appointment.getAppointmentId() > 0 ? "Update" : "Create" %> Appointment">
                        </div>
                        <div class="col"></div>
                    </div>
                </div>
            </form>
        </div>
        <%@include file="includes/footer.jsp" %>
    </body>
</html>
