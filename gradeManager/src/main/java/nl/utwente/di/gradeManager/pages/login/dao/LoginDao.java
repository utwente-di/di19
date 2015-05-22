package nl.utwente.di.gradeManager.pages.login.dao;

import java.util.HashMap;
import java.util.Map;
import nl.utwente.di.gradeManager.model.Person;
import nl.utwente.di.gradeManager.model.Student;


public enum LoginDao {
	instance;
	private Map<String, Person> contentProvider = new HashMap<String, Person>();
	
	private LoginDao(){
		
		System.out.println("making logins..");
		Person frank = new Student("s1581538", "wachtwoord");
		frank.setRealname("Frank Groeneveld");
		contentProvider.put("1", frank);
		Person koen = new Student("s1592992", "konein");
		koen.setRealname("Koentje");
		contentProvider.put("2", koen);
				
	}
	
	public Map<String, Person> getLogins(){
		return contentProvider;
	}
	
}
