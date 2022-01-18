<%-- 
    Document   : ApptUpdate
    Created on : Apr 6, 2021, 3:05:05 PM
    Author     : Jason Arnold
    CIST 2373 Semester Project
    Purpose    : Accepts patient info from DisplayPatient.jsp, to allow patient to update or schedule and appointment using ApptUpdateServlet
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="DentistWebApp.Appointment" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="stylesheet.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update or Schedule Appointment</title>
    </head>
    <body style="text-align:center;">
        <jsp:useBean class="DentistWebApp.Patient" id="p1" scope="session" />
        <% Appointment a1 = new Appointment();
        a1.selectDB(p1.getPatId());
        %>
        <div class="center">
        <h1>Update or Schedule your appointment</h1>
        <h2>Current Appointment:</h2>
        
        <form name="apptform" id="apptform" onsubmit="return apptChange(this)">
                <label for="patientin" hidden></label>
                <input type="hidden" id="patientin" name="patientin" value="<%=a1.getPatient() %>">
                <label for="datein">Date / Time:</label>
                <input type="text" id="datein" name="datein" value="<%=a1.getDateTime() %>"
                <label for="dentistin">Dentist</label>
                <input type="text" id="dentistin" name="dentistin" value="<%=a1.getDentist() %>">
                <label for="procin">Procedure</label>
                <input type="text" id="procin" name="procin" value="<%=a1.getProcedure() %>"><br>
        </form><br><br>
        
        <h2>Schedule/update Appointment:</h2>
        <form action="http://localhost:8080/DentistWebApp/ApptUpdateServlet">
            <input type="hidden" id="patient" name="patient" value="<%=a1.getPatient() %>">
            <label for="date">Date and time</label>
            <input type="datetime-local" id="date" name="date" value="<%=a1.getDateTime() %>">
            <label for="dentist">Choose a dentist</label>
            <select name="dentist" id="dentist">
                <option value="D201">Frank Martin</option>
                <option value="D202">Susan Cassidy</option>
                <option value="D203">Jerry York</option>
                <option value="D204">Wayne Patterson</option>
            </select><br>
            <label for="proc">Choose Procedure</label>
            <select name="proc" id="proc">
                <option value="P114">Cleaning/Exam</option>
                <option value="P119">X-Rays</option>
                <option value="P122">Teeth Whitening</option>
                <option value="P321">Cavity Filling</option>
                <option value="P650">Top Dentures</option>
                <option value="P660">Bottom Dentures</option>
                <option value="P780">Crown</option>
                <option value="P790">Root Canal</option>
            </select>
            <input type="submit" value="Schedule/Change Appointment">
        </form>
        </div>
    </body>
</html>
