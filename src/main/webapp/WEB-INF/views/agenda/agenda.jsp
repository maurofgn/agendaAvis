<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages" var="lang" />

<c:url value="/freeHours" var="freeHours"/>

<html>
<head>
	<title><fmt:message key="agenda.para.title" bundle="${lang}"/></title>

	<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"> 
	<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script> 

<style> 

#hours td {
	padding-top: 0;
	padding-bottom: 0;
	};

.text-center {
	text-align:center;
	}
.flex-container {
    display: -webkit-flex; 
    display: flex; 
    justify-content: center;
	}

.flex-itemForm {
	display: block; 
    background-color: #b1caf6;	 /* cornflowerblue; */ 
    margin: 5px;
    }
    
.flex-itemTable {
	display: block; 
    background-color: #b1caf6;	 /* cornflowerblue; */ 
    margin: 5px;
    }

.Libero {
	-moz-box-shadow: inset 0px 1px 0px 0px #3dc21b;
	-webkit-box-shadow: inset 0px 1px 0px 0px #3dc21b;
	background-color: #44c767;
	border: 1px solid #18ab29;
	cursor: pointer;
	color: #ffffff;
	padding: 8px 10px;
	text-decoration: none;
    }
.Libero:hover {
	background-color: #5cbf2a;
    }
.Libero:active {
    position: relative;
    }
        
.LiberoNonPrenotabile {
	-moz-box-shadow: inset 0px 1px 0px 0px #3dc21b;
	-webkit-box-shadow: inset 0px 1px 0px 0px #3dc21b;
	background-color: #468858;
	border: 1px solid #18ab29;
	color: #ffffff;
	padding: 8px 10px;
	text-decoration: none;
    }

.Occupato {
	-moz-box-shadow: inset 0px 1px 0px 0px #3dc21b;
	-webkit-box-shadow: inset 0px 1px 0px 0px #3dc21b;
	background-color: #E02A2A;            
	border: 1px solid #B00000;
	color: #ffffff;
	padding: 8px 10px;
	text-decoration: none;
    }
.Occupato:active {
	position: relative;
    }
        
.Indisponibile {
	-moz-box-shadow: inset 0px 1px 0px 0px #3dc21b;
	-webkit-box-shadow: inset 0px 1px 0px 0px #3dc21b;
	background-color: #c2c2c2;				/* grigio chiaro */
	border: 1px solid #808080;
	color: #ffffff;
	padding: 8px 10px; 
	text-decoration: none;
    }
.Indisponibile:active {
	position: relative;
    }
        
.Mia_Preno {
	-moz-box-shadow: inset 0px 1px 0px 0px #3dc21b;
	-webkit-box-shadow: inset 0px 1px 0px 0px #3dc21b;
	background-color: #13e3f2;
	border: 1px solid #808080;
	cursor: pointer;
	color: #ffffff;
	padding: 8px 10px;
	text-decoration: none;
    }
.Mia_Preno:hover {
	background-color: #1374f2;
	}
.Mia_Preno:active {
    position: relative;
    }
        
</style>


<script type="text/javascript">

$(function() {
	$( "#dateFrom" ).datepicker({ dateFormat: 'dd/mm/yy', minDate: 'today-90Y', maxDate: 'today+90'}).datepicker("setDate", new Date());
	$( "#dateTo" ).datepicker({ dateFormat: 'dd/mm/yy', minDate: 'today-90Y', maxDate: 'today+90'}).datepicker("setDate", new Date());
});

function disdetta() {
	if (confirm('Vuoi disdire la prenotazione ?')) {
		location.href = "disdetta";
	}
}

function prenota_old(dayNr) {
	location.href = "listFreeHours?dayNr=" + dayNr;
}

function prenota(dayNr) {
	var yy=${monthlyBookings.yearMonth.year};
	var mm=${monthlyBookings.yearMonth.month};
	var pp=${monthlyBookings.tipoDonaPuntoPrel.puntoprelId};
	var td=${monthlyBookings.tipoDonaPuntoPrel.tipoDonaId};
	
	$.ajax({
		type : 'get',
		url: '${freeHours}',
		data : {puntoprelId:pp, tipoDonaId:td, year:yy, month:mm, day:dayNr},
//         context: document.body,
        success: function(response) {
         	populateTable(response)
        },
		error : function(data) {
			console.log("FAILUR: ", data);
	    }
	});
	
	document.getElementById('scrollPanelHours').style.display = 'block';
}

function populateTable(data) {
	
	var trHTML = '';
	
    $.each(data, function (i, item) {
		trHTML += '<tr><td style="width: 80px;">' + item.linkPreno + '</td><td style="width: 150px;">' + item.state + '</td></tr>';
    });
    
    $('#hours').html(trHTML);
}

</script>

</head>
<body>


<div class="container">
<!--  <div class="content"> -->
 
 <div class="flex-container">
 
	<div class="flex-itemForm">
	 	<h2><fmt:message key="agenda.title" bundle="${lang}" /></h2>
	
		<div>  	
		    <form:form method="GET" action='agenda' id="frmAgenda" class="form-horizontal">
		    
				<div class="row">
					<div class="form-group col-md-12">
					
						<div class="col-md-12">
						
							<select name='tipoDonazPuntiPrel' style="width: 180px;">
							    <c:forEach items="${listTipoDonazPuntiPrel}" var="tipoDonazPuntiPrel">
							    	<option value="${tipoDonazPuntiPrel.valToString}"
							    		<c:if test="${tipoDonazPuntiPrel == tipoDonazPuntiPrelSelected}"> selected</c:if>
							    		>${tipoDonazPuntiPrel}</option>
							    </c:forEach>
							</select>
							
							<div class="has-error">
								<form:errors path="tipoDona" class="help-inline"/>
							</div>
							
						</div>
					</div>
				</div>
			        
				<div class="row">
					<div class="form-group col-md-12">
						<div class="col-md-12">
						
							<select name='yearMonth'  style="width: 180px;">
							    <c:forEach items="${listYearMonth}" var="yearMonth">
							        <c:if test="${yearMonth != yearMonthSelected}">
							            <option value="${yearMonth.valToString}">${yearMonth}</option>
							        </c:if>
							        <c:if test="${yearMonth == yearMonthSelected}">
							            <option value="${yearMonth.valToString}" selected>${yearMonth}</option>
							        </c:if>
							    </c:forEach>
							</select>							
						
							<div class="has-error">
								<form:errors path="yearMonth" class="help-inline"/>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div style="margin: auto; width: 40%;">
						<input type="submit" value="<fmt:message key="submit" bundle="${lang}"/>" style="width: 100%;" class="btn btn-primary btn-sm"> 
					</div>
				</div>

			</form:form>
			
		</div>
		
		<div style="margin: auto; width: 80%; position: relative; top: 110px;">
			<sec:authorize access="hasRole('OPERA') or hasRole('ADMIN') or hasRole('AVIS')">
				<div style="position: relative; margin: 5px;">
					<a href="#" class="btn btn-primary btn-sm" style="width: 100%;" onclick="{document.getElementById('reportAgenda').style.display = 'block';}">
		    			<span class="glyphicon glyphicon-print"></span>  <fmt:message key="agenda.report" bundle="${lang}"/>
		  			</a>
				</div>
			</sec:authorize>
			
			<sec:authorize access="hasRole('ADMIN') or hasRole('AVIS')">
				<div style="position: relative; margin: 5px;">
					<a href="<%=request.getContextPath()%>/audit" style="width: 100%;" class="btn btn-primary btn-sm" >
		    			<span class="glyphicon glyphicon-pencil"></span>  <fmt:message key="agenda.audit" bundle="${lang}"/>
		  			</a>
				</div>
			</sec:authorize>
		</div>
		
    </div>
    
	<div class="flex-itemTable" <c:if test="${empty monthlyBookings}">class="hide" </c:if>>
	    
		<table id="week" class="table table-bordered">
		    <thead>
		      <tr>
		        <th class="text-center">Lun</th>
		        <th class="text-center">Mar</th>
		        <th class="text-center">Mer</th>
		        <th class="text-center">Gio</th>
		        <th class="text-center">Ven</th>
		        <th class="text-center">Sab</th>
		        <th class="text-center">Dom</th>
		      </tr>
		    </thead>
		    <tbody>
		    
		        <c:forEach begin="0" end="5" varStatus="weekNr">
		        
			        <tr >
				    	<c:forEach begin="0" end="6" varStatus="dayNr">
				        	<c:set var="booking" value="${monthlyBookings.getBooking(weekNr.index, dayNr.index)}"/>
				        	
							<c:choose>
							    <c:when test="${empty booking}">
							        <c:set var="state" value="indisponibile"/>
							    </c:when>
							    <c:otherwise>
									<c:set var="state" value="${booking.state.value}"/>
							    </c:otherwise>
							</c:choose>					        	
				        	
				        	<td class='${state}' style="width: 50px; height: 50px; line-height: 14px;">
				        	
								<c:if test="${not empty booking && booking.valid}">
								
										<table class='borderless' style="width: 100%" >
											<tr>
												<td ${booking.functionJS} style="font-size: 10px;">
													${booking.day.day}
												</td>
											</tr>
											<tr>
												<td style="text-align: center; width: 100%; font-size: 14px;  " ${booking.functionJS}>
													${booking.freeOrMyHour}
												</td>
											</tr>
										</table>
								
								</c:if>
								
				        	</td>
						</c:forEach>
			      	</tr>
				</c:forEach>
				
		    </tbody>
		</table>
		
		<div class="row">
			<div class="form-group col-md-12">
			
				<c:if test="${empty monthlyBookings.agendaKey}">
			
					<div class="col-md-5" style="text-align:right;" >
						<a href="<c:url value='agenda?tipoDonazPuntiPrel=${tipoDonazPuntiPrelSelected.valToString}&yearMonth=${yearMonthPrev.valToString}' />" class="btn btn-primary" <c:if test="${empty yearMonthPrev}">disabled</c:if>>
							<span class="glyphicon glyphicon-step-backward"></span>
						</a>
					</div>
					
					<div class="col-md-2" style="text-align:center;">
					</div>			
					
					<div class="col-md-5" style="text-align:left;">
						<a href="<c:url value='agenda?tipoDonazPuntiPrel=${tipoDonazPuntiPrelSelected.valToString}&yearMonth=${yearMonthNext.valToString}' />" class="btn btn-primary" <c:if test="${empty yearMonthNext}">disabled</c:if>>
							<span class="glyphicon glyphicon-step-forward"></span>
						</a>
					</div>
				</c:if>	
				
				<c:if test="${not empty monthlyBookings.agendaKey}">
			
					<div class="col-md-3" style="text-align:right;" >
						<a href="<c:url value='agenda?tipoDonazPuntiPrel=${tipoDonazPuntiPrelSelected.valToString}&yearMonth=${yearMonthPrev.valToString}' />" class="btn btn-primary" <c:if test="${empty yearMonthPrev}">disabled</c:if>>
							<span class="glyphicon glyphicon-step-backward"></span>
						</a>
					</div>
					
					<div class="col-md-6" style="text-align:center;">
						<a href="<c:url value='agenda' />" class="btn btn-primary">
							<fmt:message key="agenda.para.current" bundle="${lang}"/>
						</a>
					</div>			
					
					<div class="col-md-3" style="text-align:left;">
						<a href="<c:url value='agenda?tipoDonazPuntiPrel=${tipoDonazPuntiPrelSelected.valToString}&yearMonth=${yearMonthNext.valToString}' />" class="btn btn-primary" <c:if test="${empty yearMonthNext}">disabled</c:if>>
							<span class="glyphicon glyphicon-step-forward"></span>
						</a>
					</div>
				</c:if>					
				
				
			</div>
		</div>
		
	</div>
	
	</div>	 <!-- flex-container -->
	
	<sec:authorize access="!hasRole('DONA')">
	</sec:authorize>
	
	<sec:authorize access="hasRole('OPERA') or hasRole('ADMIN') or hasRole('AVIS')">
	
<%-- div x parametri di stampa --%>
		<div id="reportAgenda" style="position: absolute; top: 0px; left: 0px; z-index: 1000; background: transparent none repeat scroll 0px 0px; height: 100%; width: 100%; display: none; background-color: rgba(192,192,192,0.6);">
	<!-- 	 display: none; block; -->
		
		    <form:form method="GET" action='reportAgenda' id="reportAgenda" target="_blank" class="form-horizontal" style="position: relative; margin: auto; top: 100px; width: 250px; padding-top:20px; padding-bottom:20px; background-color: rgba(255, 255, 255, 1);">
				<div class="row">
					<div class="form-group col-md-12">
						<div class="col-md-12">
							<label class="col-md-4 control-lable" for="tipoDona"><fmt:message key="agenda.para.tipoDona" bundle="${lang}"/></label>
							<div class="col-md-8">
								<select name='tipoDona'>
									<option value="0">--</option>
								    <c:forEach items="${listTipoDonaz}" var="tipoDona">
								    	<option value="${tipoDona.codice}"
								    		>${tipoDona.descrizione}</option>
								    </c:forEach>
								</select>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="form-group col-md-12">
						<div class="col-md-12">
							<label class="col-md-4 control-lable" for="puntoPrelievo"><fmt:message key="agenda.para.pp" bundle="${lang}"/></label>
							<div class="col-md-8">
								<select name='puntoPrelievo'>
									<option value="0">--</option>
								    <c:forEach items="${listPuntiPrel}" var="puntiPrel">
								    	<option value="${puntiPrel.codicepuntoprel}"
								    		>${puntiPrel.nomepuntoprel}</option>
								    </c:forEach>
								</select>
							</div>
						</div>
					</div>
				</div>
			        
			        
				<div class="row">
					<div class="form-group col-md-12">
						<div class="col-md-12">
							<label class="col-md-4 control-lable" for="dateFrom"><fmt:message key="agenda.para.dateFrom" bundle="${lang}"/></label>
							<div class="col-md-8">
								<input type="text" name="dateFrom" id="dateFrom" class="form-control input-sm"/>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="form-group col-md-12">
						<div class="col-md-12">
							<label class="col-md-4 control-lable" for="dateTo"><fmt:message key="agenda.para.dateTo" bundle="${lang}"/></label>
							<div class="col-md-8">
								<input type="text" name="dateTo" id="dateTo" class="form-control input-sm"/>
							</div>
						</div>
					</div>
				</div>			
				
				<div class="row">
					<div class="form-actions floatRight col-md-3"><span></span> </div>
					<div class="form-actions col-md-6">
					
						<input type="submit" value="<fmt:message key="report.button.submit" bundle="${lang}"/>" style="width: 100%;" class="btn btn-primary btn-sm" onclick="{document.getElementById('reportAgenda').style.display = 'none';}">
	
						<a href="#" class="btn btn-primary btn-sm" onclick="{document.getElementById('reportAgenda').style.display = 'none';}" style=" width: 100%; margin-top: 15px;  height: 22px;">
							<fmt:message key="agenda.report.annulla" bundle="${lang}"/>
			  			</a>
			  			
					</div>
				</div>
				
			</form:form>
		</div> 
	</sec:authorize>
	
<%-- div x parametri di stampa --%>	

<%-- div x elenco ore per prenotazione --%>	
	<div id="scrollPanelHours" style="overflow-y:auto; width: 300px; height: 200px; border: solid 1px; display: none; position: relative; margin: auto; ">
	   <table class="table table-hover" style="width:100%">
	    <thead>
	      <tr>
	        <th><fmt:message key="agenda.hours" bundle="${lang}"/></th>
	        <th><fmt:message key="agenda.available" bundle="${lang}" /></th>
	      </tr>
	    </thead>
	    <tbody id = 'hours' >
	    </tbody>
	  </table>
	</div>	
<%-- div x elenco ore per prenotazione --%>	
  
 </div>	
</body>
</html>