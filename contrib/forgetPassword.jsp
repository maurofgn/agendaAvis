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
<title><fmt:message key="login.newPassword" bundle="${lang}" /></title>

</head>
<body>
<div class="container">
    <h1><fmt:message key="login.newPassword" bundle="${lang}" /></h1>
    <br/>
    <div class="row">
        <label class="col-sm-1"><fmt:message key="label.user.email" bundle="${lang}" /></label>
        <span class="col-sm-5"><input class="form-control" id="email" name="email" type="email" value="" /></span>
    </div>
    
    
    <div class="row">
        <label class="col-sm-1"><fmt:message key="label.user.codFisc" bundle="${lang}" /></label>
        <span class="col-sm-5"><input class="form-control" id="codFisc" name="codFisc" value="" /></span>
    </div>
    
    
    <br>
    <div class="row">
        <button class="btn btn-primary" type="submit" onclick="resetPass()" ><fmt:message key="message.resetPassword" bundle="${lang}" /></button>
		<a class="btn btn-default" href=<c:url value='/login' /> ><fmt:message key="login.label" bundle="${lang}" /></a>
    </div>

	<br/> 

</div>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<!-- <script th:inline="javascript"> -->
<script>
/*<![CDATA[*/
var serverContext = "/";
function resetPass(){
    var email = $("#email").val();
    var codFisc = $("#codFisc").val();
    
    
    $.ajax({
		type : 'get',
		url : 'resetPassword2',
		data : {email: email, codFisc: codFisc}, 
		success : function(response) {
			window.location.href = serverContext + "login?message=" + data.message;
		},
		error : function(data) {
	    	if(data.responseJSON.error.indexOf("MailError") > -1)
	        {
	            window.location.href = serverContext + "emailError.html";
	        }
	        else{
	            window.location.href = serverContext + "login?message=" + data.responseJSON.message;
	        }
	    }

	});    
    
    
    
//     $.post(serverContext + "user/resetPassword",{email: email, codFisc: codFisc} ,
//     		function(data) {
//             	window.location.href = serverContext + "login?message=" + data.message;
//     		}
//     	)
//     .fail(function(data) {
//     	if(data.responseJSON.error.indexOf("MailError") > -1)
//         {
//             window.location.href = serverContext + "emailError.html";
//         }
//         else{
//             window.location.href = serverContext + "login?message=" + data.responseJSON.message;
//         }
//     });
}

$(document).ajaxStart(function() {
    $("title").html("LOADING ...");
});
/*]]>*/
</script>
</body>

</html>