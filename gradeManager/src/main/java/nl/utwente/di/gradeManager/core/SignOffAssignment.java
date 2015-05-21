package nl.utwente.di.gradeManager.core;

import java.util.Date;

/**
 * Class for signed off tasks.
 *
 */
public class SignOffAssignment extends Assignment {

	private boolean signedOff;

	/**
	 * Constructs the signed off assignment
	 * @param argTitle The title of the signed off assignment.
	 * @param argDate The date of the signed off assignment.
	 */
	public SignOffAssignment(String argTitle, Date argDate) {
		super(argTitle, argDate);
	}

	/**
	 * 
	 * @return whether the assignment has been signed off or not.
	 */
	public boolean getSignedOff(){
		return signedOff;
	}
	
	/**
	 * signs off the assignment.
	 */
	public void signOff(){
		signedOff = true;
	}
}
