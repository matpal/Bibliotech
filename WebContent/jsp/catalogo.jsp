<%-- 
    Document   : bookList
    Author     : Matteo Palumbo <matteopalumbo at mail.com>
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="org.psm.mp.models.Libro"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Catalogo</title>
        <%@include file="/includes/head.imports.jsp" %>
    </head>
    <body>
        <%@include file="/includes/header.jsp" %>
        <div id="wrapper">

          <c:choose>
            <c:when test="${!empty listaLibri}">
          <div class="row">
            <h3>Catalogo</h3>
            <table >
                <thead>
                    <th>Isbn</th>
                    <th>Titolo</th>
                    <th>Autore</th>
                    <th>Editore</th>
                    <th>Genere</th>
                    <th># Copie</th>
                    <th></th>
                </thead>
                <tbody>
                    <c:forEach items="${listaLibri}" var="libro">
                    
                        <tr>
                            <td><c:out value="${libro.isbn}" /></td>
                            <td><c:out value="${libro.titolo}" /></td>
                            <td><c:out value="${libro.autore}" /></td>
                            <td><c:out value="${libro.editore}" /></td>
                            <td><c:out value="${libro.genere}" /></td>
                            <td><c:out value="${libro.quantita}" /></td>
                            <td><a href="Libro?id=<c:out value="${libro.id}" />">Visualizza</a></td>
                        </tr>
                    
                    </c:forEach>
                </tbody>
            </table>
          </div>

          </c:when>
            <c:otherwise>
                <p>Nessun libro presente nel catalogo.</p>
            </c:otherwise>
        </c:choose>

        </div>
        <%@include file="/includes/footer.jsp" %>
    </body>
</html>
