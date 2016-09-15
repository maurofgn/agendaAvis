<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages" var="lang" />

<c:url value="/auditRecs" var="auditRecs"/>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"> 
<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script> 

<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.0/css/jquery.dataTables.css">
<script type="text/javascript" src="//cdn.datatables.net/1.10.12/js/jquery.dataTables.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII"/>
<title><fmt:message key="audit.title" bundle="${lang}" /></title>

<style type="text/css">

#tbodyLogs td {
	padding-top: 0;
	padding-bottom: 0;
};

</style>

<script type='text/javascript'>

var tableLogs;

function getParams() {
	parameters = new Object();
	parameters.dateFrom = $('#dateFrom').val();
	parameters.dateTo = $('#dateTo').val();
	parameters.user = $('#user').val();
	parameters.state = $('#state').val();
	return $.param(parameters);
}

function isValidDate(date)
{
    var matches = /^(\d{2})[-\/](\d{2})[-\/](\d{4})$/.exec(date);
    if (matches == null) return false;
    var d = matches[1];
    var m = matches[2] - 1;
    var y = matches[3];
    var composedDate = new Date(y, m, d);
    return composedDate.getDate() == d &&
            composedDate.getMonth() == m &&
            composedDate.getFullYear() == y;
}

function parseDate(input) {
	  var parts = input.split('/');
	  // new Date(year, month [, day [, hours[, minutes[, seconds[, ms]]]]])
	  return new Date(parts[2], parts[1]-1, parts[0]); // Note: months are 0-based
}	

//Plug-in to fetch page data 
jQuery.fn.dataTableExt.oApi.fnPagingInfo = function ( oSettings )
{
	return {
		"iStart":         oSettings._iDisplayStart,
		"iEnd":           oSettings.fnDisplayEnd(),
		"iLength":        oSettings._iDisplayLength,
		"iTotal":         oSettings.fnRecordsTotal(),
		"iFilteredTotal": oSettings.fnRecordsDisplay(),
		"iPage":          oSettings._iDisplayLength === -1 ? 0 : Math.ceil( oSettings._iDisplayStart / oSettings._iDisplayLength ),
		"iTotalPages":    oSettings._iDisplayLength === -1 ? 0 : Math.ceil( oSettings.fnRecordsDisplay() / oSettings._iDisplayLength )
	};
};

$(document).ready(function() {
	
	$( "#dateFrom" ).datepicker({ dateFormat: 'dd/mm/yy', minDate: '-90y', maxDate: 'today'})
 		.datepicker("setDate", new Date())
// 		.on('changeDate', function(ev) {
// 	        $(this).valid();  // triggers the validation test
// 	        // '$(this)' refers to '$("#dateFrom")'
// 	    	})
	    	;
	$( "#dateTo" ).datepicker({ dateFormat: 'dd/mm/yy', minDate: '-90y', maxDate: 'today'})
 		.datepicker("setDate", new Date())
		;
	
	tableLogs = $("#logs").DataTable( {
 		"lengthMenu": [[10, 25, 50, 100], [10, 25, 50, 100]],
        "processing": true,
        "serverSide": true,
        "bFilter": false,		//senza input x search
        //bStateSave variable you can use to save state on client cookies: set value "true" 
        "bStateSave": false,
        //Default: Page display length
        "iDisplayLength": 10,
        //We will use below variable to track page number on server side(For more information visit: http://legacy.datatables.net/usage/options#iDisplayStart)
        "iDisplayStart": 0,
        "scrollY":        "240px",
        "scrollCollapse": true,
        "fnDrawCallback": function () {
            //Get page numer on client. Please note: number start from 0 So
            //for the first page you will see 0 second page 1 third page 2...
            //Un-comment below alert to see page number
        	//alert("Current page number: "+this.fnPagingInfo().iPage);
        },
        "language": {
            "lengthMenu": "Mostra _MENU_ righe per pagina",
            "zeroRecords": "Nessun valore da visualizzare",
            "info": "Pagina _PAGE_ di _PAGES_",
            "infoEmpty": "Non ci sono valori disponibili",
            "emptyTable": "Non ci sono valori disponibili",
            "infoFiltered": "(filtrati da _MAX_ records totali)",
            "search":         "Cerca:",
            "thousands":      ".",
            "decimal":        "",
            "loadingRecords": "Loading...",
            "processing":     "Processing...",
            "paginate": {
                "first":      "Primo",
                "last":       "Ultimo",
                "next":       "Successivo",
                "previous":   "Precedente"
            }
        },
        "ajax": 
        	{
            url : "auditRecsPages",
            type: "GET", // This is the default value, could also be POST
            "beforeSend": function (msg) {
                console.log("2-sending...");
            },
            data: function(d) {
                    d.dateFrom = $('#dateFrom').val();
                	d.dateTo = $('#dateTo').val();
                	d.user = $('#user').val();
                	d.state = $('#state').val();
                }
        	},
        "columns": [
				{ "mData": "created", "render": function (data) {
			          var date = new Date(data);
			          var month = date.getMonth() + 1;
			          month = month.length > 1 ? month : "0" + month;
			          var day = date.getDate();
			          day = day > 9 ? day : "0" + day;
					  return day + "/" + month + "/" + date.getFullYear();
					}
		    	 },
			{ "mData": "ssoId" },
			{ "mData": "state" }
        ],
        "order": [[ 0, 'desc' ], [ 1, 'asc' ]]
    } );
	
	$('#dateFrom, #dateTo, #user, #state').on('change keyup paste', function () {
		if (isValidDate($('#dateFrom').val()) && isValidDate($('#dateTo').val()))
 			tableLogs.ajax.reload();
	});
	
});
	
</script>

</head>
<body>

<div class="container">
  <h2><fmt:message key="audit.title" bundle="${lang}" /></h2>
  
 	<sec:authorize access="hasRole('ADMIN') or hasRole('AVIS')">

		<div>
		    <form:form action="" method="GET" style="display: flex; margin: auto; position: relative;">

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
			</form:form>
			
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
		
	</sec:authorize>
	
</div>
</body>
</html>