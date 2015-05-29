package nl.utwente.di.gradeManager.model;

/**
 * Class for teachers and managers
 *
 */
public class Teacher extends Person{


	private boolean manager;
	/**
	 * Constructs the teacher object.
	 * @param argTeacherID the id of the teacher, format: 0000000
	 * @param argFirstname the first name of the teacher
	 * @param argSurname the surname of the teacher
	 * @param argPassword the password of the teacher
	 * @param argManager true/false whether the teacher is a manager.
	 * 
	 */
	public Teacher(int argTeacherID, String argFirstname, String argSurname, String argPassword, boolean argManager) {
		super("m" + String.valueOf(argTeacherID), argFirstname, argSurname, argPassword);
		this.manager = argManager;
		// TODO Auto-generated constructor stub
	}
	
	public Teacher(int argTeacherID, String argFirstname, String argSurname, String argPassword){
		super("m" + String.valueOf(argTeacherID), argFirstname, argSurname, argPassword);
		this.manager = false;
	}

	/**
	 * Tells whether the teacher is a manager or not.
	 * @return The state of the privileges of the teacher.
	 */
	public boolean isManager(){
		return this.manager;
	}
	
	/**
	 * Changes the privileges of the teacher;
	 * @param argManager whether the teacher should become a manager or not.
	 */
	public void setManager(boolean argManager){
		this.manager = argManager;
	}
	
}

