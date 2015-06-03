package nl.utwente.di.gradeManager.debug;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * class for helping with debugging
 * by making use of time. 
 */
public class Debug {

	/**
	 * Logs a message to be used for debugging.
	 * @param out the message to be logged
	 */	
	public static void logln(String out){
		//generate a way of displaying the time
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		//display the time, aswell as the message.
		System.out.println("Debug: " + "(" + dateFormat.format(date) + ") " + out);
	}
	
	
}
