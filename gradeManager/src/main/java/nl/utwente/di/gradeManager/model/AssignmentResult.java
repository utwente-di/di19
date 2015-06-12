package nl.utwente.di.gradeManager.model;

import java.util.Date;

/**
 * Class for results of assignments.
 * @author Frank
 *
 */

public class AssignmentResult{
	
	private int occasionid; 	//PK INT
	private int studentid; 	//PK INT
	private Date occasiondate;
	private float result;
	
	/**
	 * Constructs the result of an assignment for a student on a date.
	 * @param argAssignmentID The ID of the assignment.
	 * @param argOccasionDate The date on which the assignment occurred.
	 * @param argStudentID The student ID of the student who made the assignment.
	 * @param argResult The result of the made assignment.
	 */
	public AssignmentResult(int argOccasionid,int argStudentid,Date argOccasionDate, float argResult){
		this.occasionid = argOccasionid;
		this.studentid = argStudentid;
		this.occasiondate = argOccasionDate;
		this.result = argResult;
	}
	
	/**
	 * Gets the ID of the occasion.
	 * @return The ID of the occasion.
	 */
	public int getOccasionid(){
		return this.occasionid;
	}
	
	/**
	 * Sets the ID of the occasion.
	 * @param argAssignmentID The ID of the occasion.
	 */
	public void setOccasionid(int argOccasionid){
		this.occasionid = argOccasionid;
	}
	
	/**
	 * Gets the date on which the assignment occurred.
	 * @return The date on which the assignment occurred.
	 */
	public Date getOccasiondate(){
		return this.occasiondate;
	}
	
	/**
	 * Sets the date on which the assignment occurred
	 * @param argOccasionDate The date on which the assignment occurred.
	 */
	public void setOccasiondate(Date argOccasiondate){
		this.occasiondate = argOccasiondate;
	}
	
	/**
	 * Gets the ID of the student for this result.
	 * @return The ID of the student.
	 */
	public int getStudentid(){
		return this.studentid;
	}
	
	/**
	 * Sets the ID of the student for this result.
	 * @param argStudentID The ID of the student for this result.
	 */
	public void setStudentid(int argStudentid){
		this.studentid = argStudentid;
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
