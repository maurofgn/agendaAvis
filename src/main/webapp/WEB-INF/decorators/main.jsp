<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%-- <%@include file="/WEB-INF/template/gesCache.jsp"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages" var="lang" />

<!DOCTYPE html>
<html lang="it">
<head>
	<%@include file="/includes/header.jsp"%>
    <link href="<c:url value='/static/images/favicon.ico'/>" rel="shortcut icon" type="image/x-icon" /> 
	<title><decorator:title default="Prenotazione donazioni" /></title>
	<decorator:head/>
	<!--[if IE]>
		<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
</head>
<body>
	<div id="layout" style="  height: 100%;">
		<nav class="navbar navbar-default">
		  <div class="container-fluid">
		    <div class="navbar-header">
		      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>                        
		      </button>
		      <img src="<c:url value='/static/images/logo.png'/>" alt="logo"/>
		    </div>
		    <div class="collapse navbar-collapse" id="myNavbar">
		      <ul class="nav navbar-nav">
		        <li><a href="<c:url value='/'/>">Home</a></li>
		      </ul>
		      <ul class="nav navbar-nav navbar-right">
			    <sec:authorize access="isAuthenticated()"> 
			        <li><a href="logout"><fmt:message key="logout" bundle="${lang}" /> <sec:authentication property="principal.username" /></a> </li>
			        <li><a href="changePassword"><fmt:message key="message.changePassword" bundle="${lang}" /></a> </li>
			    </sec:authorize>      
		      </ul>
		    </div>
		  </div>
		</nav>

		<a class="menu-link" id="menuLink" href="#menu"> <span></span></a>
<%-- 		<%@include file="/WEB-INF/template/menu/menu.jsp"%> --%>
<%-- 		<%@include file="/account/header_login.jsp"%> --%>
		
		<div id="main" >
			<decorator:body/>
		</div>
	
		<footer>
			<div class="footer">
			  <p>&copy; <script>document.write(new Date().getFullYear())</script>  - <fmt:message key="footer.info.mail" bundle="${lang}" />: <a href="mailto:mesis@mesis.com">MeSIS</a>.</p>
			</div>
		</footer>
		
	</div>
	
</body>
</html>
