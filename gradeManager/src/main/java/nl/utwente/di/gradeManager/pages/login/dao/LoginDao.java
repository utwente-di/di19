package nl.utwente.di.gradeManager.pages.login.dao;

import java.util.HashMap;
import java.util.Map;
import nl.utwente.di.gradeManager.model.Person;
import nl.utwente.di.gradeManager.model.Student;


public enum LoginDao {
	instance;
	private Map<String, Person> contentProvider = new HashMap<String, Person>();
	
	private LoginDao(){
		//constructor for our loginDao, in the future we need to load login details from database here.
		System.out.println("Loading logins..");
		Person frank = new Student(1581538, "Frank", "Groeneveld", "wachtwoord");
		contentProvider.put("1", frank);
		Person koen = new Student(1592992, "Koen", "Groen", "konein");
		contentProvider.put("2", koen);
		for(Person p : getLogins().values()){
			System.out.println(p.getPersonID() + ":" + p.getPassword());
		}
				
	}
	
	public Map<String, Person> getLogins(){
		return contentProvider;
	}
	
}
