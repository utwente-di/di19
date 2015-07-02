package nl.utwente.di.gradeManager.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.utwente.di.gradeManager.db.GradesDB;
import nl.utwente.di.gradeManager.debug.Debug;

public class UpdateResult extends HttpServlet{
	
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		Integer nieuwcijfer = Integer.parseInt(request.getParameter("nieuwcijfer"));
		String studentid = request.getParameter("studentid");
		String occasionid = request.getParameter("occasionid");
		String occasiondate = request.getParameter("occasiondate");
		String terugnaar = request.getParameter("terugnaar");
		
		Debug.logln("cijfer:" + nieuwcijfer + " sid:" + studentid + " ocid:" + occasionid + " ocdate:" + occasiondate);
		//GradesDB gradesDB = new GradesDB();
		//try{
		//	gradesDB.updateResult(studentid, occasionid, occasiondate, nieuwcijfer);
		//}finally{
		//	gradesDB.closeConnection();
		//}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("DocentTabel?" + terugnaar);
		//Daadwerkelijk alles doorsturen
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
