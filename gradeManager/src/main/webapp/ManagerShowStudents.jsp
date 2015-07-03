<%@ page import="java.util.List, nl.utwente.di.gradeManager.servlets.* , nl.utwente.di.gradeManager.model.*, java.util.ArrayList"  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  <html>
  <head>
  	<meta http-equiv="Content-type" content="text/html; charset=ISO-8859-1">
  	<jsp:useBean id="students" type="nl.utwente.di.gradeManager.servlets.AllStudents"
  	scope="request" />
    <link rel="stylesheet" href="css/foundation.css">
    <script src="js/d3.js"></script>
    <script src="js/d3.min.js"></script>
    <script src="js/import.js"></script>
    <script src="js/vendor/modernizr.js"></script>
    <script src="js/vendor/jquery.js"></script>
    <script src="js/vendor/fastclick.js"></script>
    <script src="js/foundation/foundation.js"></script>
    <script src="js/foundation.min.js"></script>
    <title>TOSTi Manager</title>
  </head>
  <body>

    <nav class="top-bar" data-topbar role="navigation" data-options="is_hover: false">
      <ul class="title-area">
        <li class="name">
          <h1><a href="#">TOSTi</a></h1>
        </li>
         <!-- Remove the class "menu-icon" to get rid of menu icon. Take out "Menu" to just have icon alone -->
        <li class="toggle-topbar menu-icon"><a href="#"><span>Menu</span></a></li>
      </ul>

      <section class="top-bar-section">
        <!-- Right Nav Section -->
        <ul class="left">
          <li class="has-dropdown">
            <a href="#">Studenten</a>
            <ul class="dropdown">
              <li> 
                <a href="/gradeManager/Manager/ShowStudent">Zien</a>
              </li>
              <li> 
                <a href="/gradeManager/Manager/CreateStudent">Nieuw</a>
              </li>
            </ul>
          </li>
          <li>
            <a href="/gradeManager/Manager" class="">Bestand importeren</a>
          </li>
        </ul>
      </section>
      <section class="top-bar-section">
      <ul class="right">
      <li><a href="#">My account</a></li>
      <li><a href="#">Settings</a></li>
      <li><a href="Logout">Logout</a></li>
      </ul>
      </section>
    </nav>
		
		
		<div id="ShowStudent">
    <h1>Alle studenten</h1>
    <table>
      <thead>
        <tr>
          <td>
            StudentID
          </td>
          <td>
            Voornaam
          </td>
          <td>
            Achternaam
          </td>
        </tr>
      </thead>
      <tbody>
      <% List<Student> studentlist = students.getStudents();
      for (Student student : studentlist){
    	  out.println("<tr><td>" + student.getPersonID() + 
    			  "</td><td>" + student.getFirstname() + "</td><td>" 
    			  + student.getSurname() + "</td></tr>");
      }
      %>
        
      </tbody>
    </table>
    </div>
		<script>
			$(document).ready(addListeners());
			$(document).foundation();
		</script>
  </body>
</html>