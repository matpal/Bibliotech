<%-- 
    Document   : navbar
    Author     : Matteo Palumbo <matteopalumbo at mail.com>
--%>

<%@page import="org.psm.mp.models.Utente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<nav>
  <div class="nav-links">
    <a href="Home">Home</a>
    <a href="Cerca"><i class="fa fa-search"></i> Cerca</a>
    <a href="Classifiche"><i class="fa fa-trophy"></i> Classifiche</a>
    <a href="Catalogo"><i class="fa fa-list"></i> Catalogo</a>
  </div>
 
  
  <c:choose>
    <c:when test="${sessionScope.utente==null}">
       
        <div class="right">
          <a href="#" id="login-anchor">Accedi</a> 
          <a href="Registrazione">Registrati</a>
        </div>

        <div id="box-login" class="nav-box">
          <form action="LoginController" method="POST">
            <label for="username">Username</label>
              <input type="text" name="username" id="username"/>
            <label for="password">Password</label>
              <input type="password" name="password" id="password"/>
              <br>
<!--            <input type="checkbox" name="rememberMe" id="rememberMe" />
              <label for="rememberMe">Ricordami al prossimo accesso</label>
              <br>-->
            <input type="submit" value="Login" class="frm-control"/>
          </form>
        </div>

        
    </c:when>
    <c:otherwise>
  
        <div class="right">

          <c:if test="${sessionScope.utente.tipologiaUtenza == 'BIBLIOTECARIO'}">
              <a href="Admin">Amministrazione</a>
          </c:if>

          <a href="Profilo"><i class="fa fa-user"></i>Profilo</a>
          <a href="Logout"><i class="fa fa-sign-out"></i>Logout</a>
        </div>

    </c:otherwise>
  </c:choose>
</nav>
