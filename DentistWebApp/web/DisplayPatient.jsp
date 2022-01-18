<%-- 
    Document   : DisplayPatient
    Created on : Mar 25, 2021, 12:41:57 PM
    Author     : Jason Arnold
    CIST 2373 Java 3 Semester Project
    Purpose    : Display patient information forwarded from dentistlogin.html
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="DentistWebApp.Patient" import="DentistWebApp.Appointment"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="stylesheet.css">
        <title>Patient Information</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body style="text-align:center;">
        <jsp:useBean class="DentistWebApp.Patient" id="p1" scope="session" />
        <div class="center">
            <h1>Patient Information</h1>
            <h3>To change patient Info, update the field, and press "Change Info"</h3>
            <form action="http://localhost:8080/DentistWebApp/PatientUpdateServlet">
                <label for="patId">Patient Id:</label>
                <input type="text" id="patId" name="patId" value="<%=p1.getPatId() %>" readonly>
                <label for="password">Password:</label>
                <input type="text" id="password" name="password" value="<%=p1.getPassword() %>">
                <label for="fName">First Name:</label>
                <input type="text" id="fName" name="fName" value="<%=p1.getFName() %>">
                <label for="lName">Last Name:</label>
                <input type="text" id="lName" name="lName" value="<%=p1.getLName() %>">
                <label for="address">Address:</label>
                <input type="text" id="address" name="address" value="<%=p1.getAddress() %>">
                <label for="email">Email:</label>
                <input type="text" id="email" name="email" value="<%=p1.getEmail() %>">
                <label for="insurance">Insurance:</label>
                <input type="text" id="insurance" name="insurance" value="<%=p1.getInsurance() %>">
                <input type="submit" value="Change Info"><br>
            </form>
            <br><br>
            <form name="apptform" id="apptform" action="http://localhost:8080/DentistWebApp/ApptUpdate.jsp">
                <%Appointment a1 = p1.getAppt(); %>
                <label>Next Appointment</label>
                <label for="patient" hidden></label>
                <input type="hidden" id="patient" name="patient" value="<%=a1.getPatient() %>">
                <label for="datetime">Date / Time:</label>
                <input type="text" id="date" name="date" value="<%=a1.getDateTime() %>"
                <label for="dentist">Dentist</label>
                <input type="text" id="dentist" name="dentist" value="<%=a1.getDentist() %>">
                <label for="proc">Procedure</label>
                <input type="text" id="proc" name="proc" value="<%=a1.getProcedure() %>">
                <input type="submit" value="Schedule/Change Appointment"><br>
            </form><br><br>
        </div>
    </body>
</html>
