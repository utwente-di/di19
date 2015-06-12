package nl.utwente.di.gradeManager.pages;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import nl.utwente.di.gradeManager.db.GradesDB;
import nl.utwente.di.gradeManager.model.*;
import nl.utwente.di.gradeManager.style.Style;

@Path("/view")
public class View {
	//serves as a helper class for testing gradesDB
	//as well as viewing information in database.
	
	@GET
	@Path("students")
	@Produces(MediaType.TEXT_HTML)
	public String showStudents(){
		int depth = 2;
		GradesDB gradesDB = new GradesDB();
		String response = "<html> <head> " + Style.generateCSSLink(depth) + "</head> <body> ";
		response += "<h1> All students in database : </h1> </br>";
		response += "<table> <tr> <th> Studentid </th> <th> Firstname </th> <th> Surname </th> </tr>";
		for (Student s : gradesDB.getStudents()){
			response += "<tr> <td> " + s.getPersonID() + " </td> <td> " + s.getFirstname() + " </td> <td> " + s.getSurname() + " </td> </tr> ";
		}
		response += "</table>";
		gradesDB.closeConnection();
		return response;
	}
	
	@GET
	@Path("teachers")
	@Produces(MediaType.TEXT_HTML)
	public String showTeachers(){
		int depth = 2;
		GradesDB gradesDB = new GradesDB();
		String response = "<html> <head> " + Style.generateCSSLink(depth) + "</head> <body> ";
		response += "<h1> All teachers in database : </h1> </br>";
		response += "<table> <tr> <th> Teacherid </th> <th> Firstname </th> <th> Surname </th> <th> Is manager </th> </tr>";
		for (Teacher t : gradesDB.getTeachers()){
			response += "<tr> <td> " + t.getPersonID() + " </td> <td> " + t.getFirstname() + " </td> <td> " + t.getSurname() + "</td> <td> " + t.isManager() + " </td> </tr> ";
		}
		response += "</table>";
		gradesDB.closeConnection();
		return response;
	}
	
	@GET
	@Path("modulesforstudent/{studentid}")
	@Produces(MediaType.TEXT_HTML)
	public String showModulesForStudent(@PathParam("studentid") String argStudentid){
		int depth = 3;
		GradesDB gradesDB = new GradesDB();
		Student student = gradesDB.getStudent(Integer.parseInt(argStudentid));
		List<Module> modules = gradesDB.getModulesForStudent(Integer.parseInt(argStudentid));
				
		String response = "<html> <head> " + Style.generateCSSLink(depth) + "</head> <body> ";
		response += "<h1> All Modules for student : " + student.getFirstname() + " " + student.getSurname() + "</h1> </br>";
		response += "<table> <tr> <th> Modulecode </th> <th> Year </th> <th> Name </th> </tr>";
		for(Module m : modules){
			response += "<tr> <td> " + m.getModulecode() + " </td> <td> " + m.getYear() + " </td> <td> " + m.getName() + " </td> </tr>";
		}
		response += "</table>";
		gradesDB.closeConnection();
		return response;
	}
	
	@GET
	@Path("coursesformodule/{moduleid}")
	@Produces(MediaType.TEXT_HTML)
	public String showCoursesForModule(@PathParam("moduleid") String argModuleid){
		
		int depth = 3;
		GradesDB gradesDB = new GradesDB();
		Module module = gradesDB.getModule(Integer.parseInt(argModuleid));
		List<Course> courses = gradesDB.getCoursesForModule(Integer.parseInt(argModuleid));
				
		String response = "<html> <head> " + Style.generateCSSLink(depth) + "</head> <body> ";
		response += "<h1> All Courses for module : " + module.getName() + " : " + module.getModulecode() + "</h1> </br>";
		response += "<table> <tr> <th> coursecode </th> <th> name </th> <th> weight </th> <th> year </th> </tr>";
		for(Course c : courses){
			response += "<tr> <td> " + c.getCode() + " </td> <td> " + c.getName() + " </td> <td> " + c.getWeight() + " </td> <td> " + c.getYear() + "</td> </tr>";
		}
		response += "</table>";
		gradesDB.closeConnection();
		return response;
	}
	
	@GET
	@Path("assignmentsforcourse/{courseid}/{courseyear}")
	@Produces(MediaType.TEXT_HTML)
	public String showAssignmentsForCourse(@PathParam("courseid") String argCourseid, @PathParam("courseyear") String argCourseyear){
		
		int depth = 4;
		GradesDB gradesDB = new GradesDB();
		Course course = gradesDB.getCourse(Integer.parseInt(argCourseid),Integer.parseInt(argCourseyear));
		List<Assignment> assignments = gradesDB.getAssignmentsForCourse(Integer.parseInt(argCourseid),Integer.parseInt(argCourseyear));
				
		String response = "<html> <head> " + Style.generateCSSLink(depth) + "</head> <body> ";
		response += "<h1> All assignments for the course : " + course.getName() + "</h1> </br>";
		response += "<table> <tr> <th> id </th> <th> name </th> <th> weight </th> <th> graded </th> <th> minimumresult </th> </tr>";
		for(Assignment a : assignments){
			response+= "<tr> <td> " + a.getAssignmentID() + " </td> <td> " + a.getName() + " </td> <td> " + a.getWeight() + " </td> <td> " + a.getGraded() + " </td> <td> " + a.getMinimumresult() + "</td> </tr>";
		}
		response += "</table>";
		gradesDB.closeConnection();
		return response;
		
	}
	
	@GET
	@Path("occasionsforassignment/{assignmentid}")
	@Produces(MediaType.TEXT_HTML)
	public String showOccasionsForAssignment(@PathParam("assignmentid") String argAssignmentid){
		
		int depth = 3;
		GradesDB gradesDB = new GradesDB();
		Assignment assignment = gradesDB.getAssignment(Integer.parseInt(argAssignmentid));
		List<AssignmentOccasion> assignmentoccasions = gradesDB.getOccasionsForAssignment(Integer.parseInt(argAssignmentid));
				
		String response = "<html> <head> " + Style.generateCSSLink(depth) + "</head> <body> ";
		response += "<h1> All dates for assignment : " + assignment.getName() + "</h1> </br>";
		for(AssignmentOccasion ao : assignmentoccasions){
			response+= "<ul> <li> " + ao.getOccasiondate() + " </li> </ul> ";
		}
		gradesDB.closeConnection();
		return response;
	}
	
	
	
}
