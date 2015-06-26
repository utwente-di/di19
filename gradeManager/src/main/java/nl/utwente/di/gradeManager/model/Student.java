package nl.utwente.di.gradeManager.model;

/**
 * Class for students.
 *
 */
public class Student extends Person {

	

	/**
	 * Constructs the student object.
	 * @param argStudentID the id of the student, format: 0000000
	 * @param argFirstname the first name of the student.
	 * @param argSurname the surname of the student.
	 * @param argPassword the password of the student.
	 * @param argSalt the salt of the student.
	 */
	public Student(int argStudentID, String argFirstname, String argSurname, String argPassword, String argSalt) {
		super("s" + String.valueOf(argStudentID), argFirstname, argSurname, argPassword, argSalt);
		// TODO Auto-generated constructor stub
	}
	
}
