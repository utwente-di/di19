package nl.utwente.di.gradeManager.db;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import nl.utwente.di.gradeManager.debug.Debug;
import nl.utwente.di.gradeManager.model.*;

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
			//execute the query in the connected database.
			Statement st = conn.createStatement();
			Debug.logln("LoginDB: Executing query : "  + query);
			ResultSet rs = st.executeQuery(query);
			while (rs.next()){
				//get person count from result;
				count = rs.getInt("count");
			}
			Debug.logln("LoginDB: The number of accounts = " + count);
			//close the result set and statement.
			rs.close();
			st.close();
		} catch (SQLException e){
			//something went wrong with executing the query.
			Debug.logln("LoginDB: Oops: " + e.getMessage() );
			Debug.logln("LoginDB: SQLState: " + e.getSQLState() );
		}
		
		//return the result of the query (the number of login accounts)
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
			//execute the query in the connected database.
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
			//close resultset and statement.
			rs.close();
			st.close();
		} catch (SQLException e) {
			//something went wrong with executing the query.s
			Debug.logln("LoginDB: Oops: " + e.getMessage() );
			Debug.logln("LoginDB: SQLState" + e.getSQLState() );
		}
		
		//en nu voor de teachers
		String teacherquery = "SELECT DISTINCT Person.personid, Person.firstname, Person.surname, Person.password FROM Testi.Person, Testi.Teacher WHERE Person.personid IN (SELECT Teacher.teacherid FROM Testi.Teacher) ORDER BY personid";
		try { 
			//execute the query in the connected database.
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
			//close the resultset and statement.
			rs.close();
			st.close();
		} catch (SQLException e) {
			//something went wrong with executing the query.
			Debug.logln("LoginDB: Oops: " + e.getMessage() );
			Debug.logln("LoginDB: SQLState: " + e.getSQLState() );
		}
		
		//return the list of persons who are in the database.
		return result;
	}
	
	/**
	 * Looks in the database for a session with a corresponding session id
	 * In order to find out who is the potential logged in user.
	 * @param sessionid The session id of the user of the web app
	 * @return The identifier of the person, who is logged in, or "" if no one is logged in.
	 */
	public String getLoggedInPersonid(String sessionid){
		String query = "SELECT ls.personid FROM Testi.loginsession ls WHERE " + 
		"ls.sessionid = '" + sessionid + "'";
		String personid = "";
	
	try{
		//execute the query in the connected database.
		Statement st = conn.createStatement();
		Debug.logln("LoginDB: Executing session query : " + query);
		ResultSet rs = st.executeQuery(query);
		while(rs.next()){
			 personid = rs.getString("personid");
		}
		//close resultset and statement.
		rs.close();
		st.close();
	} catch (SQLException e) {
		//something went wrong with executing the query.s
		Debug.logln("LoginDB: Oops: " + e.getMessage() );
		Debug.logln("LoginDB: SQLState" + e.getSQLState() );
	}
	
	return personid;
	}
	
	/**
	 * Adds a LoginSession to the database. (Table: loginsession)
	 * @param ls The LoginSession to be added to the database.
	 */
	public void addSession(LoginSession ls){
		String query = "INSERT INTO Testi.loginsession(sessionid,personid) VALUES (?,?)";
		try{
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, ls.getSessionid());
			ps.setString(2, ls.getPersonid());
			Debug.logln("LoginDB: Executing statement: " + ps.toString());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e){
			Debug.logln("LoginDB: Oops: " + e.getMessage());
			Debug.logln("LoginDB: SQLState: " + e.getSQLState());
		}
	}
	
	/**
	 * Finds out of the person with the given personid is a teacher.
	 * @param argPersonid The identifier of the person.
	 * @return Whether the person is a teacher.
	 */
	public boolean isTeacher(int argPersonid){
		boolean result = false;
		//Look if the personid corresponds with a record in the teacher table
		String query = "SELECT 1 FROM Testi.teacher WHERE Testi.teacher.teacherid = " + argPersonid;
		
		try{
			//execute the query in the connected database.
			Statement st = conn.createStatement();
			Debug.logln("LoginDB: Executing query : " + query);
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				result = true;
			}
			//close resultset and statement.
			rs.close();
			st.close();
		} catch (SQLException e) {
			//something went wrong with executing the query.s
			Debug.logln("LoginDB: Oops: " + e.getMessage() );
			Debug.logln("LoginDB: SQLState" + e.getSQLState() );
		}
		
		return result;
	}
	
	/**
	 * Finds out of the person with the given personid is a student.
	 * @param argPersonid The identifier of the person.
	 * @return Whether the person is a student.
	 */
	public boolean isStudent(int argPersonid){
		boolean result = false;
		String query = "SELECT 1 FROM Testi.student WHERE Testi.student.studentid = " + argPersonid;
		//Look if the personid corresponds with a record in the student table
		
		try{
			//execute the query in the connected database.
			Statement st = conn.createStatement();
			Debug.logln("LoginDB: Executing query : " + query);
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				result = true;
			}
			//close resultset and statement.
			rs.close();
			st.close();
		} catch (SQLException e) {
			//something went wrong with executing the query.s
			Debug.logln("LoginDB: Oops: " + e.getMessage() );
			Debug.logln("LoginDB: SQLState" + e.getSQLState() );
		}
		
		return result;
	}
	
	/**
	 * Finds out of the person with the given personid is a manager
	 * @param argPersonid The identifier of the person.
	 * @return The manager state of the person.
	 */
	public boolean isManager(int argPersonid){
		boolean result = false;
		//Look if the personid corresponds with a record in the teacher table
		//Then, look if the manager field is true.
		String query = "SELECT 1 FROM Testi.teacher WHERE Testi.teacher.administrator = true AND Testi.teacher.teacherid = " + argPersonid;
		
		try{
			//execute the query in the connected database.
			Statement st = conn.createStatement();
			Debug.logln("LoginDB: Executing query : " + query);
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				 result = true;
			}
			//close resultset and statement.
			rs.close();
			st.close();
		} catch (SQLException e) {
			//something went wrong with executing the query.s
			Debug.logln("LoginDB: Oops: " + e.getMessage() );
			Debug.logln("LoginDB: SQLState" + e.getSQLState() );
		}
		
		return result;
	}
}
