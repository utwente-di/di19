package nl.utwente.di.gradeManager.model;

public class LoginSession {

	private String sessionid;
	private String personid;
	
	public LoginSession(String sessionid, String personid){
		this.sessionid = sessionid;
		this.personid = personid;
	}
	
	public void setSessionid(String sessionid){
		this.sessionid = sessionid;
	}
	
	public String getSessionid(){
		return sessionid;
	}
	
	public void setUserid(String personid){
		this.personid = personid;
	}
	
	public String getPersonid(){
		return personid;
	}
	
}
