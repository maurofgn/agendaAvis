<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" /> 
<fmt:setLocale value="${language}" /> 
<fmt:setBundle basename="messages" var="lang" /> 

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><fmt:message key="user.List.Title" bundle="${lang}"/></title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
	<div class="generic-container">
		<div class="panel panel-default">
			  <!-- Default panel contents -->
		  	<div class="panel-heading"><span class="lead"><fmt:message key="user.List.Title" bundle="${lang}"/></span></div>
			<table class="table table-hover">
	    		<thead>
		      		<tr>
				        <th><fmt:message key="user.label.firstName" bundle="${lang}"/></th>
				        <th><fmt:message key="user.label.lastName" bundle="${lang}"/></th>
				        <th><fmt:message key="user.label.email" bundle="${lang}"/></th>
				        <th><fmt:message key="user.label.ssoId" bundle="${lang}"/></th>
						<sec:authorize access="hasRole('ADMIN')">
					        <th width="100"></th>
					        <th width="100"></th>
						</sec:authorize>
					</tr>
		    	</thead>
	    		<tbody>
				<c:forEach items="${users}" var="user">
					<tr>
						<td>${user.firstName}</td>
						<td>${user.lastName}</td>
						<td>${user.email}</td>
						<td>${user.ssoId}</td>
						<sec:authorize access="hasRole('ADMIN')">
							<td><a href="<c:url value='/edit-user-${user.ssoId}' />" class="btn btn-success custom-width"><fmt:message key="edit" bundle="${lang}"/></a></td>
							<td><a href="<c:url value='/delete-user-${user.ssoId}' />" class="btn btn-danger custom-width"><fmt:message key="delete" bundle="${lang}"/></a></td>
						</sec:authorize>
					</tr>
				</c:forEach>
	    		</tbody>
	    	</table>
		</div>
		<sec:authorize access="hasRole('ADMIN')">
		 	<div class="well">
		 		<a href="<c:url value='/newUser' />"><fmt:message key="user.label.add" bundle="${lang}"/></a>
		 	</div>
		</sec:authorize>
   	</div>
</body>
</html>