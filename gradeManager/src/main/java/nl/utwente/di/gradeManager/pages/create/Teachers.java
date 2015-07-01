package nl.utwente.di.gradeManager.pages.create;

import java.security.NoSuchAlgorithmException;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nl.utwente.di.gradeManager.db.GradesDB;
import nl.utwente.di.gradeManager.debug.Debug;
import nl.utwente.di.gradeManager.helpers.HTMLGenerator;
import nl.utwente.di.gradeManager.helpers.Security;
import nl.utwente.di.gradeManager.model.Teacher;

@Path("teachers")
public class Teachers {

	@GET
	@Produces(MediaType.TEXT_HTML)
	/**
	 * Show the form where teachers can be created.
	 * @return A HTML form where necessary information can be entered for adding a teacher object.
	 */
	public String showTeacher(){
		HTMLGenerator html = new HTMLGenerator(2);
		html.addLine("<h1> Create a Teacher Object </h1>");
		html.addLine("<form action = \"teachers\" method =\"post\" accept-charset=\"utf-8\">");
		html.addLine("<label for=\"teacherid\">teacherid</label>");
		html.addLine("<input id=\"teacherid\" name=\"teacherid\" type=\"text\" required/>" );
		html.addLine("<label for=\"firstname\">firstname</label>");
		html.addLine("<input id=\"firstname\" name=\"firstname\" type=\"text\" firstname required/>" );
		html.addLine("<label for=\"surname\">surname</label>");
		html.addLine("<input id=\"surname\" name=\"surname\" type=\"text\" required/>" );
		html.addLine("<label for=\"password\">password</label>");
		html.addLine("<input id=\"password\" name=\"password\" type=\"text\" required/>" );
		html.addLine("<label for=\"manager\"> manager </label>");
		html.addLine("<input id=\"manager\" name=\"manager\" type=\"checkbox\">");
		html.addLine("</br>");
		html.addLine("<input type=\"submit\" value=\"Submit\">" );
		html.addLine("</form>");
		return html.getHTML();
	}
	
	@POST
	@Produces(MediaType.TEXT_HTML)
	/**
	 * Creates a teacher object based on the entered information.
	 * @param personID The identifier of the teacher.
	 * @param firstname The first name of the teacher.
	 * @param surname The last name of the teacher.
	 * @param password The password of the teacher.
	 * @param manager Whether the teacher is a manager or not.
	 * @return A message confirming that the teacher is created.
	 */
	public String createTeacher(@FormParam("teacherid") String personID, @FormParam("firstname") String firstname, @FormParam("surname") String surname, @FormParam("password") String password, @FormParam("manager") String manager){
		Debug.logln(personID + ":" + firstname + ":" + surname + ":" + password + ":" + manager);
		String salt= "";
		String hashedpass ="";
		try {
			salt = Security.getSalt();
			hashedpass = Security.getSHA512(password, salt);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Teacher t = new Teacher(Integer.parseInt(personID), firstname, surname, hashedpass, salt,manager != null); //manager!=null -> manager checkbox was checked.
		GradesDB gradesDB = new GradesDB();
		gradesDB.addTeacher(t);
		
		HTMLGenerator response = new HTMLGenerator(2);
		response.addLine("<h1> Teacher succesfully added to database. </h1>");
		
		
		gradesDB.closeConnection();		
		return response.getHTML();
	}
	
}
