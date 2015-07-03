package nl.utwente.di.gradeManager.rest.view;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nl.utwente.di.gradeManager.db.GradesDB;
import nl.utwente.di.gradeManager.helpers.HTMLGenerator;
import nl.utwente.di.gradeManager.model.Teacher;

@Path("teachers")
public class Teachers {
	@GET
	@Produces(MediaType.TEXT_HTML)
	/**
	 * Shows the teachers
	 * @return A HTML page containing information on all the teachers.
	 */
	public String showTeachers(){
		GradesDB gradesDB = new GradesDB();
		HTMLGenerator html = new HTMLGenerator(2, true);
		html.addLine("<h1> All teachers in database : </h1> </br>");
		html.addLine("<table> <tr> <th> Teacherid </th> <th> Firstname </th> <th> Surname </th> <th> Is manager </th> </tr>");
		for (Teacher t : gradesDB.getTeachers()){
			html.addLine("<tr> <td> " + t.getPersonID() + " </td> <td> " + t.getFirstname() + " </td> <td> " + t.getSurname() + "</td> <td> " + t.isManager() + " </td> </tr> ");
		}
		html.addLine("</table>");
		gradesDB.closeConnection();
		return html.getHTML();
	}
	
}
