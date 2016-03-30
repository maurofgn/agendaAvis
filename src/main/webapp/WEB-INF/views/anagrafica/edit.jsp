<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
 
<%@ page import="it.mesis.tmm.enu.Sesso" %>
 
<!DOCTYPE html> 
 
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" /> 
<fmt:setLocale value="${language}" /> 
<fmt:setBundle basename="messages" var="lang" /> 
 
<html> 
 
  <head> 
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="<c:url value='/static/images/favicon.ico'/>" rel="shortcut icon" type="image/x-icon" /> 
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"> 
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> 
	<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script> 
<!-- 	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>  -->
	<link rel="stylesheet" type="text/css" href="/css/style.css" /> 
	
	
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>	
	
	
<%-- 	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link> --%>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	
	<title><fmt:message key="anagrafica.Edit.Title" bundle="${lang}"/></title> 
	<!--[if IE]> 
		<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> 
	<![endif]--> 
 
	<style> 
		.error {color: #ff0000;} 
	</style> 
 
	<script type="text/javascript">
	 $(function() {
	  $( "#anagrafica_datanascita" ).datepicker({ dateFormat: 'dd/mm/yy', minDate: 'today-100Y', maxDate: 'today+1Y' });

	  $( "#comuniCittaProv" ).autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	          url: "<%=request.getContextPath()%>/JSON/city",
	          dataType: "json",
	          data: {term: request.term},
	          success: function( data ) {
	        	  response( data );
	        	  $('#idluogonascita').val("");
	        	}
	        });
	      },
	      minLength: 3,
	      select: function( event, ui ) {	        
	        $('#idluogonascita').val(ui.item.id);
	      }
	    });
	  
	 });
	</script>
 
  </head> 
  <body> 
  
 	<div class="generic-container">
 	
	<h2><fmt:message key="anagrafica.h2" bundle="${lang}" /></h2> 
	
	<h2><fmt:message key="anagrafica.label.tipizza" bundle="${lang}" /> ${tipizzazione.toString()}</h2>
	
	<form:form method="POST" modelAttribute="anagrafica" class="form-horizontal">
	
	  <form:hidden path="id" id="anagrafica_id"/>
	  
		<div class="row">
			<div class="form-group col-md-5">
				<label class="col-md-3 control-lable" for="anagrafica_cognome"><fmt:message key="anagrafica.label.cognome" bundle="${lang}"/></label>
				<div class="col-md-7">
					<form:input path="cognome" class="form-control input-sm"/>
					<div class="has-error">
						<form:errors path="cognome" class="help-inline"/>
					</div>					
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group col-md-5">
				<label class="col-md-3 control-lable" for="anagrafica_nome"><fmt:message key="anagrafica.label.nome" bundle="${lang}"/></label>
				<div class="col-md-7">
					<form:input path="nome" class="form-control input-sm"/>
					<div class="has-error">
						<form:errors path="nome" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group col-md-5">
				<label class="col-md-3 control-lable" for="luogoNascita"><fmt:message key="label.luogoNascita" bundle="${lang}"/></label>
				<div class="col-md-7">
				
					<form:hidden path="idluogonascita"/>
					<form:input path="comuniCittaProv" class="form-control input-sm"/>
					
					<div class="has-error">
						<form:errors path="comunicf" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>
		
<!-- 		<div class="row"> -->
<!-- 			<div class="form-group col-md-5"> -->
<%-- 				<label class="col-md-3 control-lable" for="anagrafica_sesso"><fmt:message key="anagrafica.label.sesso" bundle="${lang}" /></label> --%>
<!-- 				<div class="col-md-7"> -->
<!-- 					<select name="sesso" id="anagrafica_sesso" class="form-control input-sm"> -->
<%-- 					   <c:forEach var="item" items="${Sex.values()}"> --%>
<%-- 					   	<option value="${item}" ${item == anagrafica.sesso ? 'selected="selected"' : ''}>${item.toString()}</option> --%>
<%-- 					   </c:forEach> --%>
<!-- 					</select> -->
<!-- 					<div class="has-error"> -->
<%-- 						<form:errors path="sesso" class="help-inline"/> --%>
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
		
		<div class="row">
			<div class="form-group col-md-5">
				<label class="col-md-3 control-lable" for="anagrafica_sesso"><fmt:message key="anagrafica.label.sesso" bundle="${lang}" /></label>
				<div class="col-md-7">
				
				
					<select name="sesso" id="sesso" class="form-control input-sm">
<!-- 					   <option value="0">--</option> -->
					   <c:forEach var="item" items="${Sesso.values()}">
						<option value="${item.index}" ${item.index == anagrafica.sesso ? 'selected="selected"' : ''}>${item.toString()}</option>
					   </c:forEach>
					</select>
					<div class="has-error">
						<form:errors path="sesso" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>
 
		
		<div class="row">
			<div class="form-group col-md-5">
				<label class="col-md-3 control-lable" for="anagrafica_datanascita"><fmt:message key="anagrafica.label.datanascita" bundle="${lang}"/></label>
				<div class="col-md-7">
					<form:input type="text" path="datanascita" id="anagrafica_datanascita" class="form-control input-sm"/>
					<div class="has-error">
						<form:errors path="datanascita" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>	
		
		<div class="row">
			<div class="form-group col-md-5">
				<label class="col-md-3 control-lable" for="anagrafica_codfiscale"><fmt:message key="anagrafica.label.codfiscale" bundle="${lang}"/></label>
				<div class="col-md-7">
					<form:input type="text" path="codfiscale" id="anagrafica_codfiscale" class="form-control input-sm"/>
					<div class="has-error">
						<form:errors path="codfiscale" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="form-actions floatRight">
				<input type="submit" value="<fmt:message key="submit" bundle="${lang}" />" class="btn btn-primary btn-default btn-sm">
				 
				 
<%-- 				<input type="button" onclick="location.href='<c:url value='/delete-${anagrafica.id}-anagrafica' />'" value="<fmt:message key="delete" bundle="${lang}"/>" class="btn  btn-danger btn-sm"/> --%>
				
<%-- 				<input type="button" onclick="location.href='<c:url value='/listPara'/>'" value="<fmt:message key="search" bundle="${lang}"/>" class="btn btn-primary btn-sm"/> --%>

				<a href="<c:url value='/delete-${anagrafica.id}-anagrafica'/>" class="btn btn-danger btn-sm">
    				<span class="glyphicon glyphicon-erase"></span> <fmt:message key="delete" bundle="${lang}"/>
    			</a>

				<a href="<c:url value='/listPara'/>" class="btn btn-primary btn-sm">
    				<span class="glyphicon glyphicon-search"></span> <fmt:message key="search" bundle="${lang}"/>
    			</a>
				
			</div>
		</div>

	 
	</form:form>
 	</div>
  </body> 
</html> 
