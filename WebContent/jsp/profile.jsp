<%-- 
    Document   : profile
    Author     : Matteo Palumbo <matteopalumbo at mail.com>
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Profilo utente</title>
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
    
    
    <c:choose>
        <c:when test="${!empty utente}">
            <div class="row">
                <h3><i class="fa fa-user fa-3x"></i>Riepilogo account</h3>
                  <table>
                    <thead>
                        <th>Nome</th>
                        <th>Cognome</th>
                        <th>Username</th>
                        <th>Password</th>
                        <th>Email</th>
                        <th>Telefono</th>
                        <th>Utenza</th>
                    </thead>
                    <tbody>
                      <tr>
                        <td><c:out value="${utente.nome}"/></td>
                        <td><c:out value="${utente.cognome}"/></td>
                        <td><c:out value="${utente.username}"/></td>
                        <td><c:out value="${utente.password}"/></td>
                        <td><c:out value="${utente.email}" /></td>
                        <td><c:out value="${utente.telefono}" /></td>
                        <td><c:out value="${utente.tipologiaUtenza}"/></td>
                      </tr>
                    </tbody>
                      
                  </table>
                  <td><a href="Profilo?azione=modifica" class="frm-control">Modifica Informazioni</a></td>

              </div>
              <div class="row" >
                <h3>
                  <i class="fa fa-book fa-3x"></i>Riepilogo prenotazioni
                </h3>
                
                <!--Controlla se ci sono prenotazioni, altrimenti visualizza messaggio-->
                <c:choose>
                    <c:when test="${!empty listaPrenotazioni}">
                        <table >
                            <thead>
                                <th>Titolo</th>
                                <th>Inizio Prenotazione</th>
                                <th>Scadenza</th>
                                <th></th>
                            </thead>
                            <tbody>
                                <c:forEach items="${listaPrenotazioni}" var="prenotazione">
                                    <tr>
                                      <td><c:out value="${prenotazione.titoloLibro}" /></td>
                                        <td><fmt:formatDate type="date" value="${prenotazione.inizioPrenotazione}" /></td>
                                        <td><fmt:formatDate type="date" value="${prenotazione.finePrenotazione}" /></td>

                                        <td>
                                          <form action="Prenotazione" method="POST">
                                              <input type="hidden" readonly="readonly" name="azione" value="consegna" />
                                              <input type="hidden" readonly="readonly" name="idLibro" value="<c:out value="${prenotazione.idLibro}" />" />
                                              <input type="hidden" readonly="readonly" name="idUtente" value="<c:out value="${prenotazione.idUtente}" />" />
                                              <input type="submit" class="frm-control" value="Consegna" />
                                          </form>
                                        </td>
                                    </tr>

                                </c:forEach>
                            </tbody>
                        </table>
                        </c:when>
                    <c:otherwise>
                        <p>Nessuna prenotazione effettuata.</p>
                    </c:otherwise>
                </c:choose>
                  
              </div>
        </c:when>
    </c:choose>
        
    </div>
    <%@include file="/includes/footer.jsp" %>
    
  </body>
</html>
