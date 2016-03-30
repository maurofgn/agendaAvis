<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
<title>Login</title>

  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
  
  <link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
  
</head>

<body>
	<div id="mainWrapper">
		<div class="generic-container">
			<div class="login-container">
				<div class="login-card">
					<div class="login-form">
						<form action="login" method="post" class="form-horizontal">

							<c:if test="${param.error != null}">
								<div class="alert alert-danger">
									<p>Account non valido</p>
								</div>
							</c:if>

							<c:if test="${param.logout != null}">
								<div class="alert alert-success">
									<p>Uscita corretta.</p>
								</div>
							</c:if>

							<div class="input-group input-sm col-sm-4">
								<label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
								<input type="text" class="form-control" id="username" name="ssoId" placeholder="Enter Username" required>
							</div>

							<div class="input-group input-sm col-sm-4">
								<label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label>
								<input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" required>
							</div>
							
						    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

							<div class="form-actions input-sm col-sm-4">
								<input type="submit" class="btn btn-block btn-primary btn-default" value="Log in">
							</div>

						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>