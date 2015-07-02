package nl.utwente.di.gradeManager.servlets;

import java.util.List;

import nl.utwente.di.gradeManager.model.Student;


public class AllStudents{

	private String title;
	private List<Student> students;


	public AllStudents(String title, List<Student> students) {
		this.title=title;
		this.students=students;
	}
	
	public void setTitle(String title) {
		this.title=title;
	}
	
	public String getTitle() {
		return title;		
	}
	
	public void setItems (List<Student> students) {
		this.students=students;
		return;
	}
	
	public List<Student> getStudents(){
		return students;
	}
}