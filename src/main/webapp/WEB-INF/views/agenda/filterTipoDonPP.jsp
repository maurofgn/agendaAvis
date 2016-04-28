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
<title><fmt:message key="login.label" bundle="${lang}" /></title>

</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <h1 class="text-center login-title"><fmt:message key="filter.tipoDona.puntoPrelievo" bundle="${lang}" /></h1>
            <div class="account-wall">
                <form action="login" method="post" class="form-signin">
                
				<c:if test="${param.error != null}">
					<div class="alert alert-danger">
						<p>${error}</p>
					</div>
				</c:if>
				
				<c:if test="${not empty msg}">
					<div class="alert alert-danger">${msg}</div>
				</c:if>                
                
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                
                <input type="text" class="form-control" id="username" name="ssoId" placeholder="Email" required autofocus>
                <input type="text" class="form-control" id="password" name="password" placeholder="Password" required>
                
                 <br>
                <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="submit" bundle="${lang}" /></button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>