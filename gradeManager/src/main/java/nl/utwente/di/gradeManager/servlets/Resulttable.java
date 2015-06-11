package nl.utwente.di.gradeManager.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.utwente.di.gradeManager.model.Assignment;
import nl.utwente.di.gradeManager.model.Course;


public class Resulttable extends HttpServlet {
	
	private final String jsp_address = "/WEB-INF/Student2.jsp";
	private Course[] courses;
	private Assignment[] assignments;
	
	protected void setCourses(String[] courseIDs) {
		courses = new Course[courseIDs.length];
		for (int i = 0; i < courses.length; i++) {
			courses[i] = QueryvanFrank.getCourse(courseIDs[i]);
		}
	}
	
	protected void setAssignments(String[] assignmentIDs) {
		assignments = new Assignment[assignmentIDs.length];
		for (int i = 0; i < courses.length; i++) {
			courses[i] = QueryvanFrank2.getCourse(assignmentIDs[i]);
		}
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (courses != null) {
			List<Course> coursesList = new ArrayList<Course>();
			for (int i = 0; i < courses.length; i++) {
				if (courses[i] != null)
					coursesList.add(courses[i]);
			}
			
			String SID = request.getAttribute("studentID").toString();
			StudentCourses bean = new StudentCourses(SID, coursesList);
			request.setAttribute("coursestoShow", bean);
			
		if (assignments != null) {
			List<Assignment> assignmentList = new ArrayList<Assignment>();
			for (int i = 0; i < assignments.length; i++) {
				if (assignments[i] != null)
					assignmentList.add(assignments[i]);
			}
			
			
			CourseAssignments bean2 = new CourseAssignments("Dit is een vak", assignmentList);
			request.setAttribute("assignmentstoShow", bean2);
			
		
		
			RequestDispatcher dispatcher = request.getRequestDispatcher(jsp_address);
			dispatcher.forward(request, response);
			
		}
	}
}
