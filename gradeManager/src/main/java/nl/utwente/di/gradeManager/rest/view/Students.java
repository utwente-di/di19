package nl.utwente.di.gradeManager.rest.view;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nl.utwente.di.gradeManager.db.GradesDB;
import nl.utwente.di.gradeManager.helpers.HTMLGenerator;
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
		HTMLGenerator html = new HTMLGenerator(2, true);
		html.addLine("<h1> All students in database : </h1> </br>");
		html.addLine("<table> <tr> <th> Studentid </th> <th> Firstname </th> <th> Surname </th> </tr>");
		for (Student s : gradesDB.getStudents()){
			html.addLine("<tr> <td> " + s.getPersonID() + " </td> <td> " + s.getFirstname() + " </td> <td> " + s.getSurname() + " </td> </tr> ");
		}
		html.addLine("</table>");
		gradesDB.closeConnection();
		return html.getHTML();
	}
	
}
