<%-- 
    Document   : DentistLoginError
    Created on : April 6, 2021, 1:20:30 PM
    Author     : Jason Arnold
    CIST 2373 Java 3 Semester project
    Login Error page for dentist login.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="DentistWebApp.Dentist"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="stylesheet.css">
        <title>LOGIN ERROR!</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <% Dentist d1;
        d1 = (Dentist)session.getAttribute("d1");
        d1.display();
        %>
        <div class="center">
            <h2>Error logging in with dentist id <% out.println(d1.getDentId()); %></h2><br><br>
            <A href="dentistlogin.html">Back to dentist login</a><br><br>
            <a href="index.html">Back to main page</a>
        </div>
    </body>
</html>
