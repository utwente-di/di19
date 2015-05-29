package nl.utwente.di.gradeManager.model;

public class Teacher extends Person{

	/**
	 * Constructs the teacher object.
	 * @param argTeacherID the id of the teacher, format: 0000000
	 * @param argFirstname the first name of the teacher
	 * @param argSurname the surname of the teacher
	 * @param argPassword the password of the teacher
	 * 
	 */
	public Teacher(int argTeacherID, String argFirstname, String argSurname, String argPassword) {
		super("m" + String.valueOf(argTeacherID), argFirstname, argSurname, argPassword);
		// TODO Auto-generated constructor stub
	}

	
	
	
}

