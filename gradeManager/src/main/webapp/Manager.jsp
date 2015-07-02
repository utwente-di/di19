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
                <a href="#ShowStudent">Zien</a>
              </li>
              <li> 
                <a href="#CreateStudent">Nieuw</a>
              </li>
              <li> 
                <a href="#EditStudent">Pas aan</a>
              </li>
            </ul>
          </li>
          <li class="has-dropdown">
            <a href="#">Modules</a>
            <ul class="dropdown">
              <li> 
                <a href="#ShowModule">Zien</a>
              </li>
              <li> 
                <a href="#CreateModule">Nieuw</a>
              </li>
              <li> 
                <a href="#EditModule">Pas aan</a>
              </li>
            </ul>
          </li>
          <li>
            <a href="#Import" class="">Bestand importeren</a>
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

    <h1>Default to #Import</h1>
    ...import...

    <h1>#Show</h1>
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

    <h1>#Create</h1>
    <form>
      	<label for="studentID-Create">Student Nr</label>
      	<input id="studentID-Create" type="text" name="studentID" required/>
  	  	<label for="firstname-Create">Voornaam</label>
      	<input id="firstname-Create" type="text" name="firstname" required/>
  	  	<label for="lastname-Create">Achternaam</label>
      	<input id="lastname-Create" type="text" name="lastname" required/>
  	  	<label for="password-Create">Wachtwoord</label>
      	<input id="password-Create" type="password" name="password" required/>
      	<input type="submit" value="Maak student aan"/>
    </form>

    <h1>#Edit</h1>
    <input type="text" value="hier moet een zoekfunctie over studenten, genereert de form hieronder;
      hou form statisch als je hier niet uit komt, dan maak ik de zoekfunctie wel"/>
    <form id="edit-field">
    
  	  	<label for="studentID-Edit">Student Nr</label>
      	<input id="studentID-Edit" type="text" name="studentID" value="1380087"/>
  	  	<label for="firstname-Edit">Voornaam</label>
      	<input id="firstname-Edit" type="text" name="firstname" value="Mark"/>
  	  	<label for="lastname-Edit">Achternaam</label>
      	<input id="lastname-Edit" type="text" name="lastname" value="'t Hart"/>
  	  	<label for="password-Edit">Wachtwoord</label>
      	<input id="password-Edit" type="password" name="password" value="randompassword"/>
      	
      	<input type="submit" value="Pas aan"/>
    </form>

    

    <script>
      $(document).foundation();
    </script>

  </body>
</html>