package nl.utwente.di.gradeManager.model;

public class AssignmentResult {
	
	private Student student;
	private AssignmentOccasion assignment;
	private double result;
	
	/**
	 * Default constructor for Assignmentresult
	 * @param argStudent		The student that made this assignment
	 * @param argAssignment		The assignment the student made
	 * @param argResult			The result of the assignment
	 */
	public AssignmentResult(Student argStudent, AssignmentOccasion argAssignment, double argResult){
		this.student = argStudent;
		this.assignment = argAssignment;
		this.result = argResult;
	}
	
	/**
	 * 
	 * @return	The student whom this result corresponds to
	 */
	public Student getStudent(){
		return student;
	}

	/**
	 * 
	 * @param argStudent	The student whom this result corresponds to
	 */
	public void setStudent(Student argStudent){
		this.student = argStudent;
	}
	
	/**
	 * 
	 * @return	The assignmentoccasion this result corresponds to
	 */
	public AssignmentOccasion getAssignment(){
		return assignment;
	}
	
	
	public void setAssignment(AssignmentOccasion argAssignment){
		this.assignment = argAssignment;
	}
	
	/**
	 * 
	 * @return	The result of this assignment
	 */
	public double getResult(){
		return result;
	}
	
	
	public void setResult(double argResult){
		this.result = argResult;
	}
}
