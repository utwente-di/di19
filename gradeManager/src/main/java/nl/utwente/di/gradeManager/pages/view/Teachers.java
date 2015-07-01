package nl.utwente.di.gradeManager.pages.view;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nl.utwente.di.gradeManager.db.GradesDB;
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
		String response = "";//"<html> <head> " + Style.generateCSSLink(depth) + "</head> <body> ";
		response += "<h1> All teachers in database : </h1> </br>";
		response += "<table> <tr> <th> Teacherid </th> <th> Firstname </th> <th> Surname </th> <th> Is manager </th> </tr>";
		for (Teacher t : gradesDB.getTeachers()){
			response += "<tr> <td> " + t.getPersonID() + " </td> <td> " + t.getFirstname() + " </td> <td> " + t.getSurname() + "</td> <td> " + t.isManager() + " </td> </tr> ";
		}
		response += "</table>";
		gradesDB.closeConnection();
		return response;
	}
	
}
