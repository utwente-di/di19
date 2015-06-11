package nl.utwente.di.gradeManager.servlets;

import java.util.List;

import nl.utwente.di.gradeManager.model.Course;


public class StudentCourses {

	private String title;
	private List<Course> courses;
	
	public StudentCourses(String title, List<Course> courses) {
		this.title=title;
		this.courses=courses;
	}
	
	public void setTitle(String title) {
		this.title=title;
	}
	
	public String getTitle() {
		return title;		
	}
	
	public void setItems (List<Course> courses) {
		this.courses=courses;
		return;
	}
	
	public List<Course> getCourses(){
		return courses;
	}
}
