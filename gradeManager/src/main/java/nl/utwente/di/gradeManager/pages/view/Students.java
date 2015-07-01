package nl.utwente.di.gradeManager.pages.view;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nl.utwente.di.gradeManager.db.GradesDB;
import nl.utwente.di.gradeManager.model.Student;

@Path("students")
public class Students {

	@GET
	@Produces(MediaType.TEXT_HTML)
	/**
	 * Shows the students.
	 * @return A HTML page containing information on all the students.
	 */
	public String showStudents(){
		GradesDB gradesDB = new GradesDB();
		String response = "";//"<html> <head> " + Style.generateCSSLink(depth) + "</head> <body> ";
		response += "<h1> All students in database : </h1> </br>";
		response += "<table> <tr> <th> Studentid </th> <th> Firstname </th> <th> Surname </th> </tr>";
		for (Student s : gradesDB.getStudents()){
			response += "<tr> <td> " + s.getPersonID() + " </td> <td> " + s.getFirstname() + " </td> <td> " + s.getSurname() + " </td> </tr> ";
		}
		response += "</table>";
		gradesDB.closeConnection();
		return response;
	}
	
}
