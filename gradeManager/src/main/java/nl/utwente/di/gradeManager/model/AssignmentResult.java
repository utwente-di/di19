package nl.utwente.di.gradeManager.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Class for results of assignments.
 * @author Frank
 *
 */

public class AssignmentResult{
	
	private int occasionid; 	//PK INT
	private int studentid; 	//PK INT
	private int assignmentid;
	private Date occasiondate;
	private BigDecimal result;
	
	/**
	 * Constructs the result of an assignment for a student on a date.
	 * @param argOccasionID The ID of the occasion.
	 * @param argOccasionDate The date on which the occasion occurred.
	 * @param argStudentID The student ID of the student who made the assignment.
	 * @param argResult The result of the made assignment.
	 * @param argAssignmentID The ID of the assignment.
	 */
	public AssignmentResult(int argOccasionid,int argStudentid,Date argOccasionDate, BigDecimal argResult, int argAssignmentid){
		this.occasionid = argOccasionid;
		this.studentid = argStudentid;
		this.occasiondate = argOccasionDate;
		this.result = argResult;
		this.assignmentid = argAssignmentid;
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
	public BigDecimal getResult(){
		return this.result;
	}
	
	/**
	 * Sets the result of this assignment.
	 * @param argResult The result of this assignment.
	 */
	public void setResult(BigDecimal argResult){
		this.result = argResult;
	}
	
	public int getAssignmentid(){
		return this.assignmentid;
	}
	
	public void setAssignmentid(int argAssignmentid){
		this.assignmentid = argAssignmentid;
	}
}
