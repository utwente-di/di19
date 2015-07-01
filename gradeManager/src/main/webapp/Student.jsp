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
    
<%	//Lijst met modules uit de bean halen en opslaan in variabele
    List<Module> modules = studentModules.getModules();
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
    	out.println("</ul></li>");		
    }
    	%>
	</ul>
  </section>
  <section class="top-bar-section">
  <ul class="right">
	<li><a href="#">My account</a></li>
	<li><a href="#">Settings</a></li>
	</ul>
	</section>
</nav>
<% 	//Variabele aanmaken voor de achtergrondkleur van het module eindcijfer
	String backgroundcolor;
	//Als het cijger lager is dan 5.5 wordt de achtergrond rood
	if (moduleresulttoShow.getResult().doubleValue() < 5.5){
		backgroundcolor = "e74c3c";
	//Als het cijfer hoger of gelijk aan 5.5 is wordt de achtergrond groen
	} else {
		backgroundcolor = "2ecc71";}
%>

<h1 style="background-color:#EAEAEA; border-style:solid; border-width:2px; border-color:#EAEAEA"> <div style="float:left; width: 47%"><jsp:getProperty name="moduletoShow" property="name"/></div> <div style="width:4.2%; float:left; background-color:#<%out.println(backgroundcolor);%>"><jsp:getProperty name="moduleresulttoShow" property="result"/></div></h1>
<ul style="float:left; width: 50%" class="accordion" data-accordion>

<% 	//Lijst met alle vakken van de student aanmaken
	List<Course> courses = coursestoShow.getCourses();
	//Lijst met assignments aanmaken
	List<Assignment> assignments = assignmentstoShow.getAssignments();
	//Volledige lijst met resultaten ophalen
	List<AssignmentResult> results = resultstoShow.getAssResults();
	//Variabele voor de achtergrondkleur van het cijfer aanmaken
	String backgroundcolour = "";
		//Voor elk vak wordt een eigen accordion aangemaakt en gevuld met alle resultaten van dat vak
		for (Course course : courses) {
			//Check of het vak in het jaar van de module valt.
			//Omdat modules dezelfde naam kunnen hebben als SuperModule, moeten we het jaartal checken
			if(course.getYear() == moduletoShow.getYear()){
			//Spaties uit de vaknaam trimmen zodat hij gebruikt kan worden als ID in de accordion
			String courseNameID = course.getName().replaceAll("\\s","");
			//Standaard accordion html printen
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
				//Alle assignments doorlopen
				for (Assignment assignment : assignments) {
					//For-loop over alle resultaten heen
					for(AssignmentResult result : results){
						//Checken of vak, assignment en resultaat bij elkaar horen door:
						//Assignment en vak hebben zelfde vakcode
						//Assignment en vak vallen in hetzelfde jaar ( nodig om we Supercourses hebben, dus jaar is relevant)
						//Assignment en resultaat hebben hetzelfde assignmentID
						//Vak en module vallen in hetzelfde jaar
						if(assignment.getCourseCode() == course.getCourseCode() && assignment.getCourseyear() == course.getYear() && result.getAssignmentid() == assignment.getAssignmentID() && course.getYear() == moduletoShow.getYear()){	
							//Als resultaat lager is dan 5.4 achtergrond op rood zetten
							if(result.getResult().doubleValue() < 5.5) {
								backgroundcolour = "e74c3c"; // red
							//ALs dat niet het geval is achtergrond op groen zetten
							} else {
								backgroundcolour = "2ecc71"; // green
					}
					//Informatie en resultaat van de assignment printen in de accordion
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
			//Alles correct afsluiten
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