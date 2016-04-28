<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%> 
<%-- <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  --%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages" var="lang" />

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII"/>
<title><fmt:message key="label.tipoDonazione.puntoPreleievo" bundle="${lang}" /></title>

</head>
<body>

<div class="container">
  <h2><fmt:message key="label.tipoDonazione.puntoPreleievo" bundle="${lang}" /></h2>

  <table class="table table-hover">
    <thead>
      <tr>
        <th><fmt:message key="label.tipoDonazione.puntoPreleievo" bundle="${lang}" /></th>
      </tr>
    </thead>
    <tbody>
    <c:forEach items="${listTipoDonazPuntiPrel}" var="bean">
      <tr>
        <td>
         <a href="<c:url value='/agenda-${bean.tipoDonaId}-tipoDona-${bean.puntoprelId}-pp' />"> <c:out value="${bean.tipoDonazione} ${bean.puntoprel}" /> </a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  
</div>

</body>
</html>
