<%-- 
    Document   : rankings
    Author     : Matteo Palumbo <matteopalumbo at mail.com>
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Classifiche</title>
        <%@include file="/includes/head.imports.jsp" %>
    </head>
    <body>
      <%@include file="/includes/header.jsp" %>
      <div id="wrapper">
          <div class="row">
            
            <c:choose>
                <c:when test="${!empty ultimiArrivi}">
                    <h3>Ultimi Arrivi</h3>
                    <table >
                        <thead>
                            <th>Titolo</th>
                            <th>Autore</th>
                            <th>Genere</th>
                            <th></th>
                        </thead>
                        <tbody>
                            <c:forEach items="${ultimiArrivi}" var="libro">

                                <tr>
                                    <td><c:out value="${libro.titolo}" /></td>
                                    <td><c:out value="${libro.autore}" /></td>
                                    <td><c:out value="${libro.genere}" /></td>
                                    <td><a href="Libro?id=<c:out value="${libro.id}" />">Visualizza</a></td>
                                </tr>

                            </c:forEach>
                        </tbody>
                    </table>
                    
                </c:when>
                
                <c:otherwise>
                    Nessun libro presente nel catalogo.
                </c:otherwise>
            </c:choose>
          </div>
        <div class="row">
            <c:choose>
                 <c:when test="${!empty piuPrenotati}">
                   <h3>Libri pi&ugrave; prenotati</h3>
                   <table >
                        <thead>
                            <th>Titolo</th>
                            <th>Autore</th>
                            <th>Genere</th>
                            <th>Prenotazioni</th>
                            <th></th>
                        </thead>
                        <tbody>
                            <c:forEach items="${piuPrenotati}" var="libro">

                                <tr>
                                    <td><c:out value="${libro.titolo}" /></td>
                                    <td><c:out value="${libro.autore}" /></td>
                                    <td><c:out value="${libro.genere}" /></td>
                                    <td><c:out value="${libro.contatorePrenotazioni}" /></td>
                                    <td><a href="Libro?id=<c:out value="${libro.id}" />">Visualizza</a></td>
                                </tr>

                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    Nessuna prenotazione &egrave; stata effettuata.
                </c:otherwise>
            </c:choose>
                        
        </div>
        
        
      </div>
      <%@include file="/includes/footer.jsp" %>
    </body>
</html>
