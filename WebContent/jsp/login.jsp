<%-- 
    Document   : login
    Author     : Matteo Palumbo <matteopalumbo at mail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <%@include file="/includes/head.imports.jsp" %>
    </head>
    <body>
      <%@include file="/includes/header.jsp" %>
      <div id="wrapper">
      	
       
<%--         <% 
        	String msg = (String) request.getAttribute("msg"); 
        	
        	if(msg != null){
   		%>
   			<div class="messaggio">
   				<p><%= msg %></p>
   			</div>
 		<%  }  %> --%>
 		
 		<c:if test="${msg != null}">
            <div class="messaggio">
              <p>${msg}</p>
            </div>
        </c:if>
        
        
        <div class="row">
            <h3>Login</h3>

              <form action="LoginController" method="POST">
              <table>
                <tr>
                  <td>
                      <label for="usernameMain">Username</label>    
                  </td>
                  <td>
                      <input type="text" name="username" id="usernameMain"/>  
                  </td>
                </tr>
                <tr>
                  <td>
                      <label for="passwordMain">Password</label>
                  </td>
                  <td>
                      <input type="password" name="password" id="passwordMain"/>
                  </td>
                </tr>
              </table>
                                  
                  
<!--                <input type="checkbox" name="rememberMe" id="rememberMeMain" />
                  <label for="rememberMeMain">Ricordami al prossimo accesso</label>
                  <br>-->
                <input type="submit" value="Login" class="frm-control"/>
              </form>

        
        </div>
      </div>
        <%@include file="/includes/footer.jsp" %>
    </body>
</html>
