package nl.utwente.di.gradeManager.debug;

import java.time.LocalTime;

public class Debug {

	/**
	 * Logs a message to be used for debugging.
	 * @param out the message to be logged
	 */	
	public static void logln(String out){
		System.out.println("Debug: " + "(" + LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond() + "): " + out);
	}
	
	
}
