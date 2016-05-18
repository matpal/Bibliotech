<%-- 
    Document   : newBook
    Author     : Matteo Palumbo <matteopalumbo at mail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Inserisci Libro</title>
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
            <form method="POST" action='Admin' name="frmAddBook">  
              <input type="hidden" readonly="readonly" name="category" value="libri" />
              <input type="hidden" readonly="readonly" name="libroid" value="<c:out value="${libro.id}" />" />
              <input type="hidden" readonly="readonly" name="contatorePrenotazioni" value="<c:out value="${libro.contatorePrenotazioni}" />" />
              ISBN<br> <input type="text" name="isbn" value="<c:out value="${libro.isbn}" />" /><br><br>
              Titolo<br> <input type="text" name="titolo" value="<c:out value="${libro.titolo}" />" /><br><br>
              Autore<br> <input type="text" name="autore" value="<c:out value="${libro.autore}" />" /><br><br>
              Editore<br> <input type="text" name="editore" value="<c:out value="${libro.editore}" />" /><br><br>
              Genere<br> <input type="text" name="genere" value="<c:out value="${libro.genere}" />" /><br><br>
              Numero Copie<br> <input type="text" name="quantita" value="<c:out value="${libro.quantita}" />" /><br><br>
              
              <input type="submit" value="Invia" class="frm-control"/>  
          </form>      
        </div>
      
      </div>
      <%@include file="/includes/footer.jsp" %>
    </body>
</html>
