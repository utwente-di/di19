package nl.utwente.di.gradeManager.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.utwente.di.gradeManager.db.GradesDB;
import nl.utwente.di.gradeManager.model.Assignment;
import nl.utwente.di.gradeManager.model.AssignmentOccasion;
import nl.utwente.di.gradeManager.model.AssignmentResult;
import nl.utwente.di.gradeManager.model.Course;
import nl.utwente.di.gradeManager.model.Module;
import nl.utwente.di.gradeManager.model.ModuleResult;


public class Resulttable extends HttpServlet {
	
	private final String jsp_address = "Student2.jsp";
	private List<Course> courses;
	private List<Assignment> assignments;
	private List<AssignmentResult> occasions;
	private Module module;
	private ModuleResult moduleResult;
	
	protected void setModule(int moduleID){
		GradesDB gradesDB = new GradesDB();
		module = gradesDB.getModule(moduleID);
	}
	
	protected void setCourses(int moduleID) {
		GradesDB gradesDB = new GradesDB();
		courses = gradesDB.getCoursesForModule(moduleID);
		gradesDB.closeConnection();
	}
	
	protected void setAssignments() {
		GradesDB gradesDB = new GradesDB();
		List<Assignment> assignmentList = new ArrayList<Assignment>();
		for(int i = 0; i < courses.size(); i++){
			assignments = gradesDB.getAssignmentsForCourse(courses.get(i).getCode(), courses.get(i).getYear());
			if (assignments != null){
				for (int j = 0; j < assignments.size(); j++) {
					if(assignments.get(j) != null){
						assignmentList.add(assignments.get(j));
					}
				}
			}
		}
		gradesDB.closeConnection();
		assignments = assignmentList;
	}
	
	protected void setResults(int personID){
		GradesDB gradesDB = new GradesDB();
		List<AssignmentResult> resultList = new ArrayList<AssignmentResult>();
		for(int i = 0; i < assignments.size(); i++){
			occasions = gradesDB.getResultsForAssignmentAndStudent(personID, assignments.get(i).getAssignmentID());
			if (occasions != null){
				for (int j = 0; j < occasions.size(); j++) {
					if(occasions.get(j) != null){
						resultList.add(occasions.get(j));
					}
				}
			}
		}
		gradesDB.closeConnection();
		occasions = resultList;
	}
	
	protected void setModuleResult(int personID){
		GradesDB gradesDB = new GradesDB();
		moduleResult = gradesDB.getModuleResult(personID,module.getModulecode(),module.getYear());
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (courses != null) {
			List<Course> coursesList = new ArrayList<Course>();
			for (int i = 0; i < courses.size(); i++) {
				if (courses.get(i) != null)
					coursesList.add(courses.get(i));
			}
			
			//String SID = request.getAttribute("studentID").toString();
			String SID = "Tim";
			StudentCourses bean = new StudentCourses(SID, coursesList);
			request.setAttribute("coursestoShow", bean);
		}

		if (assignments != null) {
			List<Assignment> assList = new ArrayList<Assignment>();
			for (int j = 0; j < assignments.size(); j++) {
				if (assignments.get(j) != null)
					assList.add(assignments.get(j));
			}
		
			
			
			CourseAssignments bean2 = new CourseAssignments("Dit is een vak", assList);
			request.setAttribute("assignmentstoShow", bean2);
		}
		
			request.setAttribute("moduletoShow", module);
			
		if (occasions != null) {
				List<AssignmentResult> resList = new ArrayList<AssignmentResult>();
				for (int j = 0; j < occasions.size(); j++) {
					if (occasions.get(j) != null)
						resList.add(occasions.get(j));
			}
				
			StudentAssignments bean4 = new StudentAssignments("Dit is een resultaat", resList);
			request.setAttribute("resultstoShow", bean4);
		}
		if (moduleResult != null) {
			request.setAttribute("moduleresulttoShow", moduleResult);
		}
		
			RequestDispatcher dispatcher = request.getRequestDispatcher(jsp_address);
			dispatcher.forward(request, response);
			
		}
	}
