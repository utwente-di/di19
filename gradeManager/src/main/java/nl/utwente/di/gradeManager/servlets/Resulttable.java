package nl.utwente.di.gradeManager.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nl.utwente.di.gradeManager.db.GradesDB;
import nl.utwente.di.gradeManager.model.Assignment;
import nl.utwente.di.gradeManager.model.AssignmentOccasion;
import nl.utwente.di.gradeManager.model.AssignmentResult;
import nl.utwente.di.gradeManager.model.Course;
import nl.utwente.di.gradeManager.model.Module;
import nl.utwente.di.gradeManager.model.ModuleResult;
import nl.utwente.di.gradeManager.model.Student;
import nl.utwente.di.gradeManager.db.LoginDB;


public class Resulttable extends HttpServlet {
	
	private final String jsp_address = "Student2.jsp";
	private List<Course> courses;
	private List<Assignment> assignments;
	private List<AssignmentResult> occasions;
	private Module module;
	private ModuleResult moduleResult;
	private Student student;
	private List<Module> studentmodules;
	StudentModules naam;
	
	protected void setInfo(int personID, int moduleID, int moduleYear){
		
		GradesDB gradesDB = new GradesDB();
		module = gradesDB.getModule(moduleID, moduleYear);
		
		courses = gradesDB.getCoursesForModule(moduleID);
		
		student = gradesDB.getStudent(personID);
		
		studentmodules = gradesDB.getModulesForStudent(personID);
		
		List<Assignment> assignmentList = new ArrayList<Assignment>();
		for(int i = 0; i < courses.size(); i++){
			assignments = gradesDB.getAssignmentsForCourse(courses.get(i).getCourseCode(), courses.get(i).getYear());
			if (assignments != null){
				for (int j = 0; j < assignments.size(); j++) {
					if(assignments.get(j) != null){
						assignmentList.add(assignments.get(j));
					}
				}
			}
		}
		assignments = assignmentList;
		
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
		occasions = resultList;
		
		naam = new StudentModules(personID, studentmodules);
		
		moduleResult = gradesDB.getModuleResult(personID, module.getModulecode(), module.getYear());
		
		gradesDB.closeConnection();
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Integer moduleid = Integer.parseInt(request.getParameter("moduleid"));
		Integer moduleyear = Integer.parseInt(request.getParameter("moduleyear"));

		HttpSession session = request.getSession(false);
		String sessionid = session.getId();
		LoginDB logindb = new LoginDB();
		Integer SID = Integer.parseInt(logindb.getLoggedInPersonid(sessionid));
		
		setInfo(SID, moduleid, moduleyear);
		
		if (courses != null) {
			List<Course> coursesList = new ArrayList<Course>();
			for (int i = 0; i < courses.size(); i++) {
				if (courses.get(i) != null)
					coursesList.add(courses.get(i));
			}
			
			
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
			request.setAttribute("student", student);
			
			request.setAttribute("studentModules", naam);
		
			RequestDispatcher dispatcher = request.getRequestDispatcher(jsp_address);
			dispatcher.forward(request, response);
			
		}
	}
