<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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

<title><fmt:message key="login.label" bundle="${lang}" /></title>

</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <div class="account-wall">
            
                <form action="login" method="post" class="form-signin" style="position:absolute; width: 100%; top:100px;">
                
					<c:if test="${!empty SPRING_SECURITY_LAST_EXCEPTION}">
						<p class="alert alert-danger"><c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/></p>
						<c:remove scope="session" var="SPRING_SECURITY_LAST_EXCEPTION"/>
					</c:if>

	                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	                <input type="text" class="form-control" id="username" name="ssoId" placeholder="<fmt:message key="label.placeholder.codFisc" bundle="${lang}" />" required autofocus>
	                <input type="password" class="form-control" id="password" name="password" placeholder="<fmt:message key="login.placeholder.password" bundle="${lang}" />" required>
 	                
	                <br>
	                <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="login.button.submit" bundle="${lang}" /></button>
	                
		            <br>
		            <a class="btn btn-default text-center" href=<c:url value='/user/resetPassword' /> ><fmt:message key="login.newPassword" bundle="${lang}" /></a>
            	</form>
            	
            </div>
        </div>
    </div>
</div>
</body>
</html>