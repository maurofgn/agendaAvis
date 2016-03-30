<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
Integer userId = 0;
String nome = "";
if (session.getAttribute("userId") != null) {
	userId = (Integer)session.getAttribute("userId");
	nome = (String) session.getAttribute("nome");
	}
%>

<style type="text/css">
  table.header {
    width: 100%;
    border=0; 
    cellspacing=0;
    cellpadding=0;
/*     overflow: hidden; */
/*     display: inline-block; */
    white-space: nowrap;
/*     text-align: center; */
    }
  table.header th {
    width: 30px;
/*     overflow: hidden; */
    display: inline-block;
    white-space: nowrap;
    text-align: center;
    }
  table.header td {
    width: 30px;
/*     overflow: hidden; */
    display: inline-block;
    white-space: nowrap;
    text-align: center;
}

#locale A { padding:10px 0 1px 0; margin-left: 2px; display:block; float:left; }
#locale A.current { padding:2px 0 1px 0; }
</style>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale.language}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="org.mesis.i18n.Message" var="lang"/>
		
<table class="header">
		<tr>
			<td>
				<div id="tmmLogo" align="left" >
				<a href="<%=request.getContextPath()%>">
					<img src="<%=request.getContextPath()%>/images/logo.png" alt="TMM"/>
				</a>
				</div>
			</td>
		
<%-- <c:if test="${userId > 0}"> --%>

<%-- 	<c:if test="${'A' == ruolo}"> --%>
<!-- 			<td> -->
<!-- 				<div align="left"> -->
<!-- 				  <div class="dropdown"> -->
<!-- 				    <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown"> -->
<!-- 				    	<span class="caret"></span> -->
<!-- 				    </button> -->
<!-- 				    <ul class="dropdown-menu"> -->
<%-- 				      <li><a href="<%=request.getContextPath()%>/AnagraficaController">Persone</a></li> --%>
<!-- 				    </ul> -->
<!-- 				  </div> -->
<!-- 				</div> -->
<!-- 			</td> -->
<%-- 	</c:if> --%>

<!-- 			<td> -->
<!-- 				<div id="wellcome" align="right"> -->
<%-- 					<fmt:message key="login.label.username" bundle="${lang}"/>: --%>
<%-- 					<a href="<%=request.getContextPath()%>/AnagraficaController?action=edit&id=<%=userId %>"> --%>
<%-- 						<%=nome%> --%>
<!-- 					</a> -->
<!-- 				</div>			 -->
<!-- 			</td> -->
			
<!-- 			<td> -->

<!-- 					<div style="float:right; padding-top:20px" id="locale"> -->
					
<%-- 					<a <c:if test="${language == 'it'}">class="current"</c:if><c:if test="${language != 'it'}">href="<%=request.getContextPath()%>/?language=it"</c:if>><img src="<%=request.getContextPath()%>/images/flag_it.gif" border="" alt=""></a> --%>
<%-- 					<a <c:if test="${language == 'en'}">class="current"</c:if><c:if test="${language != 'en'}">href="<%=request.getContextPath()%>/?language=en"</c:if>><img src="<%=request.getContextPath()%>/images/flag_en.gif" border="" alt=""></a> --%>
<%-- 					<a <c:if test="${language == 'es'}">class="current"</c:if><c:if test="${language != 'es'}">href="<%=request.getContextPath()%>/?language=es"</c:if>><img src="<%=request.getContextPath()%>/images/flag_es.gif" border="" alt=""></a> --%>
<%-- 					<a <c:if test="${language == 'de'}">class="current"</c:if><c:if test="${language != 'de'}">href="<%=request.getContextPath()%>/?language=de"</c:if>><img src="<%=request.getContextPath()%>/images/flag_de.gif" border="" alt=""></a> --%>
<%-- 					<a <c:if test="${language == 'fr'}">class="current"</c:if><c:if test="${language != 'fr'}">href="<%=request.getContextPath()%>/?language=fr"</c:if>><img src="<%=request.getContextPath()%>/images/flag_fr.gif" border="" alt=""></a> --%>

<!-- 					</div> -->
<!-- 			</td> -->
			
<%-- 			<td>
				<div id="checkout" align="center">
					<a href="<%=request.getContextPath()%>/LogoutServlet">
					<img src="<%=request.getContextPath()%>/images/shutdown.png" alt="Uscita"/>
					</a>
				</div>
			</td>
</c:if>	 --%>		
			
		</tr>
</table>
