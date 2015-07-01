package nl.utwente.di.gradeManager.pages.view;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nl.utwente.di.gradeManager.db.GradesDB;
import nl.utwente.di.gradeManager.model.Course;
import nl.utwente.di.gradeManager.model.Module;

@Path("coursesformodule")
public class CoursesForModule {
	@GET
	@Path("{moduleid}/{moduleyear}")
	@Produces(MediaType.TEXT_HTML)
	/**
	 * Shows the courses which are part of a given module.
	 * @param argModuleid The identifier of the module.
	 * @param argModuleYear The year in which the module is given.
	 * @return A HTML page containing information on what courses are in the module.
	 */
	public String showCoursesForModule(@PathParam("moduleid") String argModuleid, @PathParam("moduleyear") String argModuleYear){
		
		GradesDB gradesDB = new GradesDB();
		Module module = gradesDB.getModule(Integer.parseInt(argModuleid), Integer.parseInt(argModuleYear) );
		List<Course> courses = gradesDB.getCoursesForModule(Integer.parseInt(argModuleid));
				
		String response = ""; //"<html> <head> " + Style.generateCSSLink(depth) + "</head> <body> ";
		response += "<h1> All Courses for module : " + module.getName() + " : " + module.getModulecode() + "</h1> </br>";
		response += "<table> <tr> <th> coursecode </th> <th> name </th> <th> weight </th> <th> year </th> </tr>";
		for(Course c : courses){
			response += "<tr> <td> " + c.getCourseCode() + " </td> <td> " + c.getName() + " </td> <td> " + c.getWeight() + " </td> <td> " + c.getYear() + "</td> </tr>";
		}
		response += "</table>";
		gradesDB.closeConnection();
		return response;
	}
}
