<%-- 
    Document   : EditAppointment
    Created on : Feb 7, 2023, 2:41:08 AM
    Author     : HYUGA
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
        <title>Edit Appointment</title>
    </head>
    <body> 
        <div class="content">
            <form method="post" action="AppointmentServlet">
                <div class="container">
                    <div class="row text-center">
                        <h1>Edit Appointment</h1>
                    </div>
                    
                    <br>
                    
                    <div class="row">
                        <div class="col"></div>
                        <div class="col">
                            <label>Appointment ID: </label>
                        </div>
                        <div class="col">
                            <input name="appointmentId">
                        </div>
                        <div class="col"></div>
                    </div>
                    
                    <br>
                    
                    <div class="row">
                        <div class="col"></div>
                        <div class="col">
                            <label>Doctor's Name:</label>
                        </div>
                        <div class="col">
                            <input name="accountDoctorId">
                        </div>
                        <div class="col"></div>
                    </div>
                    
                    <br>
                    
                    <div class="row">
                        <div class="col"></div>
                        <div class="col">
                            <label>Patient's Name:</label>
                        </div>
                        <div class="col">
                            <input name="accountPatientId">
                        </div>
                        <div class="col"></div>
                    </div>
                    
                    <br>
                    
                    <div class="row">
                        <div class="col"></div>
                        <div class="col">
                            <label>Date</label>
                        </div>
                        <div class="col">
                            <input name="date" type="date">
                        </div>
                        <div class="col"></div>
                    </div>
                    
                    <br>
                    
                    <div class="row">
                        <div class="col"></div>
                        <div class="col">
                            <label>Start Time</label>
                        </div>
                        <div class="col">
                            <input name="startTime" type="time">
                        </div>
                        <div class="col"></div>
                    </div>
                    
                    <br>
                    
                    <div class="row">
                        <div class="col"></div>
                        <div class="col">
                            <label>Duration</label>
                        </div>
                        <div class="col">
                            <input name="duration" type="time">
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
                            <input class="btn btn-primary" type="submit" value="Edit Appointment">
                        </div>
                        <div class="col"></div>
                    </div>
                </div>
            </form>
        </div>
        <%@include file="includes/footer.jsp" %>
    </body>
</html>