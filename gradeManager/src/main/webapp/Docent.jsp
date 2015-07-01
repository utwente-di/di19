<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.List, nl.utwente.di.gradeManager.servlets.* , nl.utwente.di.gradeManager.model.*, java.util.ArrayList"  %>
<html class=" js flexbox flexboxlegacy canvas canvastext webgl no-touch geolocation postmessage no-websqldatabase indexeddb hashchange history draganddrop websockets rgba hsla multiplebgs backgroundsize borderimage borderradius boxshadow textshadow opacity cssanimations csscolumns cssgradients no-cssreflections csstransforms csstransforms3d csstransitions fontface generatedcontent video audio localstorage sessionstorage webworkers applicationcache svg inlinesvg smil svgclippaths" style="">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
  	<jsp:useBean id="coursestoShow" type="nl.utwente.di.gradeManager.servlets.StudentCourses"
		scope="request" />
	<jsp:useBean id="assignmentstoShow" type="nl.utwente.di.gradeManager.servlets.CourseAssignments"
		scope="request" />
	<jsp:useBean id="moduletoShow" type="nl.utwente.di.gradeManager.model.Module"
		scope="request" />
	<jsp:useBean id="resultstoShow" type="nl.utwente.di.gradeManager.servlets.StudentAssignments"
		scope="request" />
	<jsp:useBean id="docent" type="nl.utwente.di.gradeManager.model.Teacher"
		scope="request"/>
	<jsp:useBean id="docentModules" type="nl.utwente.di.gradeManager.servlets.StudentModules"
		scope="request"/>
	<jsp:useBean id="studentstoShow" type="nl.utwente.di.gradeManager.servlets.CourseStudents"
		scope="request"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TOSTi GradeManagement</title>
    <link rel="stylesheet" href="css/foundation.css"/>
    <script src="js/d3.js"></script>
    <script src="js/d3.min.js"></script>
    <script src="js/Teacher_module_overview.js"></script>
    <script src="js/vendor/modernizr.js"></script>
    <script src="js/vendor/jquery.js"></script>
    <script src="js/vendor/fastclick.js"></script>
    <script src="js/foundation.min.js"></script>
    <meta class="foundation-data-attribute-namespace"><meta class="foundation-mq-xxlarge"><meta class="foundation-mq-xlarge-only"><meta class="foundation-mq-xlarge"><meta class="foundation-mq-large-only"><meta class="foundation-mq-large"><meta class="foundation-mq-medium-only"><meta class="foundation-mq-medium"><meta class="foundation-mq-small-only"><meta class="foundation-mq-small"><style></style>
	<meta class="foundation-mq-topbar">
</head>
<body>
<nav class="top-bar" data-topbar role="navigation" data-options="is_hover: false">
  <ul class="title-area">
    <li class="name">
      <h1><a href="#">TOSTi <jsp:getProperty name="docent" property="firstname"/> <jsp:getProperty property="surname" name="docent"/></a></h1>
    </li>
     <!-- Remove the class "menu-icon" to get rid of menu icon. Take out "Menu" to just have icon alone -->
    <li class="toggle-topbar menu-icon"><a href="#"><span>Menu</span></a></li>
  </ul>

  <section class="top-bar-section">
    <!-- Right Nav Section -->
    <ul class="left">
    
 <%
	//Lijst met modules uit de bean halen en opslaan in variabele
	 List<Module> modules = docentModules.getModules();
		//Lijst aanmaken voor alle jaren waarin de student een module heeft gedaan
	 List<Integer> years = new ArrayList<Integer>();
		//Loop over alle modules heen
	 for (Module module : modules){
	 	//Check of jaartal al in de lijst staat
	 	if (!years.contains(module.getYear())){
	 		//Jaartal toevoegen aan de lijst
	 		years.add(module.getYear());
	 	}
	 }
		//For loop over alle jaren heen
	 for (int i = 0; i <years.size(); i++ ){
	 	//Voor elk jaar een dropdownmenu genereren
	 	out.println("<li class=\"has-dropdown\">" +
	 					"<a href=\"#\">" + years.get(i) + "</a>" +
	 					"<ul class=\"dropdown\">");
	 	//For loop over alle modules heen
	 	for (Module module : modules){
	 		//Check of het jaar van de module overeenkomt met het jaar in het dropdown menu
	 		if (module.getYear() == years.get(i)){
	 			//Module toevoegen aan het dropdowm menu
	 			out.println("<li><a href=\"Resulttabel?moduleid=" +  module.getModulecode() + "&moduleyear=" + module.getYear() + "\">" +
	 							module.getName() + "</a></li>");
	 		}
	 	}
	 	//Alles netjes afsluiten
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

<h1 style="background-color:#EAEAEA; border-style:solid; border-width:2px; border-color:#EAEAEA"><jsp:getProperty name="moduletoShow" property="name"/></h1>
<ul style="float:left; width: 50%" class="accordion" data-accordion="">

<%	//Lijst met alle courses van de module maken
	List<Course> courses = coursestoShow.getCourses();
	//Lijst met alle assignments van alle vakken
	List<Assignment> assignments = assignmentstoShow.getAssignments();
	//Lijst met alle resultaten van alle assignments
	List<AssignmentResult> results = resultstoShow.getAssResults();
	//Complete lijst van studenten
	List<Student> students = studentstoShow.getStudents();
	
	//Alle vakken van de module doorlopen
	for (Course course : courses) {
		//Check of het vak in het jaar van de module valt.
		//Omdat modules dezelfde naam kunnen hebben als SuperModule, moeten we het jaartal checken
		if(course.getYear() == moduletoShow.getYear()){
		//Spaties uit de vaknaam trimmen zodat hij gebruikt kan worden als ID in de accordion
		String courseNameID = course.getName().replaceAll("\\s","");
		//Standaard accordion html printen
		out.println("<li class=\"accordion-navigation\">" +
						"<a aria-expanded=\"false\" href=\"#" + courseNameID + "\">" + course.getName() + "</a>" +
							"<div style=\"background-color:white\" id=\"" + courseNameID + "\" class=\"content\">" + 
								"<ul class=\"accordion\" data-accordion=\"\">");
		//Alle assignments van alle vakken doorlopen
		for (Assignment assignment : assignments) {
			//Checken of assignment en vak bij elkaar horen door:
			//Assignment en vak hebben zelfde vakcode
			//Assignment en vak vallen in hetzelfde jaar ( nodig om we Supercourses hebben, dus jaar is relevant)
			//Vak en module vallen in hetzelfde jaar
			if(assignment.getCourseCode() == course.getCourseCode() && assignment.getCourseyear() == course.getYear() && course.getYear() == moduletoShow.getYear()){
				//AssignmentID trimmen zodat hij gebruikt kan worden als ID in de sub-accordion
				String assNameID = assignment.getName().replaceAll("\\s","");
				//Sub-accordion printen voor de resultaaten per assignment
				out.println("<li class=\"accordion-navigation\">" +
								"<a aria-expanded=\"false\" href=\"#" + assNameID + "\">" + assignment.getName() + "</a> " + 
									"<div style=\"background-color:white\" id=\"" + assNameID + "\" class=\"content\">" +
										"<table>" +
							 				"<thead><tr><th>Name</th><th>ID</th><th>Date</th><th>Grade</th></tr></thead>");
				//Alle assignments van de vakken doorlopen
				for(AssignmentResult result : results){
					//Checken of het een result van het vak is
					if(result.getAssignmentid() == assignment.getAssignmentID()){
						//Alle studenten doorlopen
						for(Student student : students){
							//Checken of de welke student het resultaat behaald heeft
							if(result.getStudentid() == Integer.parseInt(student.getPersonID().substring(1))){
								//Resultaat in de tabel zetten
								out.println("<tbody>" + 
												"<tr>" +
													"<td>" + student.getFirstname() + " " + student.getSurname() + "</td>" + 
													"<td>" + student.getPersonID() + "</td>" +
													"<td>" + result.getOccasiondate() + "</td>" +
													"<td class=\"grade\">" + result.getResult() + "</td>" +
												"</tr>" +
											"</tbody>"
											);
							}
						}
					}
				//Tabel en de subaccordion afsluiten
				}out.println("</table>" + 
						"</div> </li>");
			}
		//Accordion afsluiten
		}out.println("</li></ul>");
		}
	}
%>
</ul>


<div class="databox">
  <h3>Cijfer verdeling</h3>
  <svg id="graphbox">
  </svg>
</div>



  <script>
    $(document).ready(prepare()); //will add listeners soon
    $(document).foundation();
  </script>
</body></html>