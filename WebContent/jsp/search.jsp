<%-- 
    Document   : search
    Author     : Matteo Palumbo <matteopalumbo at mail.com>
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Cerca</title>
        <%@include file="/includes/head.imports.jsp" %>
    </head>
    <body>
      <%@include file="/includes/header.jsp" %>
      <div id="wrapper">
        
        <c:if test="${msg!= null}">
            <div class="messaggio">
              <p>${msg}</p>
            </div>
        </c:if>
        
        <div class="row">
          <h3>Cerca libro</h3>
          <form action="SearchController" method="GET">
          
            <label><strong>Cerca per</strong>
            <select name="category" class="frm-control">
              <option value="titolo" <c:out value="${isTitolo}"/> >Titolo</option>
              <option value="autore" <c:out value="${isAutore}"/> >Autore</option>
              <option value="genere" <c:out value="${isGenere}"/> >Genere</option>
            </select>
          </label>
          
            <input type="text" name="query" value="<c:out value="${query}"/>" />
            <input type="submit" value="Cerca" class="frm-control"/>
          </form>
        </div>
     
        
        
        <c:if test="${!empty risultatoRicerca}">
        
            <div class="row">
              <h3>Risultati</h3>
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
                        <c:forEach items="${risultatoRicerca}" var="libro">
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
        </c:if>
        
      </div>
      <%@include file="/includes/footer.jsp" %>
    </body>
</html>
