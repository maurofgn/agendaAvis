<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url value="/auditRecs" var="auditRecs"/>
<%-- <c:url value="/users/download" var="downloadUrl"/> --%>
<%-- <c:url value="/users/download/token" var="downloadTokenUrl"/> --%>
<%-- <c:url value="/users/download/progress" var="downloadProgressUrl"/> --%>

<html>
<head>
	<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/static/css/jquery-ui/pepper-grinder/jquery-ui-1.8.16.custom.css"/>'/>
	<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/static/css/ui.jqgrid-4.3.1.css"/>'/>
<%-- 	<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/static/css/style.css"/>'/> --%>
	
	<script type='text/javascript' src='<c:url value="/static/js/jquery-1.6.4.min.js"/>'></script>
	<script type='text/javascript' src='<c:url value="/static/js/jquery-ui-1.8.16.custom.min.js"/>'></script>
	<script type='text/javascript' src='<c:url value="/static/js/grid.locale-en-4.3.1.js"/>'></script>
	<script type='text/javascript' src='<c:url value="/static/js/jquery.jqGrid.min.4.3.1.js"/>'></script>
<%-- 	<script type='text/javascript' src='<c:url value="/static/js/custom.js"/>'></script> --%>
	
	<title>Audit</title>
	
	<script type='text/javascript'>
	$(function() {
		$("#grid").jqGrid({
		   	url:'${auditRecs}',
			datatype: 'json',
			mtype: 'GET',

		   	colNames:['Id', 'utente', 'created', 'creato', 'stato'],
		   	colModel:[
		   		{name:'Id',index:'id', width:55, editable:false, editoptions:{readonly:true, size:10}, hidden:true},
		   		{name:'ssoId',index:'ssoId', width:45, editable:true, editrules:{required:true}, editoptions:{size:10}},
		   		{name:'created',index:'created', width:45, editable:false, editrules:{required:true}, editoptions:{size:10}, hidden:true},
		   		{name:'createdString',index:'createdString', align: "center", width:45, editable:true, editrules:{required:true}, editoptions:{size:10}},
		   		{name:'state',index:'state', width:250, editable:false}
		   	],
		   	postData: {},
			rowNum: 40,
		   	rowList:[10,20,40,60],
		   	height: 480,
		   	autowidth: true,
			rownumbers: true,
		   	pager: '#pager',
		   	sortname: 'created',
		    viewrecords: true,
		    sortorder: "desc",
		    caption:"Audits",
		    emptyrecords: "Empty records",
		    loadonce: false,
		    loadComplete: function() {},
		    jsonReader : {
		        root: "rows",
		        page: "page",
		        total: "total",
		        records: "records",
		        repeatitems: false,
		        cell: "cell",
		        id: "id"
		    }
		});

		$("#grid").jqGrid('navGrid','#pager',
				{edit:false, add:false, del:false, search:true},
				{}, {}, {}, {}
		);
		
// 		$("#grid").navButtonAdd('#pager',
// 				{ 	caption:"Pdf", 
// 					buttonicon:"ui-icon-arrowreturn-1-s", 
// 					onClickButton: downloadPdf,
// 					position: "last", 
// 					title:"", 
// 					cursor: "pointer"
// 				} 
// 			);
		
// 		$("#grid").navButtonAdd('#pager',
// 				{ 	caption:"Excel", 
// 					buttonicon:"ui-icon-arrowreturn-1-s", 
// 					onClickButton: downloadXls,
// 					position: "last", 
// 					title:"", 
// 					cursor: "pointer"
// 				} 
// 			);
		
	});

// 	function downloadXls() {download('xls');}
	
// 	function downloadPdf() {download('pdf');}
	
// 	function download(type) {
// 		// Retrieve download token
// 		// When token is received, proceed with download
// 		$.get('${downloadTokenUrl}', function(response) {
// 			// Store token
// 			var token = response.message[0];
			
// 			// Show progress dialog
// 			$('#msgbox').text('Processing download...');
// 			$('#msgbox').dialog( 
// 					{	title: 'Download',
// 						modal: true,
// 						buttons: {"Close": function()  {
// 							$(this).dialog("close");} 
// 						}
// 					});
			
// 			// Start download
// 			window.location = '${downloadUrl}'+'?token='+token+'&type='+type;

// 			// Check periodically if download has started
// 			var frequency = 1000;
// 			var timer = setInterval(function() {
// 				$.get('${downloadProgressUrl}', {token: token}, 
// 						function(response) {
// 							// If token is not returned, download has started
// 							// Close progress dialog if started
// 							if (response.message[0] != token) {
// 								$('#msgbox').dialog('close');
// 								clearInterval(timer);
// 							}
// 					});
// 			}, frequency);
			
// 		});
// 	}
	</script>
</head>

<body>
	<div class="container">
	
		<h1 id='banner'>Audit</h1>
		
		<div id='jqgrid'>
			<table id='grid'></table>
			<div id='pager'></div>
		</div>
		
		<div id='msgbox' title='' style='display:none'></div>
	</div>	
</body>
</html>