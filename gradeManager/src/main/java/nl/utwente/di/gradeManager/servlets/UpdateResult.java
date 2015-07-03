package nl.utwente.di.gradeManager.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.utwente.di.gradeManager.db.GradesDB;
import nl.utwente.di.gradeManager.debug.Debug;
import nl.utwente.di.gradeManager.model.AssignmentResult;

public class UpdateResult extends HttpServlet{
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Double nieuwcijfer = Double.parseDouble(request.getParameter("nieuwcijfer"));
		Integer studentid = Integer.parseInt(request.getParameter("studentid"));
		Integer occasionid = Integer.parseInt(request.getParameter("occasionid"));
		Date occasiondate = null;
		try {
			occasiondate = new SimpleDateFormat("YYYY-mm-dd").parse(request.getParameter("occasiondate"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Integer assignmentid = Integer.parseInt(request.getParameter("assignmentid"));
		String terugnaar = request.getParameter("terugnaar");
		BigDecimal BDnieuwcijfer = new BigDecimal(nieuwcijfer);
		
		AssignmentResult nieuwresultaat = new AssignmentResult(occasionid, studentid, occasiondate, BDnieuwcijfer, assignmentid);
		
		Debug.logln("cijfer:" + nieuwcijfer + " sid:" + studentid + " ocid:" + occasionid  + " date:" + occasiondate);
		GradesDB gradesDB = new GradesDB();
		try{
			gradesDB.modifyAssignmentResult(occasionid, studentid, nieuwresultaat);
		}finally{
			gradesDB.closeConnection();
		}
		
		String url = "DocentTabel?" + terugnaar + "#Toets1";
		response.sendRedirect(url);
	}
}
