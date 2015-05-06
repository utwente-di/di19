package nl.utwente.di.gradeManager;

import java.util.List;

public class Teacher extends Person{

	/**
	 * Constructs the teacher object.
	 * @param argUsername the username of the teacher.
	 */
	public Teacher(String argUsername) {
		super(argUsername);
		
	}

	private List<Module> modules;
	
}

