package nl.utwente.di.gradeManager.model;


/**
 * Abstract class for persons (teachers and students.)
 *
 */

public abstract class Person {

	private String personID;
	private String firstname;
	private String surname;
	private String password;
	
	/**
	 * @param argPersonID the number of the person, format: s0000000 or m0000000
	 * @param argFirstname the first name of the person
	 * @param argSurname the surname of the person
	 * @param password the password of the person
	 */
	public Person(String argPersonID, String argFirstname, String argSurname, String argPassword){
		this.setPersonID(argPersonID);
		this.firstname = argFirstname;
		this.surname = argSurname;
		this.password = argPassword;
	}
		
	/**
	 * Gets the password.
	 * @return the password.
	 */
	public String getPassword(){
		return password;
	}
	/**
	 * Sets a new password
	 * @param argPassword the new password 
	 */
	public void setPassword(String argPassword){
		this.password = argPassword;
	}
	
	/**
	 * Get the firstname
	 * @return firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * Sets a new firstname
	 * @param argPassword the new password 
	 */
	public void setFirstname(String argFirstname) {
		this.firstname = argFirstname;
	}

	/**
	 * Get the surname
	 * @return surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Sets a new surname
	 * @param argSurname the new surname 
	 */
	public void setSurname(String argSurname) {
		this.surname = argSurname;
	}

	/**
	 * Get the personID
	 * @return personID
	 */
	public String getPersonID() {
		return personID;
	}

	/**
	 * Sets a new personID
	 * @param argPersonID the new personID
	 */
	public void setPersonID(String personID) {
		this.personID = personID;
	}
	
}


