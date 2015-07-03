package nl.utwente.di.gradeManager.rest.create;

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
import nl.utwente.di.gradeManager.model.Student;

@Path("students")
public class Students {

	@GET
	@Produces(MediaType.TEXT_HTML)
	/**
	 * Shows the form where information can be entered to create a student object.
	 * @return A HTML form for entering required information for creating a student.
	 */
	public String showStudent(){
		HTMLGenerator html = new HTMLGenerator(2, true);
		html.addLine("<h1> Create a student Object </h1>");
		html.addLine("<form action = \"students\" method =\"post\" accept-charset=\"utf-8\">");
		html.addLine("<label for=\"studentid\">studentid</label>");
		html.addLine("<input id=\"studentid\" name=\"studentid\" type=\"text\" required/>");
		html.addLine("<label for=\"firstname\">firstname</label>");
		html.addLine("<input id=\"firstname\" name=\"firstname\" type=\"text\" firstname required/>");
		html.addLine("<label for=\"surname\">surname</label>");
		html.addLine("<input id=\"surname\" name=\"surname\" type=\"text\" required/>");
		html.addLine("<label for=\"password\">password</label>");
		html.addLine("<input id=\"password\" name=\"password\" type=\"text\" required/>");
		html.addLine("<input type=\"submit\" value=\"Submit\">");
		html.addLine("</form>");		
		return html.getHTML();
		
		
	}
	
	@POST
	@Produces(MediaType.TEXT_HTML)
	/**
	 * Creates a student object in the database based on entered information.
	 * @param personID The identifier of the student.
	 * @param firstname The first name of the student.
	 * @param surname The last name of the student.
	 * @param password The password of the student.
	 * @return A message confirming that the student was added. 
	 */
	public String createStudent(@FormParam("studentid") String personID, @FormParam("firstname") String firstname, @FormParam("surname") String surname, @FormParam("password") String password){
		Debug.logln(personID + ":" + firstname + ":" + surname + ":" + password);
		Student s = null;
		String salt= "";
		String hashedpass ="";
		try {
			salt = Security.getSalt();
			hashedpass = Security.getSHA512(password, salt);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			s = new Student(Integer.parseInt(personID), firstname, surname, hashedpass, salt);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		GradesDB gradesDB = new GradesDB();
		try{gradesDB.addStudent(s);
		}finally{
			gradesDB.closeConnection();		
		}
		
		HTMLGenerator response = new HTMLGenerator(2);
		response.addLine("<h1> Student succesfully added to database. </h1>");
		return response.getHTML();
		
	}
	
}
