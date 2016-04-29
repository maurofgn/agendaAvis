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
<title><fmt:message key="message.changePassword" bundle="${lang}" /></title>
</head>
<body>

    <div class="container">
        <div class="row">
        <div id="errorMsg" class="alert alert-danger" style="display:none"></div>
            <h1><fmt:message key="message.changePassword" bundle="${lang}" /></h1>
            <div >
                <br/>
                
                    <label class="col-sm-2"><fmt:message key="label.user.oldPassword" bundle="${lang}"/></label>
                    <span class="col-sm-5"><input class="form-control" id="oldpass" name="oldpassword" type="password" value="" /></span>
                    <span class="col-sm-5"></span>
<br/><br/>         
                    <label class="col-sm-2"><fmt:message key="label.user.newPassword" bundle="${lang}"/></label>
                    <span class="col-sm-5"><input class="form-control" id="pass" name="password" type="password" value="" 
                    	onkeypress='$("#errornNewPassword").hide(); $("#errornFormalNewPassword").hide(); $("#errEqPassword").hide();'/>
                    </span>
                    <span id="errornNewPassword" class="alert alert-danger" style="display:none">
                    	<fmt:message key="message.password.minLength" bundle="${lang}">
                    		<fmt:param value="${pswMinLen}"/>
						</fmt:message>
                    </span>
                    <span id="errornFormalNewPassword" class="alert alert-danger" style="display:none">
                    	<fmt:message key="message.password.minLength" bundle="${lang}">
                    		<fmt:param value="${pswMinLen}"/>
						</fmt:message>
                    </span>
                    
                    <span id="errEqPassword" class="alert alert-danger" style="display:none">
                    	<fmt:message key="message.newPsw.equal" bundle="${lang}"/>
                    </span>
                    
<br/><br/>
                    <label class="col-sm-2"><fmt:message key="label.user.confirmPass" bundle="${lang}"/></label>
                    <span class="col-sm-5"><input class="form-control" id="passConfirm" type="password" value="" onkeypress='$("#error").hide()' /></span>
                    <span id="error" class="alert alert-danger" style="display:none"><fmt:message key="PasswordMatches.user" bundle="${lang}"/></span>
                   
                <br/><br/>
                <button class="btn btn-primary" type="submit" onclick="savePass()" ><fmt:message key="message.changePassword" bundle="${lang}"/>
                </button>
            </div>
            
        </div>
    </div>
    
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script>

var serverContext = '<c:out value="${pageContext.request.contextPath}"/>';
var pswMinLen = <c:out value="${pswMinLen}"/>;
var regExp = new RegExp('${pswRegExp}');

function savePass() {
	
	var oldpass = $("#oldpass").val();
	var pass = $("#pass").val();
    var valid = pass == $("#passConfirm").val();
    
    if(!valid) {
      $("#error").show();
      return;
    }
    
    if (pass == oldpass) {
        $("#errEqPassword").show();
        return;
    } 

//      if (pass.length < pswMinLen) {
//      	 $("#errornNewPassword").show();
//           return;
//      }
    
    if (!pass.match(regExp)) {
    	 $("#errornFormalNewPassword").show().html('<fmt:message key="message.password.regExp" bundle="${lang}"/>');
    	return;
    }
    
    alert("OK match");
    
	$.get(serverContext + "/changePassword2"
    	, {password: pass, oldpassword: $("#oldpass").val()}
    	,function(data) {
    		if (data.result == 'ok') { 
    			window.location.href = serverContext + "/agenda" + "?message="+data.msg;
    		} else {
    			$("#errorMsg").show().html(data.msg);
    		}
    	}
    	
    	)
     	.fail(function(data) {
     		$("#errorMsg").show().html(data);}
     	);

}
</script>  
</body>

</html>