<%-- 
    Document   : PatientLoginError
    Created on : April 6, 2021, 1:20:30 PM
    Author     : Jason Arnold
    CIST 2373 Java 3 Semester project
    Login Error page for patient login.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="DentistWebApp.Patient"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="stylesheet.css">
        <title>LOGIN ERROR!</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <% Patient p1;
        p1 = (Patient)session.getAttribute("p1");
        p1.display();
        %>
        <div class="center">
            <h2>Error logging in with patient id <% out.println(p1.getPatId()); %></h2><br><br>
            <A href="patientlogin.html">Back to patient login</a><br><br>
            <a href="index.html">Back to main page</a>
        </div>
    </body>
</html>
