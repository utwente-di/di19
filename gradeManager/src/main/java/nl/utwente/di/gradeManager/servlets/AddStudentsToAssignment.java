package nl.utwente.di.gradeManager.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.utwente.di.gradeManager.db.GradesDB;
import nl.utwente.di.gradeManager.debug.Debug;
import nl.utwente.di.gradeManager.model.Assignment;
import nl.utwente.di.gradeManager.model.AssignmentOccasion;
import nl.utwente.di.gradeManager.model.AssignmentResult;
import nl.utwente.di.gradeManager.model.Student;

public class AddStudentsToAssignment extends HttpServlet{
	
	private List<Student> allestudentsvancourse;
	private List<AssignmentResult> results;
			
	@SuppressWarnings("null")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		//Het assignmentID uit de parameters halen
		Integer assignmentid = Integer.parseInt(request.getParameter("assignmentid"));
		Integer courseid = Integer.parseInt(request.getParameter("courseid"));
		Integer courseyear = Integer.parseInt(request.getParameter("courseyear"));
		
		List<Integer> templist = new ArrayList<Integer>();
		List<Student> toevoegen = new ArrayList<Student>();
		
		GradesDB db = new GradesDB();
		try{
			results = db.getResultsForAssignment(assignmentid);
			allestudentsvancourse = db.getStudentsForCourse(courseid, courseyear);
			for(int i = 0; i < results.size(); i++){
				templist.add(results.get(i).getStudentid());
			}
			for(int i = 0; i < allestudentsvancourse.size(); i++){
				if(!templist.contains(allestudentsvancourse.get(i))){
					toevoegen.add(allestudentsvancourse.get(i));
				}
			}
			
			BigDecimal cijfer = new BigDecimal(1);
			List<AssignmentOccasion> occasion = db.getOccasionsForAssignment(assignmentid);
			for(int i = 0; i <toevoegen.size(); i++){
				AssignmentResult assres = new AssignmentResult(occasion.get(0).getOccasionid(), Integer.parseInt(toevoegen.get(i).getPersonID().substring(1)), occasion.get(0).getOccasiondate(), cijfer, assignmentid);
				db.addAssignmentResult(assres);
			}
			
		}
		finally{
			db.closeConnection();
		}
		
		response.sendRedirect("DocentTabel");
	}

}
