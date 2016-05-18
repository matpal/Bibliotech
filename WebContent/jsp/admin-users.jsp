<%-- 
    Document   : admin-users
    Author     : Matteo Palumbo <matteopalumbo at mail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Gestione Utenti</title>
        <%@include file="/includes/head.imports.jsp" %>
    </head>
    <body>
    <%@include file="/includes/header.jsp" %>
    <div id="wrapper">
      
    <c:if test="${msg != null}">
        <div class="messaggio">
            <p>${msg}</p>
        </div>
    </c:if>
    <c:choose>
        <c:when test="${!empty listaUtenti}">
          
            <div class="row">    
            <h3>Elenco utenti</h3>
            <table >
                <thead>
                    <th>Id</th>
                    <th>Nome</th>
                    <th>Cognome</th>
                    <th>Username</th>
                    <th>Telefono</th>
                    <th>Utenza</th>
                    <th colspan=2>Azioni</th>
                </thead>
                <tbody>
                    <c:forEach items="${listaUtenti}" var="u">
                        <tr>
                            <td><c:out value="${u.id}" /></td>
                            <td><c:out value="${u.nome}" /></td>
                            <td><c:out value="${u.cognome}" /></td>
                            <td><c:out value="${u.username}" /></td>
                            <td><c:out value="${u.telefono}" /></td>
                            <td><c:out value="${u.tipologiaUtenza}" /></td>
                            <c:choose>
                                <c:when test="${u.tipologiaUtenza == 'CLIENTE'}">
                                    <td><a href="Admin?action=promuovi&category=utenti&id=<c:out value="${u.id}" />" class="green-text"><i class="fa fa-arrow-up"></i></a></td>
                                </c:when>
                                <c:otherwise>
                                    <td></td>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${u.id == utente.id}">
                                    <td></td>
                                </c:when>
                                <c:otherwise>
                                    <td><a href="Admin?action=elimina&category=utenti&id=<c:out value="${u.id}" />" class="red-text"><i class="fa fa-times"></i></a></td>
                                </c:otherwise>
                            </c:choose>

                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            </div>
        </c:when>
        </c:choose>
       
        </div>
    <%@include file="/includes/footer.jsp" %>
    </body>
</html>
