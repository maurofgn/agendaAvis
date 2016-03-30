<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages" var="lang" />

<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

  <link href="static/images/favicon.ico" rel="shortcut icon" type="image/x-icon" />
  <link href="http://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
  
  <title><fmt:message key="tmm" bundle="${lang}"/></title> 
  <!--[if IE]> 
	<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> 
  <![endif]--> 
  
  
  <style>
  .bg-1 {
      background-color: #1abc9c; /* Green */
      color: #ffffff;
  }
  .bg-2 {
      background-color: #474e5d; /* Dark Blue */
      color: #ffffff;
  }
  .bg-3 {
      background-color: #ffffff; /* White */
      color: #555555;
  }
  .bg-4 {
      background-color: #2f2f2f;
      color: #ffffff;
  }
  
  .container-fluid {
    padding-top: 0px;
    padding-bottom: 0px;
  }
  
  .navbar {
    padding-top: 15px;
    padding-bottom: 15px;
    border: 0;
    border-radius: 0;
    margin-bottom: 50;
    font-size: 12px;
    letter-spacing: 2px;
  }
  
  .navbar-nav li a:hover {
      color: #1abc9c !important;
  }
  
      /* Set black background color, white text and some padding */
  footer {
      background-color: #555;
      color: white;
      padding: 5px;
      height: 60px;
    }
	  
  body {
      font: 20px "Montserrat", sans-serif;
      line-height: 1.8;
      color: #f5f6f7;
  }
  
  p {font-size: 12px;}
  
  </style>
  
</head>
<body>

<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="boot"><img src="static/images/logo.png" alt="Logo"></a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
       <li><a href="<c:url value='listPara'/>">Persone</a></li>
       <li><a href="<c:url value='userList'/>">utenti</a></li>
       <li><a href="#internal_1">link 3</a></li>
      </ul>
	  <ul class="nav navbar-nav navbar-right">
		 <sec:authorize url="/">
		   <li>
		   	<a href="<c:url value='/logout'/>">
		   		<span class="glyphicon glyphicon-log-out"></span> Logout <sec:authentication property="principal.username" />
		   	</a>
		   </li>
		 </sec:authorize>
      </ul>
    </div>
  </div>
</nav>


<div class="container bg-1">

  <h2>Dynamic Pills</h2>
  <ul class="nav nav-pills">
    <li class="active"><a data-toggle="pill" href="#home">Home</a></li>
    <li><a data-toggle="pill" href="#menu1">Menu 1</a></li>
    <li><a data-toggle="pill" href="#menu2">Menu 2</a></li>
    <li><a data-toggle="pill" href="#menu3">Menu 3</a></li>
  </ul>
  
  <div class="tab-content">
    <div id="home" class="tab-pane fade in active">
      <h3>HOME</h3>
      
	 <form role="form">
	  <div class="form-group">
		<label for="email">Email address:</label>
		<input type="email" class="form-control" id="email">
	  </div>
	  <div class="form-group">
		<label for="pwd">Password:</label>
		<input type="password" class="form-control" id="pwd">
	  </div>
	  <div class="checkbox">
		<label><input type="checkbox"> Remember me</label>
	  </div>
	  <button type="submit" class="btn btn-default">Submit</button>
	</form>	      
      
    </div>
    <div id="menu1" class="tab-pane fade">
      <h3>Menu 1</h3>
      <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
    </div>
    <div id="menu2" class="tab-pane fade">
      <h3>Menu 2</h3>
      <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam.</p>
    </div>
    <div id="menu3" class="tab-pane fade">
      <h3>Menu 3</h3>
      <p>Eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.</p>
    </div>
  </div>
</div>


<footer class="container-fluid footer text-center">
  <p>&copy; <script>document.write(new Date().getFullYear())</script> - <fmt:message key="footer.info.mail" bundle="${lang}" />: <a href="mailto:mesis@mesis.com">MeSIS</a></p>
</footer>

</body>
</html>
