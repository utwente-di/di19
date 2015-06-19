package nl.utwente.di.gradeManager.servlets;

import nl.utwente.di.gradeManager.model.Module;
import java.util.List;

public class StudentModules {
	
	private int studentID;
	private List<Module> modules;
	
	public StudentModules(int studentID, List<Module> modules){
		this.studentID = studentID;
		this.modules = modules;
	}
	
	public void setStudentID(int studentID){
		this.studentID = studentID;
	}
	
	public int getStudentID(){
		return studentID;
	}
	
	public void setModules(List<Module> modules){
		this.modules = modules;
	}
	
	public List<Module> getModules(){
		return modules;
	}

}
