package nl.utwente.di.gradeManager.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import nl.utwente.di.gradeManager.debug.Debug;
import nl.utwente.di.gradeManager.model.Person;
import nl.utwente.di.gradeManager.model.Student;
import nl.utwente.di.gradeManager.model.Teacher;

/**
 * Extension of DB for use of the Login page.
 *
 */
public class LoginDB extends DB {
	
	/**
	 * Constructs the Login Database handler
	 * Make sure to always close connection after using it!
	 */
	public LoginDB(){
		super();
	}
	
	/**
	 * Queries the database in order to receive the count of logins.
	 * @return the number of logins.
	 */
	public int getLoginCount(){
		int count = 0;
		String query = "SELECT COUNT(Person.personID) FROM testi.Person";
		try {
			Statement st = conn.createStatement();
			Debug.logln("LoginDB: Executing query : "  + query);
			ResultSet rs = st.executeQuery(query);
			while (rs.next()){
				//get person count from result;
				count = rs.getInt("count");
			}
			rs.close();
			st.close();
		} catch (SQLException e){
			Debug.logln("LoginDB: Oops: " + e.getMessage() );
			Debug.logln("LoginDB: SQLState: " + e.getSQLState() );
		}
		
		
		return count;
		
	}
	
	/**
	 * queries the database to get a list of persons
	 * @return A List of persons who are in the database.
	 */
	public List<Person> getLogins(){
		//ik doe het nu als twee aparte queries,
		//eentje voor de studenten en eentje voor de docenten
		//omdat een Person zelf abstract is en het niet de bedoeling is een student en een docent allebei als student of allebei als docent op te slaan.
		List<Person> result = new ArrayList<Person>();
		//Eerste query, voor de studenten
		String studentquery = "SELECT DISTINCT Person.personid, Person.firstname, Person.surname, Person.password FROM Testi.Person, Testi.Student WHERE Person.personid IN (SELECT Student.studentid FROM Testi.Student) ORDER BY personid";
		try {
			Statement st = conn.createStatement();
			Debug.logln("LoginDB: Executing student query : " + studentquery);
			ResultSet rs = st.executeQuery(studentquery);
			while(rs.next()){
				int studentID = rs.getInt("personid");
				String firstname = rs.getString("firstname");
				String surname = rs.getString("surname");
				String password = rs.getString("password");
				Person p = new Student(studentID, firstname, surname, password);
				result.add(p);
				
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			Debug.logln("LoginDB: Oops: " + e.getMessage() );
			Debug.logln("LoginDB: SQLState" + e.getSQLState() );
		}
		
		//en nu voor de teachers
		String teacherquery = "SELECT DISTINCT Person.personid, Person.firstname, Person.surname, Person.password FROM Testi.Person, Testi.Teacher WHERE Person.personid IN (SELECT Teacher.teacherid FROM Testi.Teacher) ORDER BY personid";
		try { 
			Statement st = conn.createStatement();
			Debug.logln("LoginDB: Executing teacher query : " + teacherquery);
			ResultSet rs = st.executeQuery(teacherquery);
			while(rs.next()){
				int teacherId = rs.getInt("personid");
				String firstname = rs.getString("firstname");
				String surname = rs.getString("surname");
				String password = rs.getString("password");
				Person p = new Teacher(teacherId, firstname, surname, password);
				result.add(p);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			Debug.logln("LoginDB: Oops: " + e.getMessage() );
			Debug.logln("LoginDB: SQLState: " + e.getSQLState() );
		}
		
		return result;
	}
	
}
