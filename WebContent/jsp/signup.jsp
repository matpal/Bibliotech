<%-- 
    Document   : register
    Author     : Matteo Palumbo <matteopalumbo at mail.com>
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Registrazione</title>
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
        <div id="box-iscrizione">
          <h1>Modulo di Registrazione</h1>
          <form action="RegisterController" method="POST" >

          <label for="username-reg">Username</label><br>
          <input type="text" name="username" id="username-reg"/><br>
          <label for="email-reg">Email</label><br>
          <input type="text" name="email" id="email-reg"/><br>
          <label for="password-reg">Password</label><br>
          <input type="password" name="password" id="password-reg"/><br>
          <label for="conferma-psw-reg">Conferma password</label><br>
          <input type="password" name="conferma-password" id="conferma-psw-reg"/><br>
          <label for="nome-reg">Nome</label><br>
          <input type="text" name="nome" id="nome-reg"/><br>
          <label for="cognome-reg">Cognome</label><br>
          <input type="text" name="cognome" id="cognome-reg"/><br>
          <label for="telefono-reg">Telefono</label><br>
          <input type="text" name="telefono" id="telefono-reg"/><br>
          Utenza<br>
          <input type="radio" name="utenza" id="cliente-reg" value="Cliente" checked>
            <label for="cliente-reg" class="normal-font">Cliente</label><br>
          <input type="radio" name="utenza" id="bibliotecario-reg" value="Bibliotecario">
            <label for="bibliotecario-reg" class="normal-font">Bibliotecario</label>
          <br>
          <input type="submit" value="Conferma" class="frm-control"/>
        </form>
    </div>
        
        
      </div>
      <%@include file="/includes/footer.jsp" %>
    </body>
</html>
