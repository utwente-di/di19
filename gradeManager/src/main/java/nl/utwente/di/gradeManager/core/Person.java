package nl.utwente.di.gradeManager.core;

/**
 * Abstract class for persons (teachers and students.)
 *
 */
public abstract class Person {

	private String username;
	private String realname;
	
	/**
	 * 
	 * @param argUsername the username of the person.
	 */
	public Person(String argUsername){
		this.username = argUsername;
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


