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
import nl.utwente.di.gradeManager.db.LoginDB;
import nl.utwente.di.gradeManager.model.Assignment;
import nl.utwente.di.gradeManager.model.AssignmentResult;
import nl.utwente.di.gradeManager.model.Course;
import nl.utwente.di.gradeManager.model.Module;
import nl.utwente.di.gradeManager.model.Teacher;

public class DocentTabel extends HttpServlet {
	
	private final String jsp_address = "docent.jsp";
	private List<AssignmentResult> results;
	private List<Assignment> assignments;
	private Module module;
	private List<Course> courses;
	private List<Module> docentmodules;
	private Teacher docent;
	private StudentModules naam;
	
	protected void setInfo(int personID, int moduleID, int moduleYear){
		//Database connectie aanmaken
		GradesDB gradesDB = new GradesDB();
		
		module = gradesDB.getModule(moduleID, moduleYear);
		
		courses = gradesDB.getCoursesForModule(moduleID);
		
		docent = gradesDB.getTeacher(personID);
		
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
		
		List<AssignmentResult> resultList = new ArrayList<AssignmentResult>();
		for(int i = 0; i < assignments.size(); i++){
			results = gradesDB.getResultsForAssignment(assignments.get(i).getAssignmentID());
			if (results != null){
				for (int j = 0; j < results.size(); j++) {
					if(results.get(j) != null){
						resultList.add(results.get(j));
					}
				}
			}
		}
		
		naam = new StudentModules(personID, docentmodules);
		
		//Database connectie sluiten
		gradesDB.closeConnection();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//session opvragen en SID instellen
		HttpSession session = request.getSession(false);
		String sessionid = session.getId();
		LoginDB logindb = new LoginDB();
		Integer SID = Integer.parseInt(logindb.getLoggedInPersonid(sessionid).substring(1));
		logindb.closeConnection();
		
		Integer moduleid;
		Integer moduleyear;
		//Als alleen gradeManager/docenttabel zonder parameters wordt aangeroepen, automatisch doorsturen naar de meest recente module. Anders gewoon de gespecifeerde parameters volgen.	
		if (request.getParameter("moduleid") == null || request.getParameter("moduleyear") == null){
			GradesDB gradesDB = new GradesDB();
			docentmodules = gradesDB.getModulesForDocent(SID);
			moduleid = docentmodules.get(0).getModulecode();
			moduleyear = docentmodules.get(0).getYear();
			gradesDB.closeConnection();
		}else{
			moduleid = Integer.parseInt(request.getParameter("moduleid"));
			moduleyear = Integer.parseInt(request.getParameter("moduleyear"));
		}
		
		//SetInfo aanroepen zodat alle gegevens uit de database getrokken worden en de beans gevuld kunnen worden
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
			
		if (results != null) {
				List<AssignmentResult> resList = new ArrayList<AssignmentResult>();
				for (int j = 0; j < results.size(); j++) {
					if (results.get(j) != null)
						resList.add(results.get(j));
			}
				
			StudentAssignments bean4 = new StudentAssignments("Dit is een resultaat", resList);
			request.setAttribute("resultstoShow", bean4);
		}

			request.setAttribute("docent", docent);
			
			request.setAttribute("docentModules", naam);
		
		//Response teruggeven en sturen naar de juiste jsp pagina.
		RequestDispatcher dispatcher = request.getRequestDispatcher(jsp_address);
		dispatcher.forward(request, response);
	}
}
