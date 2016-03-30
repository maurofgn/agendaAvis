<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 

<%@ page import="it.mesis.tmm.enu.Sesso" %>

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
	<link rel="stylesheet" type="text/css" href="/css/style.css" /> 
	
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	
	<link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon" /> 
	
	<title><fmt:message key="anagrafica.Edit.Title" bundle="${lang}"/></title> 
	<!--[if IE]> 
		<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> 
	<![endif]--> 
 
	<style> 
		.error {color: #ff0000;} 
	    .ui-autocomplete-loading {
	    background: white url("static/images/ui-anim_basic_16x16.gif") right center no-repeat;
	  }
	  #luogonascita { width: 25em; }
	</style> 
 
  	<title><fmt:message key="ListAnagrafica.Para.Title" bundle="${lang}"/></title>

<script type="text/javascript">
$(function() {
	
//     function log( message ) {
//          $( "<div>" ).text( message ).prependTo( "#log" );
//          $( "#log" ).scrollTop( 0 );
//     }
	
    $( "#datanascita" ).datepicker({ dateFormat: 'dd/mm/yy', minDate: 'today-90Y', maxDate: 'today'});
	$( "#luogonascita" ).autocomplete({
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

    $("#frmListAnagraficaPara").submit(function (e) { allcheck(e); });
    
    /* validazione form */
    function allcheck(e) {
		
    	var errors = false;
    	
/*         var message = document.getElementById(document.getElementById('codFiscale').id + "-validation-message");
		if (document.getElementById('codFiscale').value.length == 0) {
            message.innerHTML = "inserire almeno un carattere";
            message.style.display = "";
            errors = true;
         } else {
        	message.innerHTML = "";
            message.style.display = "none";
         }
		
        var message = document.getElementById(document.getElementById('codFiscale').id + "-validation-message");
		if (document.getElementById('codFiscale').value.length == 0) {
            message.innerHTML = "inserire almeno un carattere";
            message.style.display = "";
            errors = true;
         } else {
        	message.innerHTML = "";
            message.style.display = "none";
         } */
		
		if (errors)
			e.preventDefault();
    }
	
  });
  
</script>

    </head>

    <body>
			
    <div class="generic-container">
    	<h2><fmt:message key="anagrafica.search.para" bundle="${lang}" /></h2>
        <form:form method="GET"  action='list' id="frmListAnagraficaPara" class="form-horizontal">
	        
		<div class="row">
			<div class="form-group col-md-12">
			
				<label class="col-md-3 control-lable" for="cognome"><fmt:message key="anagrafica.label.cognome" bundle="${lang}" /></label>
				<div class="col-md-7">
					<input type="text" name="cognome" id="anagrafica_cognome" class="form-control input-sm"/>
					<div class="has-error">
						<form:errors path="cognome" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>	        
	        
		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="nome"><fmt:message key="anagrafica.label.nome" bundle="${lang}" /></label>
				<div class="col-md-7">
					<input type="text" name="nome" id="nome" value="<c:out value="${anagrafica.nome}"/>" class="form-control input-sm">
					<div class="has-error">
						<form:errors path="nome" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="nome"><fmt:message key="anagrafica.label.codfiscale" bundle="${lang}" /></label>
				<div class="col-md-7">
					<input type="text" name="codfiscale" id="codfiscale" value="<c:out value="${anagrafica.codfiscale}"/>" class="form-control input-sm">
					<div class="has-error">
						<form:errors path="codfiscale" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>

 		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="sesso"><fmt:message key="anagrafica.label.sesso" bundle="${lang}" /></label>
				<div class="col-md-7">
					<select name="sesso" id="sesso" class="form-control input-sm">
					   <option value="0">--</option>
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
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="anagrafica_datanascita"><fmt:message key="anagrafica.label.datanascita" bundle="${lang}"/></label>
				<div class="col-md-7">
					<input type="text" name="datanascita" id="anagrafica_datanascita" class="form-control input-sm"/>
					<div class="has-error">
						<form:errors path="datanascita" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>			
		
		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="luogonascita"><fmt:message key="anagrafica.label.luogonascita" bundle="${lang}" /></label>
				<div class="col-md-7">
					<input type="hidden" name="idluogonascita" id="idluogonascita" value="<c:out value="${anagrafica.idluogonascita}"/>"/>
					<input type="text"  id="luogonascita"  class="form-control input-sm">
					<div class="has-error">
						<form:errors path="comunicf" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-actions floatRight">
				<input type="submit" value="<fmt:message key="submit" bundle="${lang}" />" class="btn btn-primary btn-sm"> 
			</div>
		</div>		
<%-- 		</form> --%>
		</form:form>
      </div>
    </body>
</html>