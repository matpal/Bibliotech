<%-- 
    Document   : book
    Author     : Matteo Palumbo <matteopalumbo at mail.com>
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>

    <c:choose>
        <c:when test="${!empty libro}">
            <title>${libro.titolo} di ${libro.autore}</title>
        </c:when>
            <c:otherwise>
                <title>Libro non trovato</title>
            </c:otherwise>
    </c:choose>
  
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
  
        <c:choose>
            <c:when test="${!empty libro}">
            <h3><c:out value="${libro.titolo}" /></h3> 
            di 
            <h4><c:out value="${libro.autore}"/></h4>
            <p><span class="green-text">Casa editrice:</span> 
            <c:out value="${libro.editore}"/></p>
            <p><span class="green-text">Genere:</span> 
            <c:out value="${libro.genere}"/></p>
            <p><span class="green-text">Disponibilit&agrave;:</span>
            <c:out value="${libro.quantita}" /></p>
            
            <c:if test="${!empty utente}">
                <c:if test="${libro.disponibile}">
                    <form method="POST" action="Prenotazione">
                      <input type="hidden" readonly="readonly" name="azione" value="prenota" />
                      <input type="hidden" readonly="readonly" name="idLibro" value="<c:out value="${libro.id}" />" />
                      <input type="hidden" readonly="readonly" name="idUtente" value="<c:out value="${utente.id}" />" />
                      <input type="submit" class="frm-control" value="Prenota" />
                    </form>
                </c:if>
                
            </c:if>
        </c:when>
        <c:otherwise>
        
            <p>Libro non trovato</p>
        
        </c:otherwise>
        </c:choose>
      </div>
    
    </div>
    <%@include file="/includes/footer.jsp" %>
    
  </body>
</html>
