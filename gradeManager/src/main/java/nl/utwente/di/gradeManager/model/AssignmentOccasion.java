package nl.utwente.di.gradeManager.model;

import java.util.Date;

public class AssignmentOccasion extends Assignment{
	private Date date;

	public AssignmentOccasion(int argID, Course argcourse, String argname,
			boolean argGraded, int argWeight) {
		super(argID, argcourse, argname, argGraded, argWeight);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @return 	The Date of this assignment
	 */
	public Date getDate(){
		return date;
	}

	
	public void setDate(Date argDate){
		this.date = argDate;
	}
}
