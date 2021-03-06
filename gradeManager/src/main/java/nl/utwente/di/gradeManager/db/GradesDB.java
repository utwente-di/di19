package nl.utwente.di.gradeManager.db;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

import nl.utwente.di.gradeManager.debug.Debug;
import nl.utwente.di.gradeManager.helpers.Security;
import nl.utwente.di.gradeManager.model.*;

/**
 * Helper class for Database connection for use for grades page.
 * Queries in comments are useful for testing the queries outside of java.
 */
public class GradesDB extends DB {

	/**
	 * Constructs the grades DB helper.
	 */
	public GradesDB(){
		super();
	}
	
	/*
	 * READ
	 */
	
	
	public List<AssignmentResult> getAssignmentResultsForStudent(int personid){
		List<AssignmentResult> result = new ArrayList<AssignmentResult>();
		String query = "SELECT ar.occasionid, ao.occasiondate, ar.result, ao.assignmentid FROM Testi.assignment a, Testi.assignmentresult ar, Testi.assignmentoccasion ao "+ 
	"WHERE ar.studentid = " + personid + " AND ar.occasionid = ao.occasionid AND a.assignmentid = ao.assignmentid";
		try {
			Statement st = conn.createStatement();
			Debug.logln("GradesDB: Executing query: " + query);
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()){
				int occasionid = rs.getInt("occasionid");
				int studentid = personid;
				int assignmentid = rs.getInt("assignmentid");
				Date date = rs.getDate("occasiondate");
				BigDecimal assresult = rs.getBigDecimal("result");
				result.add(new AssignmentResult(occasionid, studentid, date, assresult, assignmentid));
				
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
	 * Gets a course from the database with a given course code and a code year
	 * @param argCoursecode The code of the course.
	 * @param argCourseyear The year of the course.
	 * @return The course for the given course code and year.
	 */
	public Course getCourse(int argCoursecode, int argCourseyear){
		Course result = null;
		String query = "SELECT * FROM Testi.course c, Testi.supercourse sc WHERE " +
		"c.coursecode = sc.coursecode AND " +
		"c.coursecode = " + argCoursecode + " AND " +
		"c.year = " + argCourseyear;	
		
//		SELECT * FROM Testi.course c, Testi.supercourse sc WHERE c.coursecode = sc.coursecode AND
//				c.coursecode =  AND
//				c.year = 
		
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
				result = new Course(coursecode, year, name, weight);
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
	 * Gets all the courses from this supercourse(basically, the years in which they were given)
	 * and returns them in a list
	 * @param argCoursecode The code of this SuperCourse
	 * @return A List of courses that belong to this supercourse
	 */

	
	public SuperCourse getSuperCourse(int argCoursecode){
		SuperCourse result = null;
		String query = "SELECT * FROM Testi.supercourse sc WHERE sc.coursecode = " + argCoursecode;
		//SELECT * FROM Testi.supercourse sc WHERE sc.coursecode = argCoursecode
		
		try{
			//execute query in the connected database.
			Statement st = conn.createStatement();
			Debug.logln("GradesDB: Executing query : " + query);
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()){
				int coursecode = rs.getInt("coursecode");
				String name = rs.getString("name");
				int weight = rs.getInt("weight");
				result = new SuperCourse(coursecode, name, weight);				
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			Debug.logln("GradesDB: Oops: " + e.getMessage());
			Debug.logln("GradesDB: SQLState: " + e.getSQLState());
		}
	
		return result;
	}
	

	public List<Course> getCoursesForSuperCourse(int argCoursecode){
		List<Course> result = new ArrayList<Course>();
		
		String query = "SELECT c.coursecode,sc.name,sc.weight,c.year FROM Testi.supercourse sc, Testi.course c WHERE " +
		"c.coursecode = sc.coursecode AND " +
		"c.coursecode = " + argCoursecode;	
		
		//SELECT c.coursecode,sc.name,sc.weight,c.year FROM Testi.supercourse sc, Testi.course c WHERE
		//c.coursecode = sc.coursecode AND
		//c.coursecode = argCoursecode
		
		try{
			//execute query in the connected database.
			Statement st = conn.createStatement();
			Debug.logln("GradesDB: Executing query : " + query);
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()){
				int coursecode = rs.getInt("coursecode");
				String name = rs.getString("name");
				int weight = rs.getInt("weight");
				int year = rs.getInt("year");
				result.add(new Course(coursecode, year, name, weight));
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
	 * Gets the student from the database with an id.
	 * @param argStudentid The id of this specific student.
	 * @return The student with the id.
	 */
	public Student getStudent(int argStudentid){
		Student result = null;
		
		String query = "SELECT * FROM Testi.person p, Testi.student s WHERE " +
				"p.personid = s.studentid AND " +
				"s.studentid = " + argStudentid;
		
		//SELECT * FROM Testi.person p, Testi.student s WHERE 
		//p.personid = s.studentid AND 
		//s.studentid = argStudentid
		
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
				String salt = rs.getString("salt");
				Student s = new Student(personid, firstname, surname, password, salt);
				result = s;
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			Debug.logln("GradesDB: Oops: " + e.getMessage());
			Debug.logln("GradesDB: SQLState: " + e.getSQLState());
		}
		
		
		if (result == null){
			Debug.logln("GradesDB: I looked in the database for student id " + argStudentid + " but couldn't find anything.");
		}
		return result;
	}
	
	/**
	 * Gets all the students from the database.
	 * @return A list of students, as in the database.
	 */
	public List<Student> getStudents(){
		List<Student> result = new ArrayList<Student>();
		String query = "SELECT * FROM Testi.Person p, Testi.Student s WHERE p.personid = s.studentid";
		
		//SELECT * FROM Testi.Person p, Testi.Student s WHERE p.personid = s.studentid
		
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
				String salt = rs.getString("salt");
				Student s = new Student(personid, firstname, surname, password, salt);
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
	 * Gets a teacher from the database
	 * @param argTeacherid The id of the teacher.
	 * @return The teacher with the certain id.
	 */
	public Teacher getTeacher(int argTeacherid){
		Teacher result = null;
		
		String query = "SELECT * FROM Testi.person p, Testi.teacher t WHERE " +
				"p.personid = t.teacherid AND " +
				"t.teacherid = " + argTeacherid;
		
		//SELECT * FROM Testi.person p, Testi.teacher t WHERE 
		//p.personid = t.teacherid AND 
		//t.teacherid = argTeacherid
		
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
				String salt = rs.getString("salt");
				boolean administrator = rs.getBoolean("administrator");
				Teacher t = new Teacher(personid, firstname, surname, password,salt,administrator);
				result = t;
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			Debug.logln("GradesDB: Oops: " + e.getMessage());
			Debug.logln("GradesDB: SQLState: " + e.getSQLState());
		}
		
		
		if (result == null){
			Debug.logln("GradesDB: I looked in the database for student id " + argTeacherid + " but couldn't find anything.");
		}
		return result;
	}
	
	/**
	 * Gets all the teachers from the database.	
	 * @return A list of Teacher classes of all teachers in the database
	 */
	public List<Teacher> getTeachers(){
		List<Teacher> result = new ArrayList<Teacher>();
		String query = "SELECT * FROM Testi.Person p, Testi.Teacher t WHERE p.personid = t.teacherid";
		
		//SELECT * FROM Testi.Person p, Testi.Teacher t WHERE p.personid = t.teacherid
		
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
				String salt = rs.getString("salt");
				boolean administrator = rs.getBoolean("administrator");
				Teacher t = new Teacher(personid,firstname,surname,password,salt,administrator);
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
	 * Gets a module according to a given code and year
	 * @param argmoduleCode The Modulecode to look for
	 * @param argmoduleYear The Year to look for
	 * @return A class module which corresponds to the given modulecode and year
	 */

	
	public SuperModule getSuperModule(int argModulecode){
		SuperModule result = null;
		
		String query = "SELECT * FROM Testi.supermodule sm WHERE sm.modulecode = " + argModulecode;
		//SELECT * FROM Testi.supermodule sm WHERE sm.modulecode = argModulecode
		
		try{
			//execute the query
			Statement st = conn.createStatement();
			Debug.logln("GradesDB: Executing query : " + query);
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()){
				int modulecode = rs.getInt("modulecode");
				String name = rs.getString("name");
				result = new SuperModule(modulecode, name);
			}
			
			rs.close();
			st.close();
		} catch (SQLException e) {
			Debug.logln("GradesDB: Oops: " + e.getMessage());
			Debug.logln("GradesDB: SQLState: " + e.getSQLState());
		}
		
		
		return result;
	}
	

	public Module getModule(int argmoduleCode, int argmoduleYear){
		Module result = null;
		String query = "SELECT m.moduleCode, m.year, sm.name FROM Testi.module m, Testi.supermodule sm WHERE " +
		"m.moduleCode = sm.moduleCode AND " + 
		"m.year = " + argmoduleYear + " AND " + 
		"m.moduleCode = " + argmoduleCode
		;
		
		try{
			//execute the query
			Statement st = conn.createStatement();
			Debug.logln("GradesDB: Executing query : " + query);
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()){
				int moduleCode = rs.getInt("moduleCode");
				int year = rs.getInt("year");
				String name = rs.getString("name");
				Module m = new Module(moduleCode, year, name);
				result = m;
			}
		
			rs.close();
			st.close();
		} catch (SQLException e) {
			Debug.logln("GradesDB: Oops: " + e.getMessage());
			Debug.logln("GradesDB: SQLState: " + e.getSQLState());
		}
		
		
		if (result == null){
			Debug.logln("I tried looking for an assignment with id : " + argmoduleCode + " but didn't find anything.");
		}
		return result;
	}
	
	/**
	 * Gets all modules for a certain super module.
	 * @param argSupermodulecode The id of the supermodule
	 * @return A list of modules, which are an instance of a the supermodule.
	 */
	public List<Module> getModulesForSuperModule(int argSupermodulecode){
		List<Module> result = new ArrayList<Module>();
		String query = "SELECT m.modulecode, m.year, sm.name FROM Testi.module m, Testi.supermodule sm WHERE "
				+ "m.modulecode = sm.modulecode AND " 
				+ "m.modulecode = " + argSupermodulecode;
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
	 * Gets all the modules in the database.
	 * @return A list containing all the module elements in the database.
	 */
	public List<Module> getModules(){
		List<Module> result = new ArrayList<Module>();
		String query = "SELECT m.modulecode, m.year, sm.name FROM Testi.module m, Testi.supermodule sm WHERE "
				+ "m.modulecode = sm.modulecode ";
		//SELECT m.modulecode, m.year, sm.name FROM Testi.module m, Testi.supermodule sm WHERE
		//m.modulecode = sm.modulecode 		
		
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
	 * Gets an assignment with a specific id.
	 * @param argAssignmentid The id of the certain assignment.
	 * @return The assignment with the certain id.
	 */
	public Assignment getAssignment(int argAssignmentid){
		Assignment result = null;
		String query = "SELECT * FROM Testi.assignment WHERE " +
		"assignmentid = " + argAssignmentid;
		
		try{
			//execute the query
			Statement st = conn.createStatement();
			Debug.logln("GradesDB: Executing query : " + query);
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()){
				int assignmentid = rs.getInt("assignmentid");
				int coursecode = rs.getInt("coursecode");
				int courseyear = rs.getInt("year");
				String name = rs.getString("name");
				boolean isgradedassignment = rs.getBoolean("isgradedassignment");
				int weight = rs.getInt("weight");
				BigDecimal minimumresult = rs.getBigDecimal("minimumresult");
				Assignment a = new Assignment(assignmentid, coursecode, courseyear, name, isgradedassignment, weight, minimumresult);
				result = a;
			}
		
			rs.close();
			st.close();
		} catch (SQLException e) {
			Debug.logln("GradesDB: Oops: " + e.getMessage());
			Debug.logln("GradesDB: SQLState: " + e.getSQLState());
		}
		
		
		if (result == null){
			Debug.logln("I tried looking for an assignment with id : " + argAssignmentid + " but didn't find anything.");
		}
		return result;
	}
	
	/**
	 * Gets all the assignments from the database.
	 * @return An arraylist, containing all the assignments in the database.
	 */
	public List<Assignment> getAssignments(){
		List<Assignment> result = new ArrayList<Assignment>();
		
		String query = "SELECT * FROM Testi.assignment";
		
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
				boolean isgradedassignment = rs.getBoolean("isgradedassignment");
				int weight = rs.getInt("weight");
				BigDecimal minimumresult = rs.getBigDecimal("minimumresult");
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
	 * Gets the AssignmentResults corresponding to the assignmentid
	 * @param assignmentID The ID of the assignment to look for
	 * @return an ArrayList, containing assignmentresults with the given assignmentid's
	 */
	public List<AssignmentResult> getResultsForAssignment(int assignmentid){
		List<AssignmentResult> result = new ArrayList<AssignmentResult>();
		String query = "Select * FROM Testi.AssignmentResult ar, Testi.AssignmentOccasion ac WHERE " +
		assignmentid +  "= ac.assignmentid" + 
		" AND ac.occasionid = ar.occasionid";
		
		try{
			//execute query in the connected database
			Statement st = conn.createStatement();
			Debug.logln("GradesDB: Executing query : " + query);
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()){
				int occasionid = rs.getInt("occasionid");
				int studentid = rs.getInt("studentid");
				Date occasiondate = rs.getDate("occasiondate");
				BigDecimal assignmentresult = rs.getBigDecimal("result");
				AssignmentResult ar = new AssignmentResult(occasionid, studentid, occasiondate, assignmentresult,  assignmentid);
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
	
	/**
	 * Gets the Students that are attending the given course
	 * @param argCoursecode The ID of the SuperCourse
	 * @param argYear The year of this course
	 * @return A list of students who are following this course
	 */
	public List<Student> getStudentsForCourse(int coursecode, int year){
		List<Student> result = new ArrayList<Student>();
		String query = "SELECT DISTINCT s.studentid, p.firstname, p.surname FROM Testi.Student s, Testi.Person p, Testi.hasCourses hc, Testi.ModuleResult mr, Testi.Course c " +
		"WHERE hc.courseCode = " + coursecode +
		" "
		+ "AND c.courseCode = hc.courseCode " +
		"AND c.year = " + year +
		" AND s.studentid = mr.studentid AND mr.modulecode = hc.modulecode" + 
		" AND p.personid = s.studentid";
		try{
			//execute query in the connected database.
			Statement st = conn.createStatement();
			Debug.logln("GradesDB: Executing query : " + query);
			ResultSet rs = st.executeQuery(query);
			
			
			while(rs.next()){
				int studentid = rs.getInt("studentid");
				String firstname = rs.getString("firstname");
				String surname = rs.getString("surname");
				Student s = new Student(studentid, firstname, surname, "","");
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
	 * Gets the modules that this teacher is working on
	 * @param argDocentID The ID of the teacher.
	 * @return A list of modules where this teacher is working on
	 */
	public List<Module> getModulesForDocent(int argDocentID){
		List<Module> result = new ArrayList<Module>();
		String query = "SELECT m.modulecode, m.year, sm.name FROM Testi.module m, Testi.supermodule sm, Testi.teaches t WHERE " +
		"m.modulecode = sm.modulecode AND " +
		"t.modulecode = m.modulecode AND " +
		"t.personID = " + argDocentID;		
		
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
	 * Gets the modules that a student is doing.
	 * @param argStudentID The ID of the student.
	 * @return A list of the modules that the student is doing.
	 */
	public List<Module> getModulesForStudent(int argStudentID){
		List<Module> result = new ArrayList<Module>();
		String query = "SELECT m.modulecode, m.year, sm.name FROM Testi.module m, Testi.moduleresult mr, Testi.supermodule sm WHERE " +
		"mr.studentid = " + argStudentID + " AND " +
		"mr.modulecode = m.modulecode AND " + 
		"mr.year = m.year AND " + 
		"m.modulecode = sm.modulecode " +
		"ORDER BY m.year DESC";
		
		//SELECT m.modulecode, m.year, sm.name FROM Testi.module m, Testi.moduleresult mr, Testi.supermodule sm WHERE 
		//mr.studentid = argStudentID AND
		//mr.modulecode = m.modulecode AND 
		//mr.year = m.year AND 
		//m.modulecode = sm.modulecode
		
		
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
	 * Gets the courses that are part of a certain module.
	 * @param argModulecode The code of the module.
	 * @return A list of course that are part of this module.
	 */
	public List<Course> getCoursesForModule(int argModulecode){
		List<Course> result = new ArrayList<Course>();
		String query = "SELECT c.coursecode, c.year, sc.name, sc.weight FROM Testi.course c,Testi.hascourses hc, Testi.supercourse sc WHERE " +
		"hc.modulecode = " + argModulecode + 
		" AND c.coursecode = hc.coursecode  AND c.coursecode = sc.coursecode";
	
		//SELECT c.coursecode, c.year, sc.name, sc.weight FROM Testi.course c,Testi.hascourses hc, Testi.supercourse sc WHERE 
		//hc.modulecode = argModulecode AND 
		//c.coursecode = hc.coursecode AND 
		//c.coursecode = sc.coursecode
		
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
				Course c = new Course(coursecode, year, coursename, weight);
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
	 * @return A list of the assignments that are part of the course.
	 */
	public List<Assignment> getAssignmentsForCourse(int argCoursecode, int argCourseyear){
		List<Assignment> result = new ArrayList<Assignment>();
		String query = "SELECT * FROM Testi.assignment a WHERE "+ 
		"a.coursecode = " + argCoursecode + " AND " + 
		"a.year = " +	argCourseyear + 
		" ORDER BY assignmentid";
		
		//SELECT * FROM Testi.assignment a WHERE 
		//a.coursecode = argCoursecode AND 
		//a.courseyear = argCourseyear 
		//ORDER BY assignmentid
		
		try{
			//execute the query
			Statement st = conn.createStatement();
			Debug.logln("GradesDB: Executing query : " + query);
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()){
				int assignmentid = rs.getInt("assignmentid");
				int coursecode = rs.getInt("coursecode");
				int courseyear = rs.getInt("year");
				String name = rs.getString("name");
				boolean isgradedassignment = rs.getBoolean("isgradedassignment");
				int weight = rs.getInt("weight");
				BigDecimal minimumresult = rs.getBigDecimal("minimumresult");
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
		
		//SELECT * FROM Testi.assignmentoccasion ao WHERE
		//ao.assignmentid = argAssignmentid
		
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
				result.add(ao);
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
	 * @return A list of AssigmentResults (this should be all the results a student got for one assignment,
	 * including re-sits etc.)
	 */
	public List<AssignmentResult> getResultsForAssignmentAndStudent(int argStudentid, int argAssignmentid){
		List<AssignmentResult> result = new ArrayList<AssignmentResult>();
		
		// SELECT ar.*,ao.occasiondate FROM Testi.assignmentresult ar, Testi.assignmentoccasion ao WHERE 
		// ar.studentid = argStudentid AND  
		// ao.assignmentid = argAssignmentid AND 
		// ao.occasionid = ar.occasionid
		
		String query = "SELECT ar.*,ao.occasiondate,ao.assignmentID FROM Testi.assignmentresult ar, Testi.assignmentoccasion ao WHERE "
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
				BigDecimal grade = rs.getBigDecimal("result");
				Date date = rs.getDate("occasiondate");
				AssignmentResult ar = new AssignmentResult(occasionid, argStudentid, date,grade, argAssignmentid);
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
	
	/**
	 * Gets a specific module result for a given student and a module he/she did.
	 * @param argStudentid The student id.
	 * @param argModuleCode The code of the module.
	 * @param argModuleYear The year in which the module was given.
	 * @return The result of a student and the module he did.
	 */
	public ModuleResult getModuleResult(int argStudentid, int argModuleCode, int argModuleYear){
		ModuleResult result = null;
		
//		SELECT mr.* FROM Testi.ModuleResult mr WHERE 
//		mr.studentid = argStudentid AND 
//		mr.modulecode = argModuleCode AND
//		mr.year = argModuleYear
		
		String query = "SELECT mr.* FROM Testi.ModuleResult mr WHERE " +
				"mr.studentid = " + argStudentid + " AND " +
				"mr.modulecode = " + argModuleCode + " AND " +
				"mr.year = " + argModuleYear;
		
		try{
			//execute the query
			Statement st = conn.createStatement();
			Debug.logln("GradesDB: Executing query : " + query);
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				int studentid = rs.getInt("studentid");
				int modulecode = rs.getInt("modulecode");
				int year = rs.getInt("year");
				BigDecimal moduleresult = rs.getBigDecimal("result");
				result = new ModuleResult(studentid,modulecode,year,moduleresult);
			}
		
			rs.close();
			st.close();
		} catch (SQLException e) {
			Debug.logln("GradesDB: Oops: " + e.getMessage());
			Debug.logln("GradesDB: SQLState: " + e.getSQLState());
		}
		
		
		
		if (result == null){
			Debug.logln("GradesDB: I tried looking for a module result with studentid:" + argStudentid + " module code : " + argModuleCode + " module year " + argModuleYear);
		}
		return result;
		
	}
	
	/**
	 * Gets all module results for a student
	 * @param argStudentid The student id of the student.
	 * @return A list of Module results for a certain student.
	 */
	public List<ModuleResult> getModuleResultsForStudent(int argStudentid){
		List<ModuleResult> result = new ArrayList<ModuleResult>();
//		SELECT mr.* FROM Testi.ModuleResult mr WHERE 
//		mr.studentid = argStudentid
		String query = "SELECT mr.* FROM Testi.ModuleResult mr WHERE " + 
		"mr.studentid = " + argStudentid;
		try{
			//execute the query
			Statement st = conn.createStatement();
			Debug.logln("GradesDB: Executing query : " + query);
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				int studentid = rs.getInt("studentid");
				int modulecode = rs.getInt("modulecode");
				int year = rs.getInt("year");
				BigDecimal moduleresult = rs.getBigDecimal("result");
				result.add(new ModuleResult(studentid,modulecode,year,moduleresult));
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
	 * Gets the module results for a certain module
	 * @param argModulecode The code of the module.
	 * @param argModuleyear The year in which the module is given.
	 * @return A list of Module results for the module.
	 */
	public List<ModuleResult> getModuleResultsForModule(int argModulecode, int argModuleyear){
		List<ModuleResult> result = new ArrayList<ModuleResult>();
//		SELECT mr.* FROM Testi.ModuleResult mr WHERE 
//		mr.modulecode = argModulecode
//		mr.year = argModuleYear
		String query = "SELECT mr.* FROM Testi.ModuleResult mr WHERE " +
		"mr.modulecode = " + argModulecode + " AND " +
		"mr.year = " + argModuleyear;
		try{
			//execute the query
			Statement st = conn.createStatement();
			Debug.logln("GradesDB: Executing query : " + query);
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				int studentid = rs.getInt("studentid");
				int modulecode = rs.getInt("modulecode");
				int year = rs.getInt("year");
				BigDecimal moduleresult = rs.getBigDecimal("result");
				result.add(new ModuleResult(studentid,modulecode,year,moduleresult));
			}
		
			rs.close();
			st.close();
		} catch (SQLException e) {
			Debug.logln("GradesDB: Oops: " + e.getMessage());
			Debug.logln("GradesDB: SQLState: " + e.getSQLState());
		}
		return result;
	}
	
	/*
	 *CREATE
	 */
	
	/**
	 * Adds a teacher to the database.
	 * @param t The teacher to add.
	 */
	public void addTeacher(Teacher t){
		//because student numbers always start with a s, remove it in order to get the integer; personid.
		int personid_int = Integer.parseInt(t.getPersonID().substring(1)); //convert the string without the first character to an integer.
		String query = "INSERT INTO Testi.person(personid,firstname,surname,password,salt) " + 
		"VALUES (?,?,?,?,?)";
		String query2 = "INSERT INTO Testi.teacher (teacherid,administrator) VALUES (?,?)";
		//No dependencies
		
		try{
			//prepare the two queries && set auto-commit to false;
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(query);
			PreparedStatement ps2 = conn.prepareStatement(query2);
			ps.setInt(1, personid_int);
			ps.setString(2,t.getFirstname());
			ps.setString(3,t.getSurname());
			ps.setString(4, t.getPassword());
			ps.setString(5, t.getSalt());
			ps2.setInt(1,personid_int);
			ps2.setBoolean(2, t.isManager());
			//Execute the two queries
			Debug.logln("GradesDB: Executing query 1 : " + ps.toString());
			ps.executeUpdate();
			Debug.logln("GradesDB: Executing query 2 : " + ps2.toString());
			ps2.executeUpdate();
			ps.close();
			ps2.close();
			//commit the queries && reset auto-commit
			conn.commit();
		} catch (SQLException e) {
			Debug.logln("GradesDB: Oops: " + e.getMessage());
			Debug.logln("GradesDB: SQLState: " + e.getSQLState());
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}	
	
	/**
	 * Adds a student to the database.
	 * @param s the student to add.
	 * @throws  
	 */
	public void addStudent(Student s){
		//because student numbers always start with a s, remove it in order to get the integer; personid.
		int personid_int = Integer.parseInt(s.getPersonID().substring(1)); //convert the string without the first character to an integer.
		String query = "INSERT INTO Testi.person(personid,firstname,surname,password,salt) " + 
		"VALUES (?,?,?,?,?)";
		String query2 = "INSERT INTO Testi.student (studentid) VALUES (?)";
		//No dependencies
		
		
		try{
			//prepare the two queries && set autocommit to false
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(query);
			PreparedStatement ps2 = conn.prepareStatement(query2);
			ps.setInt(1, personid_int);
			ps.setString(2,s.getFirstname());
			ps.setString(3,s.getSurname());
			ps.setString(4, s.getPassword());
			ps.setString(5, s.getSalt());
			ps2.setInt(1,personid_int);
			//exequte the queries
			Debug.logln("GradesDB: Executing statement:" + ps.toString());
			ps.executeUpdate();
			Debug.logln("GradesDB: Executing statement:" + ps2.toString());
			ps2.executeUpdate();
			ps.close();
			ps2.close();
			//commit the queries && reset autocommit
			conn.commit();
			
		} catch (SQLException e) {
			Debug.logln("GradesDB: Oops: " + e.getMessage());
			Debug.logln("GradesDB: SQLState: " + e.getSQLState());
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}	
	
	/**
	 * Adds a SuperCourse to the Database
	 * @param sc The SuperCourse to add to the Database
	 */
	public void addSuperCourse(SuperCourse sc){
			String query = "INSERT INTO Testi.supercourse(coursecode,name,weight)" +  
			"VALUES(?,?,?)";
			//No dependencies
			try{
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setInt(1,sc.getCourseCode());
				ps.setString(2, sc.getName());
				ps.setInt(3, sc.getWeight());
				Debug.logln("GradesDB: executing statement: " + ps.toString());
				ps.executeUpdate();
				ps.close();
			}  catch (SQLException e) {
				Debug.logln("GradesDB: Oops: " + e.getMessage());
				Debug.logln("GradesDB: SQLState: " + e.getSQLState());
			}	
			
	}
	
	/**
	 * Adds a Course to the Database
	 * @param c The Course to be added to the Database
	 */
	public void addCourse(Course c){
		String query = "INSERT INTO Testi.Course(coursecode,year) " + 
		"VALUES (?,?)";
		//Note that: Coursecode, for the supercourse must exist.
		//also; the (coursecode,year) tuple should not exist before.
		try{
			//prepare the query
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1,c.getCourseCode());
			ps.setInt(2, c.getYear());
			Debug.logln("GradesDB: Executing statement: " + ps.toString());
			ps.executeUpdate();
			ps.close();
		}  catch (SQLException e) {
			Debug.logln("GradesDB: Oops: " + e.getMessage());
			Debug.logln("GradesDB: SQLState: " + e.getSQLState());
		}	
		
	}
	
	/**
	 * Adds an Assignment to the Database
	 * @param a The assignment to be added to the Database
	 */
	public void addAssignment(Assignment a){
		String query = "INSERT INTO Testi.assignment(assignmentid, coursecode,year, name,isgradedassignment, weight, minimumresult)" + 
		"VALUES(?,?,?,?,?,?,?)";
		//Note that, when creating an assignment, it has to be part of a course,
		//therefore, a valid (coursecode,courseyear) tuple has to be given for the assignment to be added succesfully!
		try{
			//prepare the query
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, a.getAssignmentID());
			ps.setInt(2, a.getCourseCode());
			ps.setInt(3, a.getCourseyear());
			ps.setString(4, a.getName());
			ps.setBoolean(5,a.getGraded());
			ps.setInt(6, a.getWeight());
			ps.setBigDecimal(7, a.getMinimumresult()); //BigDecimal is the recommended java mapping for numeric values in SQL. (https://docs.oracle.com/javase/1.5.0/docs/guide/jdbc/getstart/mapping.html Paragraph 8.3.11)
			Debug.logln("GradesDB: Executing statement : " + ps.toString());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			Debug.logln("GradesDB: Oops: " + e.getMessage());
			Debug.logln("GradesDB: SQLState: " + e.getSQLState());
		}	
		
	}
	
	/**
	 * Adds an AssignmentOccasion to the Database
	 * @param ao The AssignmentOccasion to be added to the Database
	 */
	public void addAssignmentOccasion(AssignmentOccasion ao){
		String query = "INSERT INTO Testi.assignmentoccasion(assignmentid, occasionid, occasiondate) " + 
		"VALUES (?,?,?)";
		//Assignmentid for which the ao counts must exist beforehand
		try{
			//prepare the query
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, ao.getAssignmentid());
			ps.setInt(2, ao.getOccasionid());
			ps.setDate(3, ao.getOccasiondate());
			//execute it
			Debug.logln("GradesDB: Executing statement: " + ps.toString());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			Debug.logln("GradesDB: Oops: " + e.getMessage());
			Debug.logln("GradesDB: SQLState: " + e.getSQLState());
		}
	}
	
	/**
	 * Adds an AssignmentResult to the Database
	 * @param ar The AssignmentResult to be added to the Database
	 */
	//TODO: test
	public void addAssignmentResult(AssignmentResult ar){
		String query = "INSERT INTO Testi.assignmentresult(occasionid,studentid,result) "+ 
	"VALUES (?,?,?)";
		//Note that; 	1. Occasion for which the ar counts, must exist beforehand
		//				2. Student for which the ar counts, must exist beforehand
		//				3. (Occasionid,studentid) must be unique tuple in table
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1,ar.getOccasionid());
			ps.setInt(2, ar.getStudentid());
			ps.setBigDecimal(3, ar.getResult());
			Debug.logln("GradesDB: Executing statement: " + ps.toString());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e){
			Debug.logln("GradesDB: Oops: " + e.getMessage());
			Debug.logln("GradesDB: SQLState: " + e.getSQLState());
		}
	}
	/**
	 * Adds a SuperModule to the Database
	 * @param sm The SuperModule to be added to the Database
	 */
	public void addSuperModule(SuperModule sm){
		String query = "INSERT INTO Testi.supermodule(modulecode,name) " +
		"VALUES(?,?)";
		//no dependencies.
		try{
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, sm.getModulecode());
			ps.setString(2, sm.getName());
			Debug.logln("GradesDB: Executing statement: " + ps.toString());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e){
			Debug.logln("GradesDB: Oops: " + e.getMessage());
			Debug.logln("GradesDB: SQLState: " + e.getSQLState());
		}
	}
	/**
	 * Adds a Module to the Database
	 * @param m The Module to be added to the Database
	 */
	public void addModule(Module m){
		String query = "INSERT INTO Testi.module(modulecode,year) " + 
		"VALUES(?,?)";
		//Note that, super module with the module code must exist before making a module.
		try{
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, m.getModulecode());
			ps.setInt(2, m.getYear());
			Debug.logln("GradesDB: Executing statement: " + ps.toString());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e){
			Debug.logln("GradesDB: Oops: " + e.getMessage());
			Debug.logln("GradesDB: SQLState: " + e.getSQLState());
		}
	}
	/**
	 * Adds a ModuleResult to the Database
	 * @param mr The ModuleResult to be added to the Database
	 */
	public void addModuleResult(ModuleResult mr){
		String query = "INSERT INTO Testi.moduleresult(studentid,modulecode,year,result) " + 
		"VALUES(?,?,?,?)";
		//Note that:
		//1. Student id, for which the module result counts, must exist beforehand.
		//2. the combination (Modulecode,year) needs to be a unique tuple for the student. (a student can't do the same module in the same year twice)
		//3. the exact combination (Modulecode,year) for which the mr counts, must exist beforehand 
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1,mr.getStudentID());
			ps.setInt(2, mr.getModuleCode());
			ps.setInt(3, mr.getYear());
			ps.setBigDecimal(4, mr.getResult());
			Debug.logln("GradesDB: Executing statement: " + ps.toString());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e){
			Debug.logln("GradesDB: Oops: " + e.getMessage());
			Debug.logln("GradesDB: SQLState: " + e.getSQLState());
		}
				
	}
	
	/**
	 * Add a Course to a Module
	 * @param argModulecode The code of the module
	 * @param argCoursecode The code of the Course
	 */
//	//TODO: AddCourseToModule
	public void addCourseToModule(int argModulecode, int argCoursecode){
		String query = "INSERT INTO Testi.hascourses(modulecode, coursecode) " +
	"VALUES(?,?)";
		//Note that:
		//1. The modulecode for the module has to exist.
		//2. The coursecode for the course has to exist.
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1,argModulecode);
			ps.setInt(2, argCoursecode);
			Debug.logln("GradesDB: Executing statement: " + ps.toString());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e){
			Debug.logln("GradesDB: Oops: " + e.getMessage());
			Debug.logln("GradesDB: SQLState: " + e.getSQLState());
		}
	
	}
	
	/**
	 * Add a teacher to a module, according to the TeacherModule class
	 * @param TeacherModule This class contains a teacher, a module, and the type of teacher
	 * that they are in this module
	 */
	public void addTeacherToModule(TeacherModule TeacherModule){
		String query = "INSERT INTO Testi.teaches(personid, modulecode, year, type) " +
	"VALUES(?,?,?,?)";
		//Note that:
		//1. the modulecode for the module has to exist
		//2. the year for the module has to exist
		//3. the personID for the teacher has to exist
		//4. the type has to be one of the standard types (Docent, Medewerker, Studentassistent)
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(TeacherModule.getTeacher().getPersonID().substring(1)));
			ps.setInt(2, TeacherModule.getModule().getModulecode());
			ps.setInt(3, TeacherModule.getModule().getYear());
			ps.setString(4, TeacherModule.getType());
			Debug.logln("GradesDB: Executing statement: " + ps.toString());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e){
			Debug.logln("GradesDB: Oops: " + e.getMessage());
			Debug.logln("GradesDB: SQLState: " + e.getSQLState());
		}
	}
	
	/*
	 *DELETE 
	 */
	/**
	 * Deletes a Teacher from the database (but not the actual person)
	 * @param personid The ID of this teacher
	 */
	public void deleteTeacher(String personid){
		int personid_int = Integer.parseInt(personid.substring(1));
		String query = "DELETE FROM Testi.Teacher t WHERE t.teacherid = (?)";
		try{
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, personid_int);
			Debug.logln("GradesDB: Executing statement: " + ps.toString());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e){
			Debug.logln("GradesDB: Oops: " + e.getMessage());
			Debug.logln("GradesDB: SQLState:" + e.getSQLState());
		}
		
	}
	
	/** Deletes a Student from the database (but not the actual person)
	 * @param personid The ID of this Student
	 */
	public void deleteStudent(String personid){
		int personid_int = Integer.parseInt(personid.substring(1));
		String query = "DELETE FROM Testi.Student s WHERE s.studentid = (?)";
		try{
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, personid_int);
			Debug.logln("GradesDB: Executing statement: " + ps.toString());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e){
			Debug.logln("GradesDB: Oops: " + e.getMessage());
			Debug.logln("GradesDB: SQLState:" + e.getSQLState());
		}
	}
	/** Deletes a Person from the database
	 * @param personid the ID of this person
	 */
	public void deletePerson(String personid){
		int personid_int = Integer.parseInt(personid.substring(1));
		String query3 = "DELETE FROM Testi.Person p WHERE p.personid = (?)";
		String query1 = "DELETE FROM Testi.Student s WHERE s.studentid = (?)";
		String query2 = "DELETE FROM Testi.Teacher t WHERE t.teacherid = (?)";
		try{
			conn.setAutoCommit(false);
			PreparedStatement ps1 = conn.prepareStatement(query1);
			PreparedStatement ps2 = conn.prepareStatement(query2);
			PreparedStatement ps3 = conn.prepareStatement(query3);
			ps1.setInt(1, personid_int);
			ps2.setInt(1, personid_int);
			ps3.setInt(1, personid_int);
			Debug.logln("GradesDB: Executing statement: " + ps1.toString());
			ps1.executeUpdate();
			Debug.logln("GradesDB: Executing statement: " + ps2.toString());
			ps2.executeUpdate();
			Debug.logln("GradesDB: Executing statement: " + ps3.toString());
			ps3.executeUpdate();
			conn.commit();
			ps1.close();
			ps2.close();
			ps3.close();
			
		} catch (SQLException e){
			Debug.logln("GradesDB: Oops: " + e.getMessage());
			Debug.logln("GradesDB: SQLState:" + e.getSQLState());
	} finally {
		try {
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}
	/**
	 * Delete a Module from the Database
	 * @param modulecode The code of the Module
	 * @param year The Year of the Module
	 */
	public void deleteModule(int modulecode, int year){
		String query = "DELETE FROM Testi.Module m WHERE m.modulecode = (?) " +
					"AND m.year = (?)";
		try{
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, modulecode);
			ps.setInt(2, year);
			Debug.logln("GradesDB: Executing statement: " + ps.toString());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e){
			Debug.logln("GradesDB: Oops: " + e.getMessage());
			Debug.logln("GradesDB: SQLState:" + e.getSQLState());
	}
	}

	/**
	 * Delete a Supermodule from the DataBase (alongside its regular modules
	 * @param modulecode The code of the Supermodule to be deleted
	 */
	public void deleteSuperModule(int modulecode){
		String query1 = "DELETE FROM Testi.SuperModule sm WHERE sm.modulecode = (?)";
		String query2 = "DELETE FROM Testi.Module m WHERE m.modulecode = (?)";
		try{
			conn.setAutoCommit(false);
			PreparedStatement ps1 = conn.prepareStatement(query2);
			PreparedStatement ps2 = conn.prepareStatement(query1);
			ps1.setInt(1, modulecode);
			ps2.setInt(1, modulecode);
			Debug.logln("GradesDB: Executing statement: " + ps1.toString());
			ps1.executeUpdate();
			Debug.logln("GradesDB: Executing statement: " + ps2.toString());
			ps2.executeUpdate();
			conn.commit();
			ps1.close();
			ps2.close();
		} catch (SQLException e){
			Debug.logln("GradesDB: Oops: " + e.getMessage());
			Debug.logln("GradesDB: SQLState:" + e.getSQLState());
	} finally {
		try {
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}
	/**
	 * Delete a single course from the DataBase
	 * @param coursecode The code of this Course
	 * @param year The year of this course
	 */
	public void deleteCourse(int coursecode, int year){
		String query = "DELETE FROM Testi.Course c WHERE c.coursecode = (?) AND c.year = (?)";
		try{
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, coursecode);
			ps.setInt(2, year);
			Debug.logln("GradesDB: Executing statement: " + ps.toString());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e){
			Debug.logln("GradesDB: Oops: " + e.getMessage());
			Debug.logln("GradesDB: SQLState:" + e.getSQLState());
	}
	}
	
	/**
	 * Delete a SuperCourse from the Database
	 * @param coursecode The code of the SuperCourse
	 */
	public void deleteSuperCourse(int coursecode){
		String query1 = "DELETE FROM Testi.Course c WHERE c.coursecode = (?)";
		String query2 = "DELETE FROM Testi.SuperCourse sc WHERE sc.coursecode = (?)";
		try{
			conn.setAutoCommit(false);
			PreparedStatement ps1 = conn.prepareStatement(query1);
			PreparedStatement ps2 = conn.prepareStatement(query2);
			ps1.setInt(1, coursecode);
			ps2.setInt(1, coursecode);
			Debug.logln("GradesDB: Executing statement: " + ps1.toString());
			ps1.executeUpdate();
			Debug.logln("GradesDB: Executing statement: " + ps2.toString());
			ps2.executeUpdate();
			conn.commit();
			ps1.close();
			ps2.close();	
		} catch (SQLException e){
			Debug.logln("GradesDB: Oops: " + e.getMessage());
			Debug.logln("GradesDB: SQLState:" + e.getSQLState());
	} finally {
		try {
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}
	
	/**
	 * Delete an assignment occasion from the Database
	 * @param OccasionID The OccasionID of this AssigmentOccasion
	 * @param AssignmentID The AssignmentID of this AssignmentOccasion
	 */
	public void deleteAssignmentOccasion(int OccasionID, int AssignmentID){
		String query = "DELETE FROM Testi.AssignmentOccasion ao WHERE ao.occasionid = (?) " +
					"AND ao.assingmentid = (?)";
		try{
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, OccasionID);
			ps.setInt(2, AssignmentID);
			Debug.logln("GradesDB: Executing statement: " + ps.toString());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e){
			Debug.logln("GradesDB: Oops: " + e.getMessage());
			Debug.logln("GradesDB: SQLState:" + e.getSQLState());
	}
	}
	
	/**
	 * Delete an assignment and its occasions from the database
	 * @param assignmentID The ID of the assignment to be removed
	 */
	public void deleteAssignment(int assignmentID){
		String query1 = "DELETE FROM Testi.AssignmentOccasion ao WHERE ao.assignmentid = (?)";
		String query2 = "DELETE FROM Testi.Assignment a WHERE a.assignmentid = (?)";
		try{
			conn.setAutoCommit(false);
			PreparedStatement ps1 = conn.prepareStatement(query1);
			PreparedStatement ps2 = conn.prepareStatement(query2);
			ps1.setInt(1, assignmentID);
			ps2.setInt(1, assignmentID);
			Debug.logln("GradesDB: Executing statement: " + ps1.toString());
			ps1.executeUpdate();
			Debug.logln("GradesDB: Executing statement: " + ps2.toString());
			ps2.executeUpdate();
			conn.commit();
			ps1.close();
			ps2.close();	
		} catch (SQLException e){
			Debug.logln("GradesDB: Oops: " + e.getMessage());
			Debug.logln("GradesDB: SQLState:" + e.getSQLState());
	} finally {
		try {
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}
	
	/*
	 *  UPDATE
	 */
	
	/**
	 * Modify an AssignmentResult
	 * @param argOccasionid The occasion id for the AssignmentResult
	 * @param argStudentid The student id for the AssignmentResult
	 * @param argAssignmentResult The AssignmentResult object containing the new values for the database.
	 */
	public void modifyAssignmentResult(int argOccasionid, int argStudentid, AssignmentResult argAssignmentResult){
		//UPDATE Testi.assignmentresult ar SET result =  WHERE 
		//ar.occasionid = AND
		//		ar.studentid = 
		String query = "UPDATE Testi.assignmentresult ar SET result = (?) WHERE " + 
		"ar.occasionid = (?) AND " +
		"ar.studentid = (?)";
		
		try{
			Debug.logln("GradesDB : Preparing statement : " + query);
			PreparedStatement ps = conn.prepareStatement(query);
			//Prepare the statement
			ps.setBigDecimal(1, argAssignmentResult.getResult()); 
			ps.setInt(2, argOccasionid); 
			ps.setInt(3, argStudentid);
			Debug.logln("GradesDB: Executing statement " + ps.toString());
			//Execute and close the prepared statement.
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e){
		Debug.logln("GradesDB: Oops: " + e.getMessage());
		Debug.logln("GradesDB: SQLState:" + e.getSQLState());	
		}
	}
	
	/**
	 * Modifies an assignment occasion.
	 * @param argOccasionid The identifier of the assignment occasion.
	 * @param argAssignmentOccasion The AssignmentOccasion object containing the new information for the database.
	 */
	public void modifyAssignmentOccasion(int argOccasionid, AssignmentOccasion argAssignmentOccasion){
		String query = "UPDATE Testi.assignmentoccasion ao SET assignmentid = (?), occasiondate = (?) " +
		"WHERE ao.occasionid (?)";
		
		try{
			Debug.logln("GradesDB : Preparing statement : " + query);
			PreparedStatement ps = conn.prepareStatement(query);
			//Prepare the statement.
			ps.setInt(1, argAssignmentOccasion.getAssignmentid()); 
			ps.setDate(2, argAssignmentOccasion.getOccasiondate()); 
			ps.setInt(3, argOccasionid);
			Debug.logln("GradesDB: Executing statement " + ps.toString());
			//Execute and close the prepared statement.
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e){
		Debug.logln("GradesDB: Oops: " + e.getMessage());
		Debug.logln("GradesDB: SQLState:" + e.getSQLState());	
		}
	}
	
	/**
	 * Modify an assignment.
	 * @param argAssignmentid The Identifier for the assignment to be modified.
	 * @param argAssignment The Assignment object containing the new information to be added to the database.
	 */
	public void modifyAssignment(int argAssignmentid, Assignment argAssignment){
		String query = "UPDATE Testi.assignment a SET coursecode = (?), year = (?), name = (?), isgradedassignment=(?), weight = (?), minimumresult=(?) " + 
		"WHERE a.assignmentid = (?)";
		
		try{
			Debug.logln("GradesDB : Preparing statement : " + query);
			PreparedStatement ps = conn.prepareStatement(query);
			//Prepare the parameters.
			ps.setInt(1, argAssignment.getCourseCode());
			ps.setInt(2, argAssignment.getCourseyear());
			ps.setString(3, argAssignment.getName());
			ps.setBoolean(4, argAssignment.getGraded());
			ps.setInt(5, argAssignment.getWeight());
			ps.setBigDecimal(6, argAssignment.getMinimumresult());
			ps.setInt(7,argAssignmentid);
			Debug.logln("GradesDB: Executing statement " + ps.toString());
			//Execute and close the prepared statement.
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e){
		Debug.logln("GradesDB: Oops: " + e.getMessage());
		Debug.logln("GradesDB: SQLState:" + e.getSQLState());	
		}
		
	}
	
	//ModifyCourse is not added, because all of the values stored in the course table
	//are used as the primary key, therefore, there is nothing to change, without changing the identity of the course.
	
	/**
	 * Modify a SuperCourse
	 * @param argCoursecode
	 * @param argSuperCourse
	 */
	public void modifySupercourse(int argCoursecode, SuperCourse argSuperCourse){
		String query = "UPDATE Testi.supercourse sc SET name = (?), weight = (?) WHERE sc.coursecode = (?)";
		try{
			Debug.logln("GradesDB : Preparing statement : " + query);
			PreparedStatement ps = conn.prepareStatement(query);
			//Prepare the parameters.
			ps.setString(1, argSuperCourse.getName());
			ps.setInt(2, argSuperCourse.getWeight());
			ps.setInt(3, argCoursecode);
			Debug.logln("GradesDB: Executing statement " + ps.toString());
			//Execute and close the prepared statement.
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e){
		Debug.logln("GradesDB: Oops: " + e.getMessage());
		Debug.logln("GradesDB: SQLState:" + e.getSQLState());	
		}
	}

	//ModifyModule is not added, because all of the values stored in the module table
	//are used as the primary key, therefore, there is nothing to change, without changing the identity of the module.
		
	
	/**
	 * Modify a SuperModule
	 * @param argModulecode The module code of the SuperModule to be modified.
	 * @param argSuperModule The SuperModule-object containing the new information.
	 */
	public void modifySuperModule(int argModulecode, SuperModule argSuperModule){
		String query = "Update Testi.supermodule sm SET name = (?) WHERE sm.modulecode = (?)";
		try{
			Debug.logln("GradesDB : Preparing statement : " + query);
			PreparedStatement ps = conn.prepareStatement(query);
			//Prepare the parameters.
			ps.setString(1, argSuperModule.getName());
			ps.setInt(2, argModulecode);
			Debug.logln("GradesDB: Executing statement " + ps.toString());
			//Execute and close the prepared statement.
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e){
		Debug.logln("GradesDB: Oops: " + e.getMessage());
		Debug.logln("GradesDB: SQLState:" + e.getSQLState());	
		}
	}
	
	
}


