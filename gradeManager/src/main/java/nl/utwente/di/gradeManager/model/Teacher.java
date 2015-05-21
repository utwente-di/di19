package nl.utwente.di.gradeManager.model;

import java.util.List;

public class Teacher extends Person{

	/**
	 * Constructs the teacher object.
	 * @param argUsername the username of the teacher.
	 */
	public Teacher(String argUsername, String argPassword) {
		super(argUsername,argPassword);
		
	}

	private List<Module> modules;
	
}

