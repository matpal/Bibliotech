<%-- 
    Document   : admin
    Author     : Matteo Palumbo <matteopalumbo at mail.com>
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Pannello di amministrazione</title>
        <%@include file="/includes/head.imports.jsp" %>
    </head>
    <body>
      <%@include file="/includes/header.jsp" %>  
      <div id="wrapper">
        
        
        <div class="row">
            <c:choose>
                <c:when test="${utente.tipologiaUtenza == 'BIBLIOTECARIO'}">
                    <div class="row">
                      <a href="Admin?action=lista&category=libri" ><h3><i class="fa fa-book fa-2x"></i>Libri</h3></a>
                    </div>

                    <div class="row">
                      <a href="Admin?action=lista&category=utenti" ><h3><i class="fa fa-users fa-2x"></i>Utenti</h3></a>
                    </div>

                    <div class="row">
                      <a href="Admin?action=lista&category=prenotazioni" ><h3 ><i class="fa fa-calendar fa-2x"></i>Prenotazioni</h3></a>
                    </div>
                </c:when>
                <c:otherwise>
                    <p>Non hai i permessi necessari per visualizzare questa pagina.</p>
                </c:otherwise>
                    
            </c:choose>

        </div>    
        
        </div>
        <%@include file="/includes/footer.jsp" %>
    </body>
</html>
