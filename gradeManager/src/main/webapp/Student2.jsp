<%@ page import="java.util.List, nl.utwente.di.gradeManager.servlets.StudentCourses, nl.utwente.di.gradeManager.servlets.CourseAssignments, nl.utwente.di.gradeManager.model.*"  %>
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
<script src="Navigatiebalk.js"></script>
<h1 style="background-color:#EAEAEA; border-style:solid; border-width:2px; border-color:#EAEAEA">Module 1</h1>
<ul style="float:left; width: 50%" class="accordion" data-accordion>
<% List<Course> courses = coursestoShow.getCourses();
		for (Course course : courses) {
			out.println("<li class=\"accordion-navigation\">"+
							"<a href=\"#=" + course.getName() + "> " + course.getName() + "</a>"+
								"<div style=\"background-color:white\" id==" + course.getName() + " class=\"content\">"+
									"<table>"+
										"<thead>"+
											"<tr>"+
												"<th>Opdracht</th>"+
												"<th>Resultaat</th>"+
											"</tr>"+
										"</thead>"
			);
			List<Assignment> assignments = assignmentstoShow.getAssignments();
				for (Assignment assignment : assignments) {
					out.println(		"<tbody>"+
											"<tr>"+
												"<td>" + assignment.getName() + ":</td>"+
												"<td style=\"background-color:#FF0000\">" + assignment.getMinimumresult() + "</td>"+
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
	<script>
	  $(document).foundation();
	</script>

</body>

</html>