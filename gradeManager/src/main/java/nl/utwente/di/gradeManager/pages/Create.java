package nl.utwente.di.gradeManager.pages;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import nl.utwente.di.gradeManager.db.GradesDB;
import nl.utwente.di.gradeManager.debug.Debug;
import nl.utwente.di.gradeManager.model.Student;
import nl.utwente.di.gradeManager.model.Teacher;
import nl.utwente.di.gradeManager.style.Style;

@Path("/create")
public class Create {

	@GET
	@Path("students")
	@Produces
	public String showStudent(){
		int depth = 2;
		String HTML = ""
				+ "<html>"
				+ "<head> " + Style.generateCSSLink(depth) + "</head>"
				+ "<body>"
				+ "<h1> Create a student Object </h1>"
				+ "<form action = \"students\" method =\"post\" accept-charset=\"utf-8\">"
				+ "<label for=\"studentid\">studentid</label>"
				+ "<input id=\"studentid\" name=\"studentid\" type=\"text\" required/>" 
				+ "<label for=\"firstname\">firstname</label>"
				+ "<input id=\"firstname\" name=\"firstname\" type=\"text\" firstname required/>" 
				+ "<label for=\"surname\">surname</label>"
				+ "<input id=\"surname\" name=\"surname\" type=\"text\" required/>" 
				+ "<label for=\"password\">password</label>"
				+ "<input id=\"password\" name=\"password\" type=\"text\" required/>" 
				+ "<input type=\"submit\" value=\"Submit\">" 
				+ "</form>"
				+ "</body>"
				+ "</html>";
		
		return HTML;
		
		
	}
	
	@POST
	@Path("students")
	@Produces(MediaType.TEXT_HTML)
	public String createStudent(@FormParam("studentid") String personID, @FormParam("firstname") String firstname, @FormParam("surname") String surname, @FormParam("password") String password){
		int depth = 2;
		Debug.logln(personID + ":" + firstname + ":" + surname + ":" + password);
		Student s = new Student(Integer.parseInt(personID), firstname, surname, password);
		GradesDB gradesDB = new GradesDB();
		gradesDB.addStudent(s);
		
		String response = "<html> <head> " + Style.generateCSSLink(depth) + " </head> <h1> Student succesfully added to database. </h1> </html> ";
		
		
		gradesDB.closeConnection();		
		return response;
	}
	
	@GET
	@Path("teachers")
	@Produces
	public String showTeacher(){
		int depth = 2;
		String HTML = ""
				+ "<html>"
				+ "<head> " + Style.generateCSSLink(depth) + "</head>"
				+ "<body>"
				+ "<h1> Create a Teacher Object </h1>"
				+ "<form action = \"teachers\" method =\"post\" accept-charset=\"utf-8\">"
				+ "<label for=\"teacherid\">teacherid</label>"
				+ "<input id=\"teacherid\" name=\"teacherid\" type=\"text\" required/>" 
				+ "<label for=\"firstname\">firstname</label>"
				+ "<input id=\"firstname\" name=\"firstname\" type=\"text\" firstname required/>" 
				+ "<label for=\"surname\">surname</label>"
				+ "<input id=\"surname\" name=\"surname\" type=\"text\" required/>" 
				+ "<label for=\"password\">password</label>"
				+ "<input id=\"password\" name=\"password\" type=\"text\" required/>" 
				+ "<label for=\"manager\"> manager </label>"
				+ "<input id=\"manager\" name=\"manager\" type=\"checkbox\">"
				+ "</br>"
				+ "<input type=\"submit\" value=\"Submit\">" 
				+ "</form>"
				+ "</body>"
				+ "</html>";
		
		return HTML;
		
		
	}
	
	@POST
	@Path("teachers")
	@Produces(MediaType.TEXT_HTML)
	public String createTeacher(@FormParam("teacherid") String personID, @FormParam("firstname") String firstname, @FormParam("surname") String surname, @FormParam("password") String password, @FormParam("manager") String manager){
		int depth = 2;
		Debug.logln(personID + ":" + firstname + ":" + surname + ":" + password + ":" + manager);
		Teacher t = new Teacher(Integer.parseInt(personID), firstname, surname, password,manager != null); //manager!=null -> manager checkbox was checked.
		GradesDB gradesDB = new GradesDB();
		gradesDB.addTeacher(t);
		
		String response = "<html> <head> " + Style.generateCSSLink(depth) + " </head> <h1> Teacher succesfully added to database. </h1> </html> ";
		
		
		gradesDB.closeConnection();		
		return response;
	}
	
	
}
