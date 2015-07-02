package nl.utwente.di.gradeManager.rest.view;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nl.utwente.di.gradeManager.db.GradesDB;
import nl.utwente.di.gradeManager.model.Assignment;
import nl.utwente.di.gradeManager.model.Course;

@Path("assignmentsforcourse")
public class AssignmentsForCourse {
	@GET
	@Path("{courseid}/{courseyear}")
	@Produces(MediaType.TEXT_HTML)
	/**
	 * Show the assignments which are part of a given course.
	 * @param argCourseid The identifier of the course.
	 * @param argCourseyear The year in which the course is given.
	 * @return A HTML page containing information on the assignments which are part of the given course.
	 */
	public String showAssignmentsForCourse(@PathParam("courseid") String argCourseid, @PathParam("courseyear") String argCourseyear){
		
		GradesDB gradesDB = new GradesDB();
		Course course = gradesDB.getCourse(Integer.parseInt(argCourseid),Integer.parseInt(argCourseyear));
		List<Assignment> assignments = gradesDB.getAssignmentsForCourse(Integer.parseInt(argCourseid),Integer.parseInt(argCourseyear));
				
		String response = "";//"<html> <head> " + Style.generateCSSLink(depth) + "</head> <body> ";
		response += "<h1> All assignments for the course : " + course.getName() + "</h1> </br>";
		response += "<table> <tr> <th> id </th> <th> name </th> <th> weight </th> <th> graded </th> <th> minimumresult </th> </tr>";
		for(Assignment a : assignments){
			response+= "<tr> <td> " + a.getAssignmentID() + " </td> <td> " + a.getName() + " </td> <td> " + a.getWeight() + " </td> <td> " + a.getGraded() + " </td> <td> " + a.getMinimumresult() + "</td> </tr>";
		}
		response += "</table>";
		gradesDB.closeConnection();
		return response;
		
	}
}
