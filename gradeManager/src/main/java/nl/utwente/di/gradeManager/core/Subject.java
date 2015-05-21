package nl.utwente.di.gradeManager.core;

import java.util.List;

/**
 * The class for a subject.
 *
 */
public class Subject {

	List<Assignment> assignments;
	
	/**
	 * Adds an assignment to the Subject
	 * @param argAssignment the assignment to be added.
	 */
	public void addAssignment(Assignment argAssignment){
		assignments.add(argAssignment);
	}
	
	/**
	 * 
	 * @return The assignments which are part of this subject.
	 */
	public List<Assignment> getAssignments(){
		return assignments;
	}
	
	/**
	 * 
	 * @return The result of the subject
	 */
	public String getResult(){
		//TODO: implement this function.
		return null;
	}
	
}
