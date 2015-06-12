package nl.utwente.di.gradeManager.db;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import nl.utwente.di.gradeManager.debug.Debug;
import nl.utwente.di.gradeManager.model.*;

/**
 * Helper class for Database connection for use for grades page.
 *
 */
public class GradesDB extends DB {

	public GradesDB(){
		super();
	}
	
	/**
	 * Gets a course from the database with a given course code and a code year
	 * @param argCoursecode The code of the course.
	 * @param argCourseyear The year of the course.
	 * @return The course for the given course code and year.
	 */
	public Course getCourse(int argCoursecode, int argCourseyear){
		Course result = null;
		String query = "SELECT * FROM Testi.course c WHERE " +
		"c.coursecode = " + argCoursecode + " AND " +
		"c.year = " + argCourseyear;	
		
		try{
			//execute query in the connected database.
			Statement st = conn.createStatement();
			Debug.logln("GradesDB: Executing query : " + query);
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()){
				int coursecode = rs.getInt("coursecode");
				String  name = rs.getString("name");
				int weight = rs.getInt("weight");
				int year = rs.getInt("year");
				result = new Course(coursecode, name, weight, year);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			Debug.logln("GradesDB: Oops: " + e.getMessage());
			Debug.logln("GradesDB: SQLState: " + e.getSQLState());
		}
		
		if (result == null){
			//niet gevonden!!
			Debug.logln("GradesDB: I looked for a course with ID: " + argCoursecode + " and year : " + argCourseyear + " but I wasn't able to find one.");
		}
		
		return result;
	}
	
	/**
	 * Gets the students from the database.
	 * @return A list of students, as in the database.
	 */
	public List<Student> getStudents(){
		List<Student> result = new ArrayList<Student>();
		String query = "SELECT * FROM Testi.Person p, Testi.Student s WHERE p.personid = s.studentid";
		
		try{
			//execute query in the connected database.
			Statement st = conn.createStatement();
			Debug.logln("GradesDB: Executing query : " + query);
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()){
				int personid = rs.getInt("personid");
				String firstname = rs.getString("firstname");
				String surname = rs.getString("surname");
				String password = rs.getString("password");
				Student s = new Student(personid, firstname, surname, password);
				result.add(s);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			Debug.logln("GradesDB: Oops: " + e.getMessage());
			Debug.logln("GradesDB: SQLState: " + e.getSQLState());
		}
		
		return result;
	}
	
	/**
	 * Gets the teachers from the database.	
	 * @return A list of teachers, as in the database.
	 */
	public List<Teacher> getTeachers(){
		List<Teacher> result = new ArrayList<Teacher>();
		String query = "SELECT * FROM Testi.Person p, Testi.Teacher t WHERE p.personid = t.teacherid";
		
		try{
			//execute query in the connected database.
			Statement st = conn.createStatement();
			Debug.logln("GradesDB: Executing query : " + query);
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()){
				int personid = rs.getInt("personid");
				String firstname = rs.getString("firstname");
				String surname = rs.getString("surname");
				String password = rs.getString("password");
				boolean administrator = rs.getInt("administrator") == 1;
				Teacher t = new Teacher(personid,firstname,surname,password,administrator);
				result.add(t);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			Debug.logln("GradesDB: Oops: " + e.getMessage());
			Debug.logln("GradesDB: SQLState: " + e.getSQLState());
		}
		
		return result;
	}
	
	/**
	 * Gets the modules, which a student is doing.
	 * @param argStudentID The ID of the student.
	 * @return A list of modules, which the student is doing.
	 */
	public List<Module> getModulesForStudent(int argStudentID){
		List<Module> result = new ArrayList<Module>();
		String query = "SELECT m.modulecode, m.year, sm.name FROM Testi.module m, Testi.moduleresult mr, Testi.supermodule sm WHERE " +
		"mr.studentid = " + argStudentID + 
		"AND mr.modulecode = m.modulecode AND mr.year = m.year AND m.modulecode = sm.modulecode";
		
		try{
			//execute query in the connected database.
			Statement st = conn.createStatement();
			Debug.logln("GradesDB: Executing query : " + query);
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()){
				int modulecode = rs.getInt("modulecode");
				int year = rs.getInt("year");
				String name = rs.getString("name");
				Module m = new Module(modulecode, year, name);
				result.add(m);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			Debug.logln("GradesDB: Oops: " + e.getMessage());
			Debug.logln("GradesDB: SQLState: " + e.getSQLState());
		}
		
		return result;
	}
	
	/**
	 * Gets the courses which are part of a certain module.
	 * @param argModulecode The code of the module.
	 * @return The courses which are part of the module.
	 */
	public List<Course> getCoursesForModule(int argModulecode){
		List<Course> result = new ArrayList<Course>();
		String query = "SELECT c.* FROM Testi.course c,Testi.hascourses hc WHERE " +
		"hc.modulecode = " + argModulecode + 
		" AND c.coursecode = hc.coursecode AND c.year = hc.courseyear";
	
		try {
			//execute query in the connected database.
			Statement st = conn.createStatement();
			Debug.logln("GradesDB: Executing query : " + query);
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()){
				int coursecode = rs.getInt("coursecode");
				String coursename = rs.getString("name");
				int weight = rs.getInt("weight");
				int year = rs.getInt("year");
				Course c = new Course(coursecode, coursename, weight, year);
				result.add(c);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			Debug.logln("GradesDB: Oops: " + e.getMessage());
			Debug.logln("GradesDB: SQLState: " + e.getSQLState());
		}
		
		return result;
	
	}
	
	/**
	 * Gets the assignments for a certain course.
	 * @param argCoursecode The code of the course.
	 * @param argCourseyear The year in which this course is held.
	 * @return A list of assignments, which are part of the course.
	 */
	public List<Assignment> getAssignmentsForCourse(int argCoursecode, int argCourseyear){
		List<Assignment> result = new ArrayList<Assignment>();
		String query = "SELECT * FROM Testi.assignment a WHERE "+ 
		"a.coursecode = " + argCoursecode + " AND " + 
		"a.courseyear = " +	argCourseyear + 
		" ORDER BY assignmentid";
		
		try{
			//execute the query
			Statement st = conn.createStatement();
			Debug.logln("GradesDB: Executing query : " + query);
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()){
				int assignmentid = rs.getInt("assignmentid");
				int coursecode = rs.getInt("coursecode");
				int courseyear = rs.getInt("courseyear");
				String name = rs.getString("name");
				boolean isgradedassignment = rs.getInt("isgradedassignment") == 1;
				int weight = rs.getInt("weight");
				float minimumresult = rs.getFloat("minimumresult");
				Assignment a = new Assignment(assignmentid, coursecode, courseyear, name, isgradedassignment, weight, minimumresult);
				result.add(a);
			}
		
			rs.close();
			st.close();
		} catch (SQLException e) {
			Debug.logln("GradesDB: Oops: " + e.getMessage());
			Debug.logln("GradesDB: SQLState: " + e.getSQLState());
		}
		
		return result;
	}
	
	/**
	 * Gets the occasions for a given assignment.
	 * @param argAssignmentid The id of the assignment.
	 * @return A list of occasions for this assignment.
	 */
	public List<AssignmentOccasion> getOccasionsForAssignment(int argAssignmentid){
		List<AssignmentOccasion> result = new ArrayList<AssignmentOccasion>();
		String query = "SELECT * FROM Testi.assignmentoccasion ao WHERE " + 
		"ao.assignmentid = " + argAssignmentid; 
		
		try{
			//execute the query
			Statement st = conn.createStatement();
			Debug.logln("GradesDB: Executing query : " + query);
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()){
				int occasionid = rs.getInt("occasionid");
				int assignmentid = rs.getInt("assignmentid");
				Date occasiondate = rs.getDate("occasiondate");
				AssignmentOccasion ao = new AssignmentOccasion(occasionid, assignmentid, occasiondate);
			
			}
		
			rs.close();
			st.close();
		} catch (SQLException e) {
			Debug.logln("GradesDB: Oops: " + e.getMessage());
			Debug.logln("GradesDB: SQLState: " + e.getSQLState());
		}
		
		return result;
	}
	
	/**
	 * Gets the assignment results for a specific student and assignment.
	 * @param argStudentid The id of the student who made the assignment
	 * @param argAssignmentid The id of the made assignment.
	 * @return
	 */
	public List<AssignmentResult> getResultsForAssignmentAndStudent(int argStudentid, int argAssignmentid){
		List<AssignmentResult> result = new ArrayList<AssignmentResult>();
		
		// SELECT ar.*,ao.occasiondate FROM Testi.assignmentresult ar, Testi.assignmentoccasion ao WHERE 
		// ar.studentid = argStudentid AND  
		// ao.assignmentid = argAssignmentid AND 
		// ao.occasionid = ar.occasionid
		
		String query = "SELECT ar.*,ao.occasiondate FROM Testi.assignmentresult ar, Testi.assignmentoccasion ao WHERE "
				+ "ar.studentid = " + argStudentid + " AND " 
				+ "ao.assignmentid = " + argAssignmentid  + " AND "
				+ "ao.occasionid = ar.occasionid";
		
		try{
			//execute the query
			Statement st = conn.createStatement();
			Debug.logln("GradesDB: Executing query : " + query);
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()){
				int occasionid = rs.getInt("occasionid");
				float grade = rs.getFloat("result");
				Date date = rs.getDate("occasiondate");
				AssignmentResult ar = new AssignmentResult(occasionid, argStudentid, date,grade);
				result.add(ar);
			}
		
			rs.close();
			st.close();
		} catch (SQLException e) {
			Debug.logln("GradesDB: Oops: " + e.getMessage());
			Debug.logln("GradesDB: SQLState: " + e.getSQLState());
		}
		
		
		
		return result;
		
	}
	
}
