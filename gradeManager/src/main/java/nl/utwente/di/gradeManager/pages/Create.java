package nl.utwente.di.gradeManager.pages;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import nl.utwente.di.gradeManager.db.GradesDB;
import nl.utwente.di.gradeManager.debug.Debug;
import nl.utwente.di.gradeManager.helpers.*;
import nl.utwente.di.gradeManager.model.Student;
import nl.utwente.di.gradeManager.model.Teacher;

@Path("/create")
public class Create {

	@GET
	@Path("students")
	@Produces
	public String showStudent(){
		HTMLGenerator html = new HTMLGenerator(2);
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
	@Path("students")
	@Produces(MediaType.TEXT_HTML)
	public String createStudent(@FormParam("studentid") String personID, @FormParam("firstname") String firstname, @FormParam("surname") String surname, @FormParam("password") String password){
		Debug.logln(personID + ":" + firstname + ":" + surname + ":" + password);
		Student s = new Student(Integer.parseInt(personID), firstname, surname, password);
		GradesDB gradesDB = new GradesDB();
		gradesDB.addStudent(s);
		
		HTMLGenerator response = new HTMLGenerator(2);
		response.addLine("<h1> Student succesfully added to database. </h1>");
		
		
		gradesDB.closeConnection();		
		return response.getHTML();
	}
	
	@GET
	@Path("teachers")
	@Produces
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
	@Path("teachers")
	@Produces(MediaType.TEXT_HTML)
	public String createTeacher(@FormParam("teacherid") String personID, @FormParam("firstname") String firstname, @FormParam("surname") String surname, @FormParam("password") String password, @FormParam("manager") String manager){
		Debug.logln(personID + ":" + firstname + ":" + surname + ":" + password + ":" + manager);
		Teacher t = new Teacher(Integer.parseInt(personID), firstname, surname, password,manager != null); //manager!=null -> manager checkbox was checked.
		GradesDB gradesDB = new GradesDB();
		gradesDB.addTeacher(t);
		
		HTMLGenerator response = new HTMLGenerator(2);
		response.addLine("<h1> Teacher succesfully added to database. </h1>");
		
		
		gradesDB.closeConnection();		
		return response.getHTML();
	}
	
	
}
