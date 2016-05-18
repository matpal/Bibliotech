<%-- 
    Document   : admin-books
    Author     : Matteo Palumbo <matteopalumbo at mail.com>
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Gestione Libri</title>
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

        <div class="row">
            <a href="Admin?action=inserisci&category=libri" class="frm-control">Aggiungi un libro</a>
        </div>
        
        <c:choose>
            <c:when test="${!empty listaLibri}">
            <div class="row">
              <h3>Elenco Libri</h3>
            <table >
                <thead>
                    <th>Isbn</th>
                    <th>Titolo</th>
                    <th>Autore</th>
                    <th>Editore</th>
                    <th>Genere</th>
                    <th># Copie</th>
                    <th># Prenotazioni</th>
                    <th colspan=2>Azioni</th>
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
                            <td><c:out value="${libro.contatorePrenotazioni}" /></td>

                            <td><a href="Admin?action=modifica&category=libri&id=<c:out value="${libro.id}" />" class="green-text"><i class="fa fa-pencil"></i></a></td>
                            <td><a href="Admin?action=elimina&category=libri&id=<c:out value="${libro.id}" />" class="red-text"><i class="fa fa-times"></i></a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            </div>
        </c:when>
        <c:otherwise>


        <p>Non ci sono libri da visualizzare</p>

        </c:otherwise>
        </c:choose>
      </div>
      <%@include file="/includes/footer.jsp" %>  
    </body>
</html>
