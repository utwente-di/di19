package nl.utwente.di.gradeManager.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Resulttable extends HttpServlet {
	
	private final String jsp_address = "/WEB-INF/Student2.jsp";
	private StudentCourses[] courses;
	private String[] courseIDs;
	
	
	protected void setCourses(String[] courseIDs) {
		this.courseIDs = courseIDs;
		courses = new StudentCourses[courseIDs.length];
		for (int i = 0; i < courses.length; i++) {
			courses[i] = QueryvanFrank.getCourse(courseIDs[i]);
		}
	}
	
	
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			RequestDispatcher dispatcher = request.getRequestDispatcher(jsp_address);
			dispatcher.forward(request, response);
			
		}
}
