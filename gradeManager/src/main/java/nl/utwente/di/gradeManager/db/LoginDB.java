package nl.utwente.di.gradeManager.db;


import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import nl.utwente.di.gradeManager.debug.Debug;
import nl.utwente.di.gradeManager.helpers.Security;
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
		GradesDB gradesDB = new GradesDB();
		
		List<Person> result = new ArrayList<Person>();
		List<Student> students = gradesDB.getStudents();
		List<Teacher> teachers = gradesDB.getTeachers();
		for (Student s : students){
			int personid_asinteger = Integer.parseInt(s.getPersonID().substring(1));
			Person p = new Student(personid_asinteger, s.getFirstname(), s.getSurname(), s.getPassword(), s.getSalt());
			result.add(s);
		}
		for (Teacher t : teachers){
			int personid_asinteger = Integer.parseInt(t.getPersonID().substring(1));
			Person p = new Teacher(personid_asinteger, t.getFirstname(), t.getSurname(), t.getPassword(), t.getSalt(),t.isManager());
			result.add(t);
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
		//something went wrong with executing the query.
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
			//something went wrong with executing the query.
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
			//something went wrong with executing the query.
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
			//something went wrong with executing the query.
			Debug.logln("LoginDB: Oops: " + e.getMessage() );
			Debug.logln("LoginDB: SQLState" + e.getSQLState() );
		}
		
		return result;
	}
	
	/**
	 * Queries the database to get the salt corresponding to a person in the database.
	 * @param argPersonid The identifier of the person.
	 * @return The salt which belongs to the person in the database.
	 */
	public String getSalt(int argPersonid){
		String result = "";
		String query = "SELECT p.salt FROM Testi.person p WHERE p.personid = " + argPersonid;
		
		try{
			//execute the query in the connected database.
			Statement st = conn.createStatement();
			Debug.logln("LoginDB: Executing query : " + query);
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				 result = rs.getString("salt");
			}
			//close resultset and statement.
			rs.close();
			st.close();
		} catch (SQLException e) {
			//something went wrong with executing the query.
			Debug.logln("LoginDB: Oops: " + e.getMessage() );
			Debug.logln("LoginDB: SQLState" + e.getSQLState() );
		}
		
		return result;
	}
	
	/**
	 * Gets the password of a certain person
	 * @param argPersonid The identifier of this person
	 * @return The password of the person. 
	 */
	public String getPassword(int argPersonid){
		String result = "";
		String query = "SELECT p.password FROM Testi.person p WHERE p.personid = " + argPersonid;
		
		try{
			//execute the query in the connected database.
			Statement st = conn.createStatement();
			Debug.logln("LoginDB: Executing query : " + query);
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				 result = rs.getString("password");
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
	 * Tests whether a password is valid for a certain person.
	 * @param argPersonid The identifier of this person
	 * @param argPassword The password to be tested, which should not be the SHA-512 hash, but the actual password to test.
	 * @return Whether the password was entered correctly, or not.
	 */
	public boolean checkPassword(int argPersonid, String argPassword){
		//get the salt for the specific person.
		String salt = getSalt(argPersonid);
		
		//generate the SHA-512 hash, based on the password and the salt.
		String hash = "";
		try {
			hash = Security.getSHA512(argPassword, salt);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Debug.logln("Login attempt: comparing " + getPassword(argPersonid) + " with " + hash + " result = " + (getPassword(argPersonid).equals(hash)));
		
		//Compare this hash to the stored hash in the database.
		return getPassword(argPersonid).equals(hash);
		
	}
	
	
}
