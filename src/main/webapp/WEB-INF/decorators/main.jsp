<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages" var="lang" />


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    
    <link href="<c:url value='images/favicon.ico'/>" rel="shortcut icon" type="image/x-icon" /> 
<!-- 	<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"> -->
<!-- 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> -->
<!-- 	<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script> -->
<!-- 	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> -->
<!-- 	<link rel="stylesheet" type="text/css" href="css/style.css" /> -->
	
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	
	
	<title><decorator:title default="Trasfusional Medical Management" /></title>
	<decorator:head/>
	<!--[if IE]>
		<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
</head>
<body>
	      <div class="generic-container">
	<header>
		<div id="headerLogo" >
			<a href="<c:url value='/'/>">
			    <img src="<c:url value='/static/images/logo.png'/>" alt="logo"/>
			</a>
			
				<aside class='right'>
					<fmt:message key="login.label.username" bundle="${lang}" />: ${sessionScope.user}
			        <a data-align="left" href="LogoutServlet" data-click="logoff">Log Off</a>
				</aside>
			
		</div>
		
	</header>
	<aside>
        <ul>
	        <li><a href="<%=request.getContextPath()%>">Anagrafica 1</a></li>
	        <li><a href="<%=request.getContextPath()%>">Anagrafica 2</a></li>
        </ul>
	</aside>
	<section>
	  <article>
	    	<decorator:body/>
	  </article>
	</section>
	<footer>
	<div class="footer">
	  <p>&copy; <script>document.write(new Date().getFullYear())</script>  - <fmt:message key="footer.info.mail" bundle="${lang}" />: <a href="mailto:mesis@mesis.com">MeSIS</a>.</p>
	</div>
	</footer>
	      </div>
</body>
</html>


