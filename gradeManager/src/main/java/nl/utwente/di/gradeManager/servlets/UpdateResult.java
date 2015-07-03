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
		//StudentID uit de parameters halen
		Integer studentid = Integer.parseInt(request.getParameter("studentid"));
		//OccasionID ophalen
		Integer occasionid = Integer.parseInt(request.getParameter("occasionid"));
		//Een date object initialiseren
		Date occasiondate = null;
		//Het assignmentID uit de parameters halen
		Integer assignmentid = Integer.parseInt(request.getParameter("assignmentid"));
		//De String die uit de getParameter komt in het goede format in het date object zetten
		try {
			occasiondate = new SimpleDateFormat("YYYY-mm-dd").parse(request.getParameter("occasiondate"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Parameter url ophalen, zodat deze servelt correct kan redirecten
		String terugnaar = request.getParameter("terugnaar");
		//Het nieuwe cijfer in een BigDecimal zetten
		BigDecimal BDnieuwcijfer = new BigDecimal(request.getParameter("nieuwcijfer"));
		
		//Nieuw assignmentResult object aanmaken om mee te geven aan de query voor de database
		AssignmentResult nieuwresultaat = new AssignmentResult(occasionid, studentid, occasiondate, BDnieuwcijfer, assignmentid);
		
		Debug.logln("cijfer:" + BDnieuwcijfer + " sid:" + studentid + " ocid:" + occasionid  + " date:" + occasiondate);
		
		//Database verbinding opzetten
		GradesDB gradesDB = new GradesDB();
		try{
			//Query voor het aanpassen van een resultaat uitvoeren
			gradesDB.modifyAssignmentResult(occasionid, studentid, nieuwresultaat);
		}finally{
			//Verbinding sluiten
			gradesDB.closeConnection();
		}
		
		//Correcte opmaak van de doorstuur url maken
		String url = "DocentTabel?" + terugnaar + "#Toets1";
		//Terugsturen naar de module waar het cijfer in zit
		response.sendRedirect(url);
	}
}
