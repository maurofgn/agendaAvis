<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<%-- <%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %> --%>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages" var="lang" />

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="<c:url value='/static/images/favicon.ico'/>" rel="shortcut icon" type="image/x-icon" /> 

	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	
<title>Show All</title>
</head>
<body>
    <div class="generic-container">
    <table border=1>
        <thead>
            <tr>
               	<th><fmt:message key="anagrafica.label.nome" bundle="${lang}" /></th>
               	<th><fmt:message key="anagrafica.label.cognome" bundle="${lang}" /></th>
               	<th><fmt:message key="anagrafica.label.citta" bundle="${lang}" /></th>
               	<th><fmt:message key="anagrafica.label.datanascita" bundle="${lang}" /></th>
<!-- 				<th>prov</th>
				<th>indirizzo</th>
				<th>telefono</th>
				<th>mail</th>
				<th>codFisc</th>
				<th>psw</th>
				<th>utente</th>
				<th>ruolo</th>
				<th>Nascita</th>
				<th>sesso</th> -->
<!--                 <th colspan=2>Action</th> -->
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${anagraficaList}" var="bean">
                <tr>
                	<td>
                	<a href="<c:url value='/edit-${bean.id}-anagrafica' />">${bean.nome}</a>
                	</td>
					<td><c:out value="${bean.cognome}" /></td>
					<td><c:out value="${bean.rescitta}" /></td>
<%-- 					<td align="center"><fmt:formatDate pattern="dd/MMM/yyyy" value="${bean.datanascita}" /></td> --%>
					
					<td align="center">
					<fmt:formatDate value="${bean.datanascita}" pattern="dd/MMM/yyyy"/>
					
					</td>
<%-- 					
					<td><c:out value="${bean.sesso}" /></td>
					 --%>
					
<%--                     <td><a href="PersonaController?action=edit&id=<c:out value="${bean.id}"/>">Update</a></td> --%>
<%--                     <td><a href="PersonaController?action=delete&id=<c:out value="${bean.id}"/>">Delete</a></td> --%>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <p>
    	<a href="<c:url value='/new' />"><fmt:message key="add" bundle="${lang}"/></a>
       	<a href="<c:url value='/listPara' />"><fmt:message key="search" bundle="${lang}"/></a>
    </p>
</div>
</body>

