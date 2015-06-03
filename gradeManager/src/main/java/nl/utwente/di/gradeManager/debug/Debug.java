package nl.utwente.di.gradeManager.debug;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Debug {

	/**
	 * Logs a message to be used for debugging.
	 * @param out the message to be logged
	 */	

	
	public static void logln(String out){
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		System.out.println("Debug: " + "(" + dateFormat.format(date) + ") " + out);
	}
	
	
}
