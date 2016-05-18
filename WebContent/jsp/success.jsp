<%-- 
    Document   : success
    Author     : Matteo Palumbo <matteopalumbo at mail.com>
--%>


<%@page session="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <title>Registrazione completata</title>
        <%@include file="/includes/head.imports.jsp" %>
    </head>
    <body>
      <%@include file="/includes/header.jsp" %>
      <div id="wrapper">
        <div class="row">
            <h1>Benvenuto in BiblioTech!</h1>
            <p>La registrazione ha avuto esito positivo!</p>

            <a href="Home">Torna alla home</a>    
        </div>
      </div>
    <%@include file="/includes/footer.jsp" %>
    </body>
</html>
