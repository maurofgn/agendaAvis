<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
		<div id="main" >
			<decorator:body />
		</div>
	
		<footer>
			<div class="footer">
			  <p>&copy; <script>document.write(new Date().getFullYear())</script>  - <fmt:message key="footer.info.mail" bundle="${lang}" />: <a href="mailto:mesis@mesis.com">MeSIS</a>.</p>
			</div>
		</footer>
	</div>
	
</body>
</html>
