package nl.utwente.di.gradeManager.model;

/**
 * 
 * Class for courses.
 */
public class Course extends SuperCourse {
	private int year; 
	
	/**
	 * Default constructor for Course
	 * @param code  	This course's code
	 * @param year 		This course's year
	 */
	public Course(int argCode, int argYear, String argName, int argWeight){
		super(argCode,argName,argWeight);
		this.year = argYear;
	}
	

	/**
	 * 
	 * @return this course's year
	 */
	public int getYear(){
		return year;
	}
	
	/**
	 * 
	 * @param year  this course's year
	 */
	public void setYear(int year){
		this.year = year;
	}
	
}
