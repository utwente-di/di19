package nl.utwente.di.gradeManager.model;

import java.math.BigDecimal;



/**
 * Class for assignments.
 *
 */
public class Assignment {

	private int assignmentID; //PK unique
	private int coursecode; 
	private int courseyear; 
	private String name;
	private boolean isGraded;
	private int weight;
	private BigDecimal minimumresult;
	
	/**
	 * Constructs an assignment
	 * @param argAssignmentid The id for the assignment.
	 * @param argCoursecode The course code of the course of which the assignment is a part.
	 * @param argCourseyear The course year in which the assignment is held.
	 * @param argName The name of the assignment.
	 * @param argIsgradedassignment Whether the assignment is graded (true) or signed off (false)
	 * @param argWeight The weight of the assignment used for computing the average result of a course.
	 * @param argMinimumresult The minimum expected result for the assignment for passing it.
	 */
	public Assignment(int argAssignmentid, int argCoursecode, int argCourseyear, String argName, boolean argIsgradedassignment, int argWeight, BigDecimal argMinimumresult){
		this.assignmentID = argAssignmentid;
		this.coursecode = argCoursecode;
		this.courseyear = argCourseyear;
		this.name = argName;
		this.isGraded = argIsgradedassignment;
		this.weight = argWeight;
		this.minimumresult = argMinimumresult;
	}
	
	/**
	 * Gets the assignment id of this assignment.
	 * @return The ID of the assignment.
	 */
	public int getAssignmentID(){
		return this.assignmentID;
	}
	
	/**
	 * Sets the id of this assignment.
	 * @param argAssignmentID The id of this assignment.
	 */
	public void setAssignmentID(int argAssignmentID){
		this.assignmentID = argAssignmentID;
	}
	
	public int getCourseCode(){
		return this.coursecode;
	}
	
	public void setCourseCode(int argCourseCode){
		this.coursecode = argCourseCode;
	}
	
	public int getCourseyear(){
		return this.courseyear;
	}
	
	public void setCourseyear(int argCourseyear){
		this.courseyear = argCourseyear;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String argName){
		this.name = argName;
	}
	
	public boolean getGraded(){
		return this.isGraded;
	}
	
	public void setGraded(boolean argGraded){
		this.isGraded = argGraded;
	}
	
	public int getWeight(){
		return this.weight;
	}
	
	public void setWeight(int argWeigth){
		this.weight = argWeigth;
	}
	
	public BigDecimal getMinimumresult(){
		return this.minimumresult;
	}
	
	public void setMinimumresult(BigDecimal argMinimumresult){
		this.minimumresult = argMinimumresult;
	}
	
	
	
	
	
	
}
