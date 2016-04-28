<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Agenda Avis</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body>
	<strong>${user}</strong>, Mi dispiace, ma non hai i permessi sufficienti per avere accesso alla risorsa richiesta.
	<br/>
	<a href="<c:url value="/" />">Torna alla pagina principale</a> oppure <a href="<c:url value="/logout" />">Esci</a>
</body>
</html>