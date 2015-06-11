package nl.utwente.di.gradeManager.model;

import java.sql.Date;

public class AssignmentOccasion{
	
	private int occasionid;
	private int assignmentid;
	private Date occasiondate;
	
	public AssignmentOccasion(int argOccasionid, int argAssignmentid, Date argOccasiondate){
		this.occasionid = argOccasionid;
		this.assignmentid = argAssignmentid;
		this.occasiondate = argOccasiondate;
	}
	
	public int getOccasionid(){
		return this.occasionid;
	}
	
	public void setOccasionid(int argOccasionid){
		this.occasionid = argOccasionid;
	}
	
	public int getAssignmentid(){
		return this.assignmentid;
	}
	
	public void setAssignmentid(int argAssignmentid){
		this.assignmentid = argAssignmentid;
	}
	
	public Date getOccasiondate(){
		return this.occasiondate;
	}
	
	public void setOccasiondate(Date argOccasiondate){
		this.occasiondate = argOccasiondate;
	}
	
	
	
}
