package nl.utwente.di.gradeManager.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nl.utwente.di.gradeManager.db.GradesDB;
import nl.utwente.di.gradeManager.debug.Debug;
import nl.utwente.di.gradeManager.helpers.Security;
import nl.utwente.di.gradeManager.model.Student;

public class ImportStudent extends HttpServlet{
	
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		String firstname = "";
		String surname = "";
		String personID = "";
		String password = "";
		String salt= "";
		String hashedpass ="";

		
		HttpSession session = request.getSession(false);
		GradesDB db = new GradesDB();
		
		try{
			int i = 0;
			firstname = request.getParameter(i + "-firstname");
			while(firstname != null){
				surname = request.getParameter(i + "-surname");
				personID = request.getParameter(i + "-personID").substring(1);
				password = request.getParameter(i + "-password");
				
				i++;
				Debug.logln(firstname + " " + surname + " " + personID + " " + password);
				try {
					salt = Security.getSalt();
					hashedpass = Security.getSHA512(password, salt);
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Student student = new Student(Integer.parseInt(personID), firstname, surname, hashedpass, salt);
				
				db.addStudent(student);
			
				firstname = request.getParameter(i + "-firstname");		
			}
		}finally{
			db.closeConnection();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("login");
		try { rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
