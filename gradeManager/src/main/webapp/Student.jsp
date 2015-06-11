<%-- Mock-up van Cijferpagina jsp. --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>TOSTi Framework</title>
    <link rel="stylesheet" href="css/foundation.css"/>
    <script src="js/vendor/modernizr.js"></script>
    <script src="js/vendor/jquery.js"></script>
    <script src="js/vendor/fastclick.js"></script>
    <script src="js/foundation.min.js"></script>
</head>
<body>
<script src="Navigatiebalk.js"></script>
<h1 style="background-color:#EAEAEA; border-style:solid; border-width:2px; border-color:#EAEAEA">module.getname()</h1>
<ul style="float:left; width: 50%" class="accordion" data-accordion>
<%-- For-loopje voor aantal Vakken in Module --%>
<% for(int i=0, i => moduleset.getlength(), i++) {%>
<li class="accordion-navigation">
	<a href="#<%=i.getnaam() %>"><%=i.getnaam()%></a>
		<div style="background-color:white" id="<%=i.getnaam() %>"class="content">
			<table>
			<thead>
				<tr>
					<th>Opdracht</th>
					<th>Resultaat</th>
				</tr>
			</thead>
			<tbody>
			<%-- For-loopje voor aantal opdrachten in vak --%>
			<% for(int j=0, j <=vakset.getlength(), j++) {%>
				<tr>
					<td><%j.getNaam(); %></td>
					<td style="background-color:<%if (j.result<4.5) {%>red <% } else if (j.result>=6.0) {%>lime <% }else { %>orange<% }  %>"><%=j.getResult() %></td>
					</tr>
					<% } %>
			</tbody>
			</table></div>
			</li>
			<% } %>
</ul>
<table style="float:right; width=50%">
	<tbody>
		<tr>
			<td style="width:25%">Laatste wijziging:</td>
			<td style="width:25%">6-6-2015</td>
		</tr>
		<tr>
			<td>Toegevoegd door:</td>
			<td><%-- Dit moet nog variabel worden --%>Mourice van Ceulen</td>
		</tr>
		<tr>
			<td>Minimaal cijfer:</td>
			<td><%-- Dit moet nog variabel worden --%>6.0</td>
		</tr>
		<tr>
			<td>Datum van Toets:</td>
			<td><%-- Dit moet nog variabel worden --%>4-6-2015</td>
		</tr>
		<tr>
			<td>Weging:</td>
			<td><%-- Dit moet nog variabel worden --%>15%</td>
		</tr>
		<tr>
			<td>Opmerking:</td>
			<td><%-- Dit moet nog variabel worden --%></td>
		</tr>
	</tbody>
</table>
</body>
</html>
