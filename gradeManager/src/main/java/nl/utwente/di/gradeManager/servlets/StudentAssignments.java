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
	
	public void getTitle(String title){
		this.title = title;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void getAssResults(List<AssignmentResult> assresults){
	this.assresults = assresults;
	}
	
	public List<AssignmentResult> getAssResults(){
		return assresults;
	}
}
