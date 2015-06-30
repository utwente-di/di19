<%-- This page should be for the students. --%>
<%@ page import="java.util.List, nl.utwente.di.gradeManager.servlets.* , nl.utwente.di.gradeManager.model.*, java.util.ArrayList"  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<jsp:useBean id="coursestoShow" type="nl.utwente.di.gradeManager.servlets.StudentCourses"
		scope="request" />
	<jsp:useBean id="assignmentstoShow" type="nl.utwente.di.gradeManager.servlets.CourseAssignments"
		scope="request" />
	<jsp:useBean id="moduletoShow" type="nl.utwente.di.gradeManager.model.Module"
		scope="request" />
	<jsp:useBean id="resultstoShow" type="nl.utwente.di.gradeManager.servlets.StudentAssignments"
		scope="request" />
	<jsp:useBean id="moduleresulttoShow" type="nl.utwente.di.gradeManager.model.ModuleResult"
		scope="request"/>
	<jsp:useBean id="student" type="nl.utwente.di.gradeManager.model.Student"
		scope="request"/>
	<jsp:useBean id="studentModules" type="nl.utwente.di.gradeManager.servlets.StudentModules"
		scope="request"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>TOSTi GradeManagement</title>
    <link rel="stylesheet" href="css/foundation.css"/>
    <script src="js/vendor/modernizr.js"></script>
    <script src="js/vendor/jquery.js"></script>
    <script src="js/vendor/fastclick.js"></script>
    <script src="js/foundation.min.js"></script>
</head>
<body>
<nav class="top-bar" data-topbar role="navigation" data-options="is_hover: false">
  <ul class="title-area">
    <li class="name">
      <h1><a href="#">TOSTi <jsp:getProperty name="student" property="firstname"/> <jsp:getProperty property="surname" name="student"/></a></h1>
    </li>
     <!-- Remove the class "menu-icon" to get rid of menu icon. Take out "Menu" to just have icon alone -->
    <li class="toggle-topbar menu-icon"><a href="#"><span>Menu</span></a></li>
  </ul>

  <section class="top-bar-section">
    <!-- Right Nav Section -->
    <ul class="left">
    
    <%List<Module> modules = studentModules.getModules();
    List<Integer> years = new ArrayList<Integer>();
    for (Module module : modules){
    	if (!years.contains(module.getYear())){
    		years.add(module.getYear());
    	}
    }
    for (int i = 0; i <years.size(); i++ ){
    	out.println("<li class=\"has-dropdown\">" +
    					"<a href=\"#\">" + years.get(i) + "</a>" +
    					"<ul class=\"dropdown\">");
    	for (Module module : modules){
    		if (module.getYear() == years.get(i)){
    			out.println("<li><a href=\"Resulttabel?moduleid=" +  module.getModulecode() + "&moduleyear=" + module.getYear() + "\">" +
    							module.getName() + "</a></li>");
    		}
    	}
    	out.println("</ul></li>");		
    }
    	%>

  </section>
  <section class="top-bar-section">
  <ul class="right">
	<li><a href="#">My account</a></li>
	<li><a href="#">Settings</a></li>
	</ul>
	</section>
</nav>
<% String backgroundcolor;
if (moduleresulttoShow.getResult().doubleValue() < 5.5){
	backgroundcolor = "FF0000";
} else {
	backgroundcolor = "00FF00";}%>

<%--<script src="js/Navigatiebalk.js"></script>--%>
<h1 style="background-color:#EAEAEA; border-style:solid; border-width:2px; border-color:#EAEAEA"> <div style="float:left; width: 47%"><jsp:getProperty name="moduletoShow" property="name"/></div> <div style="width:4.2%; float:left; background-color:#<%out.println(backgroundcolor);%>"><jsp:getProperty name="moduleresulttoShow" property="result"/></div></h1>
<ul style="float:left; width: 50%" class="accordion" data-accordion>

<% List<Course> courses = coursestoShow.getCourses();
		for (Course course : courses) {
			if(course.getYear() == moduletoShow.getYear()){
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
			List<AssignmentResult> results = resultstoShow.getAssResults();
			String backgroundcolour = "";
				for (Assignment assignment : assignments) {
					for(AssignmentResult result : results){
						if(assignment.getCourseCode() == course.getCourseCode() && assignment.getCourseyear() == course.getYear() && result.getAssignmentid() == assignment.getAssignmentID() && course.getYear() == moduletoShow.getYear()){	
							if(result.getResult().doubleValue() < 5.5) {
							//result is not passing grade.
								backgroundcolour = "FF0000"; // red
							} else {
								backgroundcolour = "00FF00"; // green
					}
					
					
					out.println(		"<tbody>" + 
											"<tr>"+
												"<td>" + assignment.getName() + ":</td>"+
												"<td style=\"background-color:#"+ backgroundcolour + "\">" + result.getResult() + "</td>"+
												"<td>" + result.getOccasiondate() + "</td>" +
											"</tr>"+
										"</tbody>");
						}
					}
				}
			out.println(
									"</table>"+
								"</div>"+
							"</li>"
			);
		}
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