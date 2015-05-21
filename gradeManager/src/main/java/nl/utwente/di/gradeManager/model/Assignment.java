package nl.utwente.di.gradeManager.model;

import java.util.Date;

/**
 * Abstract class for assignments.
 *
 */
public abstract class Assignment {

	private String title;
	public Date date;
	
	/**
	 * Constructor for Assignment
	 * @param argTitle The title of the assignment.
	 * @param argDate The date of the assignment.
	 */
	public Assignment(String argTitle, Date argDate){
		this.title = argTitle;
		this.date = argDate;
	}
	
	/**
	 * 
	 * @return The title of the assignment.
	 */
	public String getTitle(){
		return title;
	}
	
	/**
	 * 
	 * @param argTitle The new title of the assignment.
	 */
	public void setTitle(String argTitle){
		this.title = argTitle;
	}
	
	/**
	 * 
	 * @return The date of the assignment.
	 */
	public Date getDate(){
		return this.date;
	}
	
	/**
	 * 
	 * @param argDate The new date of the assignment.
	 */
	public void setDate(Date argDate){
		this.date = argDate;
	}
	
}
