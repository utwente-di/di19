package nl.utwente.di.gradeManager.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.utwente.di.gradeManager.db.GradesDB;
import nl.utwente.di.gradeManager.helpers.Security;
import nl.utwente.di.gradeManager.model.Student;

public class MakeStudent {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, NoSuchAlgorithmException{
		Integer studentid = Integer.parseInt(request.getParameter("studentid"));
		String firstname = request.getParameter("firstname");
		String surname = request.getParameter("surname");
		String password = request.getParameter("password");
		String salt = Security.getSalt();
		String terugnaar = request.getParameter("terugnaar");
		Student newStudent = new Student(studentid, firstname, surname, Security.getSHA512(password, salt), salt);
		GradesDB gradesDB = new GradesDB();
		try {
			gradesDB.addStudent(newStudent);
		}finally{
			gradesDB.closeConnection();
		}
		String url = "Manager?" + terugnaar;
		response.sendRedirect(url);
	}

}
