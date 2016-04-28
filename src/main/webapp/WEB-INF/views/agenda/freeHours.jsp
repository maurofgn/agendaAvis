<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages" var="lang" />

<html>
<head>
	<title><fmt:message key="agenda.hours.free" bundle="${lang}"/></title>
</head>
<body>

<div class="container">
  <h2><fmt:message key="agenda.hours.free" bundle="${lang}"/></h2>
  <br>
  <h3>${freeHoursTitle}</h3>
  <br>

	<div>
	  <table class="table table-hover">
	  
	    <thead>
	      <tr>
	        <th><fmt:message key="agenda.hours" bundle="${lang}" /></th>
	      </tr>
	    </thead>
	    
	    <tbody>
	    <c:forEach items="${freeHours}" var="bean">
	      <tr>
	        <td>
	        
	        <c:if test="${bean.hasFree()}">
		        <a href="<c:url value='/preno-${bean.dayNr}-dd-${bean.hourMinutes}-hh' />">
		        	<c:out value="${bean.hourMinutes}" />
		        </a>
	        </c:if>
	        
	        <c:if test="${!bean.hasFree()}">
		        <c:out value="${bean.hourMinutes}" />
	        </c:if>
	        
	        </td>
	      </tr>
	    </c:forEach>
	    </tbody>
	    
	  </table>
	</div>
	<div>
		<a href="<c:url value='agenda' />" class="btn" >
			<span class="glyphicon glyphicon-step-backward"> <fmt:message key="back" bundle="${lang}"/></span>
		</a>
	</div>
</div>


</body>
</html>
	