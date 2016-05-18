<%-- 
    Document   : changeProfile
    Author     : Matteo Palumbo <matteopalumbo at mail.com>
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Modifica Profilo</title>
        <%@include file="/includes/head.imports.jsp" %>
    </head>
    <body>
      <%@include file="/includes/header.jsp" %>
        
          <div id="wrapper">
            <h3>Modifica Profilo</h3>
            <form method="POST" action='Profilo' name="frmChangeProfile">  

                <input type="hidden" readonly="readonly" name="id" value="<c:out value="${utente.id}" />" />
                Nome<br> <input type="text" name="nome" value="<c:out value="${utente.nome}" />" /><br><br>
                Cognome<br> <input type="text" name="cognome" value="<c:out value="${utente.cognome}" />" /><br><br>
                Username<br> <input type="text" name="username" value="<c:out value="${utente.username}" />" readonly="readonly"/><br><br>
                Password<br> <input type="text" name="password" value="<c:out value="${utente.password}" />" /><br><br>
                Email<br> <input type="text" name="email" value="<c:out value="${utente.email}" />" /><br><br>
                Telefono<br> <input type="text" name="telefono" value="<c:out value="${utente.telefono}" />" /><br><br>
                Utenza<br> <input type="text" name="utenza" value="<c:out value="${utente.tipologiaUtenza}" />" readonly="readonly"/><br><br>

                <input type="submit" value="Invia" />  
            </form>    
          </div>
      <%@include file="/includes/footer.jsp" %>
    </body>
</html>
