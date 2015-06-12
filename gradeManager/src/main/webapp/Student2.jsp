<%@ page import="java.util.List, nl.utwente.di.gradeManager.servlets.* , nl.utwente.di.gradeManager.model.*"  %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<jsp:useBean id="coursestoShow" type="nl.utwente.di.gradeManager.servlets.StudentCourses"
		scope="request" />
	<jsp:useBean id="assignmentstoShow" type="nl.utwente.di.gradeManager.servlets.CourseAssignments"
		scope="request" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>TOSTi Framework</title>
    <link rel="stylesheet" href="css/foundation.css"/>
    <script src="js/vendor/modernizr.js"></script>
    <script src="js/vendor/jquery.js"></script>
    <script src="js/vendor/fastclick.js"></script>
    <script src="js/foundation.min.js"></script>
</head>
<body>
<script src="js/Navigatiebalk.js"></script>
<h1 style="background-color:#EAEAEA; border-style:solid; border-width:2px; border-color:#EAEAEA">Module 1</h1>
<ul style="float:left; width: 50%" class="accordion" data-accordion>

<% List<Course> courses = coursestoShow.getCourses();
		for (Course course : courses) {
			String courseNameID = course.getName().replaceAll("\\s","");
			out.println("<li class=\"accordion-navigation\">" + 
							"<a href=\"#" + courseNameID + "\"> " + course.getName() + "</a>"+
								"<div style=\"background-color:white\" id=\"" + courseNameID + "\" class=\"content\">"+
									"<table>"+
										"<thead>"+
											"<tr>"+
												"<th>Opdracht</th>"+
												"<th>Resultaat</th>"+
												"<th>Datum</th>"+
											"</tr>"+
										"</thead>" 
						);
			List<Assignment> assignments = assignmentstoShow.getAssignments();
				for (Assignment assignment : assignments) {
					String backgroundcolour = "";
					
					if(assignment.getMinimumresult() < 5.4) {
						//result is not passing grade.
						backgroundcolour = "FF0000"; // red
						} else {
						backgroundcolour = "00FF00"; // green
					}
					
					
					out.println(		"<tbody>" + 
											"<tr>"+
												"<td>" + assignment.getName() + ":</td>"+
												"<td style=\"background-color:#"+ backgroundcolour + "\">" + assignment.getMinimumresult() + "</td>"+
											"</tr>"+
										"</tbody>");
				}
			out.println(
									"</table>"+
								"</div>"+
							"</li>"
			);
		}
%>
	</ul>
	<table style="float:right; width=50%">
	<tbody>
		<tr>
			<td style="width:25%">Laatste wijziging:</td>
			<td style="width:25%">6-6-2015</td>
		</tr>
		<tr>
			<td>Toegevoegd door:</td>
			<td>Maurice van Keulen</td>
		</tr>
		<tr>
			<td>Minimaal cijfer:</td>
			<td>6.0</td>
		</tr>
		<tr>
			<td>Datum van Toets:</td>
			<td>4-6-2015</td>
		</tr>
		<tr>
			<td>Weging:</td>
			<td>15%</td>
		</tr>
		<tr>
			<td>Opmerking:</td>
			<td>Dit is een test cijfer</td>
		</tr>
	</tbody>
</table>
	<script>
	  $(document).foundation();
	</script>

</body>

</html>