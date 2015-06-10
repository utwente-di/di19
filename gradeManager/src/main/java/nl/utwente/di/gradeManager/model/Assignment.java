package nl.utwente.di.gradeManager.model;



/**
 * Abstract class for assignments.
 *
 */
public class Assignment {

	private int assignmentID; //PK unique
	private int courseCode; 
	private int courseYear; 
	private String name;
	private boolean isGraded;
	private int weight;
	
	/**
	 * Constructor for Assignment
	 * @param argID		The ID of the assignment.
	 * @param argCourse The Course of the assignment.
	 * @param argName 	The Name of the assignment.
	 * @param argGraded Boolean whether the assignment is graded or not.
	 * @param argWeight The Weight of the assignment.
	 */
	public Assignment(int argID, int argCourseCode, int argCourseYear, String argname, boolean argGraded, int argWeight){
		this.assignmentID = argID;
		this.courseCode = argCourseCode;
		this.courseYear = argCourseYear;
		this.name = argname;
		this.isGraded = argGraded;
		this.weight = argWeight;
	}
	
	/**
	 * 
	 * @return	The ID of this assignment
	 */
	public int getID(){
		return assignmentID;
	}
	
	/**
	 * 
	 * @param ID	The ID of this assignment
	 */
	public void setID(int argID){
		this.assignmentID = argID;
	}
	
	/**
	 * Gets the course code of this assignment.
	 * @return The course code of this assignment.
	 */
	public int getCourseCode(){
		return this.courseCode;
	}
	
	/**
	 * Sets the course year of this assignment. 
	 * @param argCourseCode The course code of this assignment.
	 */
	public void setCourseCode(int argCourseCode){
		this.courseCode = argCourseCode;
	}
	
	/**
	 * Gets the course year of this assignment.
	 * @return The course year of this assignment..
	 */
	public int getCourseYear(){
		return this.courseYear;
	}
	
	/**
	 * Sets the course year of this assignment.
	 * @param argCourseYear The course year of this assignment.
	 */
	public void setCourseYear(int argCourseYear){
		this.courseYear = argCourseYear;
	}
	
	/**
	 * 
	 * @return	The name of this Assignment
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * 
	 * @param argName	The name of this assignment
	 */
	public void setName(String argName){
		this.name = argName;
	}
	
	/**
	 * 
	 * @return  True if assignment is graded, False if assignment has to be signed off
	 */
	public Boolean getGraded(){
		return isGraded;
	}
	
	/**
	 * 
	 * @param argGraded		Whether this assignment is graded or not
	 */
	public void setGraded(boolean argGraded){
		this.isGraded = argGraded;
	}
	
	/**
	 * 
	 * @return	The weight of this assignment
	 */
	public int getWeight(){
		return weight;
	}
	
	/**
	 * 
	 * @param argWeight		The weight of this assignment
	 */
	public void setWeight(int argWeight){
		this.weight = argWeight;
	}
}
