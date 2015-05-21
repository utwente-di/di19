package nl.utwente.di.gradeManager.core;

/**
 * Class for the authentication system.
 *
 */
public class Login {
	
	private Person person;
	private String password;

	/**
	 * Create a Login with a username and a password
	 * @param argPerson the person which is associated to the password
	 * @param argPassword the password of the user.
	 */
	public Login(Person argPerson, String argPassword){
		this.person = argPerson;
		this.password = argPassword;
	}
	
	/**
	 * 
	 * @param argPassword the new password of the user.
	 */
	public void setPassword(String argPassword){
		this.password = argPassword;
	}

	/**
	 * 
	 * @return the password of the user.
	 */
	public String getPassword(){
		return password;
	}
	
	/**
	 * @return the person of this login
	 */
	public Person getPerson(){
		return person;
	}
	
}
