package nl.utwente.di.gradeManager.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.utwente.di.gradeManager.db.GradesDB;
import nl.utwente.di.gradeManager.model.Student;

public class ManagerServlet extends HttpServlet{
	
	private final String jsp_address = "Manager.jsp";
	private AllStudents students;
	
	
	
	
	protected void getStudents(){
		GradesDB gradesDB = new GradesDB();
		try {
			List<Student> studentlist = gradesDB.getStudents();
			students.setItems(studentlist);
			students.setTitle("Dit zijn alle studenten");
		}finally{
			gradesDB.closeConnection();
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		
		getStudents();
		request.setAttribute("students", students);
		RequestDispatcher dispatcher = request.getRequestDispatcher(jsp_address);
		dispatcher.forward(request, response);
	}
}
