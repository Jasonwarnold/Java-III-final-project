<%-- 
    Document   : DisplayDentist
    Created on : Mar 25, 2021, 12:42:13 PM
    Author     : Jason Arnold
    CIST 2373 Java 3 Semester project
    Purpose    : Accept dentist information from dentistlogin.html to display dentist information and appointments.  allows dentist to change info via DentistUpdateServlet
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="DentistWebApp.Dentist" import="DentistWebApp.Appointment" import="DentistWebApp.ApptList" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="stylesheet.css">
        <title>Dentist Information</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <jsp:useBean class="DentistWebApp.Dentist" id="d1" scope="session" />
        <div class="center">
            <h1>Dentist Information</h1>
            <h3>To change Dentist Info, update the field, and press "Change Info"</h3>
            <form action="http://localhost:8080/DentistWebApp/DentistUpdateServlet">
            <label for="patID">Dentist Id:</label>
            <input type="text" id="dentId" name="dentId" value="<%=d1.getDentId() %>" readonly>
            <label for="password">Password:</label>
            <input type="text" id="password" name="password" value="<%=d1.getPassword() %>">
            <label for="fName">First Name:</label>
            <input type="text" id="fName" name="fName" value="<%=d1.getFName() %>">
            <label for="lName">Last Name:</label>
            <input type="text" id="lName" name="lName" value="<%=d1.getLName() %>">
            <label for="address">Office:</label>
            <input type="text" id="office" name="office" value="<%=d1.getOffice() %>">
            <label for="email">Email:</label>
            <input type="text" id="email" name="email" value="<%=d1.getEmail() %>">
            <input type="submit" value="Change Info">
            <input type="reset" value="Clear"><br>
            </form><br><br>
            <table border="2" class="center">
            <tr><th colspan="4">List of appointments for <jsp:getProperty name="d1" property="dentId" /></th></tr>
            <tr><th>Date</th><th>Patient</th><th>Dentist</th><th>Procedure</th></tr>
            <%  ApptList appts = d1.getApptList();
            for(int i=0; i<appts.count; i++)
            {
                Appointment a1 = appts.getAppointment(i);
                String date = a1.getDateTime();
                String patient = a1.getPatient();
                String dentist = a1.getDentist();
                String procedure = a1.getProcedure();
            
               %>
            
            <tr>
                <td><%=date %></td>
                <td><%=patient %></td>
                <td><%=dentist %></td>
                <td><%=procedure %></td>
            
            </tr>
            <%}%>
            </table><br>
        </div>
    </body>
</html>