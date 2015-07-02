package nl.utwente.di.gradeManager.rest.view;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nl.utwente.di.gradeManager.db.GradesDB;
import nl.utwente.di.gradeManager.model.Assignment;
import nl.utwente.di.gradeManager.model.AssignmentOccasion;

@Path("occasionsforassignment")
public class OccassionsForAssignment {
	@GET
	@Path("{assignmentid}")
	@Produces(MediaType.TEXT_HTML)
	/**
	 * Show the Occasions for a given assignment.
	 * @param argAssignmentid The identifier of the assignment.
	 * @return A HTML page containing information on the occasions of this assignment.
	 */
	public String showOccasionsForAssignment(@PathParam("assignmentid") String argAssignmentid){
		
		GradesDB gradesDB = new GradesDB();
		Assignment assignment = gradesDB.getAssignment(Integer.parseInt(argAssignmentid));
		List<AssignmentOccasion> assignmentoccasions = gradesDB.getOccasionsForAssignment(Integer.parseInt(argAssignmentid));
				
		String response = "";//"<html> <head> " + Style.generateCSSLink(depth) + "</head> <body> ";
		response += "<h1> All dates for assignment : " + assignment.getName() + "</h1> </br>";
		for(AssignmentOccasion ao : assignmentoccasions){
			response+= "<ul> <li> " + ao.getOccasiondate() + " </li> </ul> ";
		}
		gradesDB.closeConnection();
		return response;
	}
	
}
