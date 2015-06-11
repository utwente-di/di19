package nl.utwente.di.gradeManager.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.utwente.di.gradeManager.model.Course;


public class Resulttable extends HttpServlet {
	
	private final String jsp_address = "/WEB-INF/Student2.jsp";
	private Course[] courses;
	
	protected void setCourses(String[] courseIDs) {
		courses = new Course[courseIDs.length];
		for (int i = 0; i < courses.length; i++) {
			courses[i] = QueryvanFrank.getCourse(courseIDs[i]);
		}
	}
	
	
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (courses != null) {
			List<Course> itemsList = new ArrayList<Course>();
			for (int i = 0; i < courses.length; i++) {
				if (courses[i] != null)
					itemsList.add(courses[i]);
			}
			
			String SID = request.getAttribute("studentID").toString();
			StudentCourses bean = new StudentCourses(SID, itemsList);
			request.setAttribute("coursestoShow", bean);
		
			RequestDispatcher dispatcher = request.getRequestDispatcher(jsp_address);
			dispatcher.forward(request, response);
			
		}
	}
}
