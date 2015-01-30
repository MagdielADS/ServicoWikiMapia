<%-- 
    Document   : result
    Created on : 02/12/2014, 21:12:19
    Author     : Magdiel Ildefonso
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <p>
            ${resposta.title}</br>
            ${resposta.description}
        </p>
        
        <c:forEach var="point" items="${resposta.points}">
            <p>
                ${point}
            </p>
        </c:forEach>
    </body>
</html>
