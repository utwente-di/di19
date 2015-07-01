package nl.utwente.di.gradeManager.pages.view;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nl.utwente.di.gradeManager.db.GradesDB;
import nl.utwente.di.gradeManager.model.Module;
import nl.utwente.di.gradeManager.model.Student;

@Path("modulesforstudent")
public class ModulesForStudents {

	@GET
	@Path("{studentid}")
	@Produces(MediaType.TEXT_HTML)
	/**
	 * Show the modules which a certain student is being taught.
	 * @param argStudentid The identifier of the student.
	 * @return A HTML page containing information on the modules which the student attends.
	 */
	public String showModulesForStudent(@PathParam("studentid") String argStudentid){
		GradesDB gradesDB = new GradesDB();
		Student student = gradesDB.getStudent(Integer.parseInt(argStudentid));
		List<Module> modules = gradesDB.getModulesForStudent(Integer.parseInt(argStudentid));
				
		String response = "";//"<html> <head> " + Style.generateCSSLink(depth) + "</head> <body> ";
		response += "<h1> All Modules for student : " + student.getFirstname() + " " + student.getSurname() + "</h1> </br>";
		response += "<table> <tr> <th> Modulecode </th> <th> Year </th> <th> Name </th> </tr>";
		for(Module m : modules){
			response += "<tr> <td> " + m.getModulecode() + " </td> <td> " + m.getYear() + " </td> <td> " + m.getName() + " </td> </tr>";
		}
		response += "</table>";
		gradesDB.closeConnection();
		return response;
	}
	
}
