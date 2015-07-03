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
	<script src="js/import.js"></script>
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
    
    <div id="Import">
    <div class="panel left" style="height:500px; width:29%;">
    <div id="dvImportSegments" class="fileupload ">
				<h1>Importeer uw bestand</h1>
				<input type="file" name="File Upload" id="txtFileUpload" accept=".csv, .xls" />
				<select id="tableSelector">
				</select>
		</div>
		</div>
	<div class="panel right" style="height:500px; width:70%;">
	
		<div id="showHash">
		</div>
		<div id="importData">
		</div>
		</div>
		</div>
		
		
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
    <div id="CreateStudent">
    <h1>Maak een student aan</h1>
    <form>
      	<label for="studentID-Create">Student Nr
      	<input id="studentID-Create" type="text" name="studentID" required/>
      	</label>
  	  	<label for="firstname-Create">Voornaam
      	<input id="firstname-Create" type="text" name="firstname" required/>
      	</label>
  	  	<label for="lastname-Create">Achternaam
      	<input id="lastname-Create" type="text" name="lastname" required/>
      	</label>
  	  	<label for="password-Create">Wachtwoord
      	<input id="password-Create" type="password" name="password" required/>
      	</label>
      	<input type="submit" value="Maak student aan"/>
    </form>
    </div>
		<script>
			$(document).ready(addListeners());
			$(document).foundation();
		</script>
  </body>
</html>