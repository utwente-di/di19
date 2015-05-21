package nl.utwente.di.gradeManager.core;

import java.util.Date;


public class GradedAssignment extends Assignment {

	

	private float grade;
	private float weight;
	private float requiredGrade;
	
	/**
	 * Builds the graded assignment.
	 * @param argTitle The title of the assignment.
	 * @param argDate The date of the assignment.
	 */
	public GradedAssignment(String argTitle, Date argDate, float argWeight, float argRequiredGrade) {
		super(argTitle, argDate);
		this.date = argDate;
		this.weight = argWeight;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @return The grade of the assignment.
	 */
	public float getGrade(){
		return grade;
	}
	
	/**
	 * 
	 * @param argGrade The (new) grade for the assignment.
	 */
	public void setGrade(float argGrade){
		this.grade = argGrade;
	}
	
	/**
	 * 
	 * @return The weight of the assignment.
	 */
	public float getWeight(){
		return weight;
	}
	
	/**
	 * 
	 * @param argWeight The new weight of the assignment.
	 */
	public void setWeight(float argWeight){
		this.weight = argWeight;
	}
	
	/**
	 * 
	 * @return The required grade for the assignment.
	 */
	public float getRequiredGrade(){
		return requiredGrade;
	}
	
	/**
	 * 
	 * @param argRequiredGrade The new required grade for the assignment.
	 */
	public void setRequiredGrade(float argRequiredGrade){
		this.requiredGrade = argRequiredGrade;
	}
	
}
