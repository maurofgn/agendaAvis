<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" /> 
<fmt:setLocale value="${language}" /> 
<fmt:setBundle basename="messages" var="lang" /> 

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>User Registration Form</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body>
	<div class="success">
		Messaggio di conferma : ${success}
		<br>
		Vuoi <a href="<c:url value='/newUser' />">aggiungere altri Utenti</a>?
		<br/>
		avere <a href="<c:url value='/userList' />">la lista utenti</a> 
		<fmt:message key="or" bundle="${lang}"/>
		<a href="<c:url value="/logout" />"><fmt:message key="logout" bundle="${lang}"/></a>	
	</div>
	
</body>
</html>