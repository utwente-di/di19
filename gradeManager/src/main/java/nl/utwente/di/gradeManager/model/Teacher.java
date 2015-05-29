package nl.utwente.di.gradeManager.model;

import java.util.List;

public class Teacher extends Person{

	/**
	 * Constructs the teacher object.
	 * @param argUsername the username of the teacher.
	 */
	public Teacher(int argPersonID, String argFirstname, String argSurname,
			String argUsername, String argPassword) {
		super(argPersonID, argFirstname, argSurname, argUsername, argPassword);
		// TODO Auto-generated constructor stub
	}

	private List<Module> modules;
	
}

