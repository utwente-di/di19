package nl.utwente.di.gradeManager.servlets;

import java.util.List;

import nl.utwente.di.gradeManager.model.AssignmentResult;

public class StudentAssignments {
	
	
	private String title;
	private List<AssignmentResult> assresults;
	public StudentAssignments(String title, List<AssignmentResult> assresults){
		this.title = title;
		this.assresults = assresults;
	}
	
	public void SetTitle(String title){
		this.title = title;
	}
	
	public String GetTitle(){
		return title;
	}
	
	public void SetAssResults(List<AssignmentResult> assresults){
	this.assresults = assresults;
	}
	
	public List<AssignmentResult> GetAssignment(){
		return assresults;
	}
}
