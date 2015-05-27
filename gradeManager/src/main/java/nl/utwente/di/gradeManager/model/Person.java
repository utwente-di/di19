package nl.utwente.di.gradeManager.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Abstract class for persons (teachers and students.)
 *
 */

public abstract class Person {

	private int personID;
	private String firstname;
	private String surname;
	private String username;
	private String password;
	
	/**
	 * 
	 * @param argUsername the username of the person.
	 */
	public Person(int argPersonID, String argFirstname, String argSurname, String argUsername, String argPassword){
		this.setPersonID(argPersonID);
		this.firstname = argFirstname;
		this.surname = argSurname;
		this.username = argUsername;
		this.password = argPassword;
	}
	
	/**
	 * the s- or m- number of the teacher/student
	 * @return the username of the person.
	 */
	public String getUsername(){
		return username;
	}
	
	/**
	 * sets the new username of the person
	 * @param argUsername the new username
	 */
	public void setUsername(String argUsername){
		this.username = argUsername;
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
	public int getPersonID() {
		return personID;
	}

	/**
	 * Sets a new personID
	 * @param argPersonID the new personID
	 */
	public void setPersonID(int personID) {
		this.personID = personID;
	}
	
}


