<%-- 
    Document   : index
    Author     : Matteo Palumbo <matteopalumbo at mail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>

<!DOCTYPE html>
<html>
  <head>
    <title>BiblioTech</title>
    <%@include file="/includes/head.imports.jsp" %>
    
  </head>
  <body>
    <%@include file="/includes/header.jsp" %>  
    
    <div id="wrapper">
      <div class="row">

        <img src="img/background.jpg" />
        <div id="poesia">
            <p>
              Nessun vascello c'&egrave; che come un libro<br>
              possa portarci in contrade lontane<br>
              n&eacute; corsiere che superi la pagina<br>
              d'una poesia al galoppo -<br>
              Questo viaggio pu&ograve; farlo anche il pi&ugrave; povero<br>
              senza pagare nulla -<br>
              tant'&egrave; frugale il carro che trasporta<br>
              l'anima umana.
              
            </p>
            <em>Emily Dickinson</em>
            
        </div>
        

      </div>
      <!--row-->

    </div>
    <!--wrapper-->
    
    <%@include file="/includes/footer.jsp" %>
    
  </body>
</html>
