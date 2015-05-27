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
		Person frank = new Student(1581538, "Frank", "Groeneveld", "FrankGroeneveld", "wachtwoord");
		contentProvider.put("1", frank);
		Person koen = new Student(1592992, "Koen", "", "Koentjuh", "konein");
		contentProvider.put("2", koen);
				
	}
	
	public Map<String, Person> getLogins(){
		return contentProvider;
	}
	
}
