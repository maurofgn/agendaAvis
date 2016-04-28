<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%-- <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages" var="lang" />

<c:url value="/auditRecs" var="auditRecs"/>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"/>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"> 
<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script> 
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII"/>
<title><fmt:message key="audit.title" bundle="${lang}" /></title>

<style type="text/css">

#tbodyLogs td {
	padding-top: 0;
	padding-bottom: 0;
};

</style>

<script type='text/javascript'>

$(function() {
	$( "#dateFrom" ).datepicker({ dateFormat: 'dd/mm/yy', minDate: 'today-190Y', maxDate: 'today'}).datepicker("setDate", new Date());
	$( "#dateTo" ).datepicker({ dateFormat: 'dd/mm/yy', minDate: 'today-190Y', maxDate: 'today'}).datepicker("setDate", new Date());
});

$(document).ready(function() {
	getLogs();
	});
	
$(document).ajaxStart(function() {
    $("title").html("LOADING ...");
});
	
function getLogs() {
    var dateFrom = $("#dateFrom").val();
    var dateTo = $("#dateTo").val();
    var user = $("#user").val();
    var state = $("#state").val();
    
	$.ajax({
		type : 'get',
		url: '${auditRecs}',
		data : {dateFrom: dateFrom, dateTo: dateTo, user:user, state:state},
//         context: document.body,
        success: function(response) {
         	populateTable(response)
        },
		error : function(data) {
			console.log("FAILUR: ", data);
	    }
	});
}

function populateTable(data) {
	
	var trHTML = '';
	
    $.each(data.rows, function (i, item) {
		trHTML += '<tr><td style="width: 160px;">' + item.createdHourString + '</td><td style="width: 150px;">' + item.ssoId + '</td><td style="width: 780px;">' + item.state + '</td></tr>';
    });
    
    $('#tbodyLogs').html(trHTML);
}

	
</script>

</head>
<body>

<div class="container">
  <h2><fmt:message key="audit.title" bundle="${lang}" /></h2>
  
 	<sec:authorize access="hasRole('ADMIN') or hasRole('AVIS')">

		<div id="auditPara">
		
		    <form  style=" display: flex; margin: auto; position: relative; ">

				<div class="row">
					<div class="form-group col-md-12" style=" display: flex; margin: auto; position: relative; ">
						<label class="control-lable" for="dateFrom" style="padding: 5px;"><fmt:message key="audit.label.dateFrom" bundle="${lang}" /></label>
						<input type="text" name="dateFrom" id="dateFrom" class="form-control input-sm" style="width: 100px;"/>
					</div>
				</div>
				
				<div class="row">
					<div class="form-group col-md-12" style=" display: flex; margin: auto; position: relative; ">
						<label class="control-lable" for="dateTo" style="padding: 5px;"><fmt:message key="audit.label.dateTo" bundle="${lang}"/></label>
						<input type="text" name="dateTo" id="dateTo" class="form-control input-sm" style="width: 100px;" />
					</div>
				</div>


				<div class="row">
					<div class="form-group col-md-12" style=" display: flex; margin: auto; position: relative; ">
						<label class="control-lable" for="user" style="padding: 5px;"><fmt:message key="audit.label.utente" bundle="${lang}"/></label>
						<input type="text" name="user" id="user" class="form-control input-sm" style="width: 150px;" />
					</div>
				</div>
				
				
				<div class="row">
					<div class="form-group col-md-12" style=" display: flex; margin: auto; position: relative; ">
						<label class="control-lable" for="state" style="padding: 5px;"><fmt:message key="audit.label.state" bundle="${lang}"/></label>
						<input type="text" name="state" id="state" class="form-control input-sm" style="width: 150px;" />
					</div>
				</div>
				
				<div class="row">
					<div class="form-group col-md-12" >
						<a href="#" class="btn btn-primary btn-default btn-sm" onclick="getLogs()" style=" width: 100%; margin-left:15px">
							<fmt:message key="search" bundle="${lang}"/>
			  			</a>
					</div>
				</div>
				
			</form>
		</div>
		
	</sec:authorize>
	
   <div id="scrollPanel" style="overflow-y:auto; height: 400px; border: solid 1px;">
	   <table id='logs' class="table table-hover" style="width:100%">
	    <thead>
	      <tr>
	        <th><fmt:message key="audit.label.data" bundle="${lang}" /></th>
	        <th><fmt:message key="audit.label.utente" bundle="${lang}" /></th>
	        <th><fmt:message key="audit.label.state" bundle="${lang}" /></th>
	      </tr>
	    </thead>
	    <tbody id = 'tbodyLogs' >
	    </tbody>
	  </table>
  </div>
	  
</div>

</body>
</html>