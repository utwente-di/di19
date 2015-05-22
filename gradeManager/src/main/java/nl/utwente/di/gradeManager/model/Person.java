package nl.utwente.di.gradeManager.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Abstract class for persons (teachers and students.)
 *
 */

@XmlRootElement
public abstract class Person {

	private String username;
	private String password;
	private String realname;
	
	/**
	 * 
	 * @param argUsername the username of the person.
	 */
	public Person(String argUsername, String argPassword){
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
	 * 
	 * @return the real name of the person.
	 */
	public String getRealname(){
		return realname;
	}
	
	/**
	 * sets the new real name of the person
	 * @param argRealname the new real name.
	 */
	public void setRealname(String argRealname){
		this.realname = argRealname;
	}
	
}


