<%-- 
    Document   : VistaJSP
    Created on : 27-02-2025, 1:02:54 p. m.
    Author     : nelsonstuardo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <c:forEach var="tempProductos" items="${lista_productos}">
            ${tempProductos}<br>
        </c:forEach>
    </body>
</html>
