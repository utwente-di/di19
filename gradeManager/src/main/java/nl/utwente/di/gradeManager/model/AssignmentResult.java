package nl.utwente.di.gradeManager.model;

import java.util.Date;

/**
 * Class for results of assignments.
 * @author Frank
 *
 */

public class AssignmentResult{
	
	private int assignmentID; 	//PK INT
	private Date occasionDate; //PK DATE
	private int studentID; 	//PK INT
	private float result;
	
	/**
	 * Constructs the result of an assignment for a student on a date.
	 * @param argAssignmentID The ID of the assignment.
	 * @param argOccasionDate The date on which the assignment occurred.
	 * @param argStudentID The student ID of the student who made the assignment.
	 * @param argResult The result of the made assignment.
	 */
	public AssignmentResult(int argAssignmentID, Date argOccasionDate, int argStudentID, float argResult){
		this.assignmentID = argAssignmentID;
		this.occasionDate = argOccasionDate;
		this.studentID = argStudentID;
		this.result = argResult;		
	}
	
	/**
	 * Gets the ID of the assignment.
	 * @return The ID of the assignment.
	 */
	public int getAssignmentID(){
		return this.assignmentID;
	}
	
	/**
	 * Sets the ID of the assignment.
	 * @param argAssignmentID The ID of the assignment.
	 */
	public void setAssignmentID(int argAssignmentID){
		this.assignmentID = argAssignmentID;
	}
	
	/**
	 * Gets the date on which the assignment occurred.
	 * @return The date on which the assignment occurred.
	 */
	public Date getOccasionDate(){
		return this.occasionDate;
	}
	
	/**
	 * Sets the date on which the assignment occurred
	 * @param argOccasionDate The date on which the assignment occurred.
	 */
	public void setOccasionDate(Date argOccasionDate){
		this.occasionDate = argOccasionDate;
	}
	
	/**
	 * Gets the ID of the student for this result.
	 * @return The ID of the student.
	 */
	public int getStudentID(){
		return this.studentID;
	}
	
	/**
	 * Sets the ID of the student for this result.
	 * @param argStudentID The ID of the student for this result.
	 */
	public void setStudentID(int argStudentID){
		this.studentID = argStudentID;
	}
	
	/**
	 * Gets the result of this assignment.
	 * @return The result of this assignment.
	 */
	public float getResult(){
		return this.result;
	}
	
	/**
	 * Sets the result of this assignment.
	 * @param argResult The result of this assignment.
	 */
	public void setResult(float argResult){
		this.result = argResult;
	}
}
