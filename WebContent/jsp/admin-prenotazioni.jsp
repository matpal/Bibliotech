<%-- 
    Document   : admin-prenotazioni
    Author     : Matteo Palumbo <matteopalumbo at mail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Gestione Prenotazioni</title>
        <%@include file="/includes/head.imports.jsp" %>
    </head>
    <body>
        <%@include file="/includes/header.jsp" %>
            <div id="wrapper">
                <c:if test="${requestScope.msg != null}">
                    <div class="messaggio">
                      <p>${requestScope.msg}</p>
                    </div>
                </c:if>
              
                <c:choose>
                    <c:when test="${!empty listaPrenotazioni}">
                    <div class="row">
                    <h3>Elenco Prenotazioni</h3>
                    <table >
                        <thead>
                            <th>Id</th>
                            <th>id Utente</th>
                            <th>id Libro</th>
                            <th>Titolo Libro</th>
                            <th>Inizio</th>
                            <th>Fine</th>
                            <th>Stato</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${listaPrenotazioni}" var="p">
                                <tr>
                                    <td><c:out value="${p.id}" /></td>
                                    <td><c:out value="${p.idUtente}" /></td>
                                    <td><c:out value="${p.idLibro}" /></td>
                                    <td><c:out value="${p.titoloLibro}" /></td>
                                    <td><c:out value="${p.inizioPrenotazione}" /></td>
                                    <td><c:out value="${p.finePrenotazione}" /></td>
                                    <c:choose>
                                        <c:when test="${p.isOver()}">
                                            <td class="statusError">SCADUTA</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td class="statusOk">Regolare</td>
                                        </c:otherwise>
                                    </c:choose>
                                    
                                    
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    </div>
                    </c:when>
                    <c:otherwise>

                    <div class="row">
                        <p>Non ci sono prenotazioni attive</p>
                    </div>
                        
                    </c:otherwise>
                    </c:choose>
            </div>
        <%@include file="/includes/footer.jsp" %>
    </body>
</html>
