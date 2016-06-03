<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII" />
<title>Sessioni</title>

</head>
<body>

	<div class="container">
		<h2>Sessioni</h2>
	</div>
	current: <%= session.getServletContext().getAttribute("currentusers") %>
	<br>
	total: <%=session.getServletContext().getAttribute("totalusers") %>

</body>
</html>