package nl.utwente.di.gradeManager.servlets;

import java.util.List;

import nl.utwente.di.gradeManager.model.Assignment;


public class CourseAssignments {

	private String title;
	private List<Assignment> assignments;
	
	public CourseAssignments(String title, List<Assignment> assignments) {
		this.title=title;
		this.assignments=assignments;
	}
	
	public void setTitle(String title) {
		this.title=title;
	}
	
	public String getTitle() {
		return title;		
	}
	
	public void setItems (List<Assignment> assignments) {
		this.assignments=assignments;
		return;
	}
	
	public List<Assignment> getAssignments(){
		return assignments;
	}
}
