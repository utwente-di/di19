package nl.utwente.di.gradeManager.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManagerCreateStudents extends HttpServlet{
	
	private final String jsp_address = "ManagerCreateStudents.jsp";	

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher(jsp_address);
		dispatcher.forward(request, response);
	}
}
