package nl.utwente.di.gradeManager.db;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
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
	
	/*
	 * READ
	 */
	
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
		
		//SELECT * FROM Testi.course c WHERE 
		//c.coursecode = argCoursecode  AND 
		//c.year = argCourseyear
		
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
				Student s = new Student(personid, firstname, surname, password);
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
	 * Gets the students from the database.
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
				boolean administrator = rs.getBoolean("administrator");
				Teacher t = new Teacher(personid, firstname, surname, password,administrator);
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
	 * Gets the teachers from the database.	
	 * @return A list of teachers, as in the database.
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
				boolean administrator = rs.getBoolean("administrator");
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
	 * Gets a module with a specific module code.
	 * @param argModulecode The code of the module.
	 * @return The module which has the specific code.
	 */
	public Module getModule(int argModulecode){
		Module result = null;
		String query = "SELECT m.modulecode, m.year, sm.name FROM Testi.module m, Testi.supermodule sm WHERE "
				+ "m.modulecode = sm.modulecode AND " 
				+ "m.modulecode = " + argModulecode;
		//SELECT m.modulecode, m.year, sm.name FROM Testi.module m, Testi.supermodule sm WHERE
		//m.modulecode = sm.modulecode AND
		//m.modulecode = argModulecode
		
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
				result = m;
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
				int courseyear = rs.getInt("courseyear");
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
	 * Gets the assignments from the database.
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
	 * Gets the modules, which a student is doing.
	 * @param argStudentID The ID of the student.
	 * @return A list of modules, which the student is doing.
	 */
	public List<Module> getModulesForStudent(int argStudentID){
		List<Module> result = new ArrayList<Module>();
		String query = "SELECT m.modulecode, m.year, sm.name FROM Testi.module m, Testi.moduleresult mr, Testi.supermodule sm WHERE " +
		"mr.studentid = " + argStudentID + " AND " +
		"mr.modulecode = m.modulecode AND " + 
		"mr.year = m.year AND " + 
		"m.modulecode = sm.modulecode";
		
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
	 * Gets the courses which are part of a certain module.
	 * @param argModulecode The code of the module.
	 * @return The courses which are part of the module.
	 */
	public List<Course> getCoursesForModule(int argModulecode){
		List<Course> result = new ArrayList<Course>();
		String query = "SELECT c.* FROM Testi.course c,Testi.hascourses hc WHERE " +
		"hc.modulecode = " + argModulecode + 
		" AND c.coursecode = hc.coursecode AND c.year = hc.courseyear";
	
		//SELECT c.* FROM Testi.course c,Testi.hascourses hc WHERE 
		//hc.modulecode = argModulecode AND 
		//c.coursecode = hc.coursecode AND 
		//c.year = hc.courseyear
		
		
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
	 * @return
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
	 * @param s The teacher to add.
	 */
	public void addTeacher(Teacher t){
		//because student numbers always start with a s, remove it in order to get the integer; personid.
		int personid_int = Integer.parseInt(t.getPersonID().substring(1)); //convert the string without the first character to an integer.
		String query = "INSERT INTO Testi.person(personid,firstname,surname,password) " + 
		"VALUES (?,?,?,?)";
		String query2 = "INSERT INTO Testi.teacher (teacherid,administrator) VALUES (?,?)";
		//No dependencies
		try{
			//prepare the two queries.
			PreparedStatement ps = conn.prepareStatement(query);
			PreparedStatement ps2 = conn.prepareStatement(query2);
			ps.setInt(1, personid_int);
			ps.setString(2,t.getFirstname());
			ps.setString(3,t.getSurname());
			ps.setString(4, t.getPassword());
			ps2.setInt(1,personid_int);
			ps2.setBoolean(2, t.isManager()); 
			Debug.logln("GradesDB: Executing query 1 : " + ps.toString());
			ps.executeUpdate();
			Debug.logln("GradesDB: Executing query 2 : " + ps2.toString());
			ps2.executeUpdate();
			ps.close();
			ps2.close();
		} catch (SQLException e) {
			Debug.logln("GradesDB: Oops: " + e.getMessage());
			Debug.logln("GradesDB: SQLState: " + e.getSQLState());
		}		
	}	
	
	/**
	 * Adds a student to the database.
	 * @param s the student to add.
	 */
	public void addStudent(Student s){
		//because student numbers always start with a s, remove it in order to get the integer; personid.
		int personid_int = Integer.parseInt(s.getPersonID().substring(1)); //convert the string without the first character to an integer.
		String query = "INSERT INTO Testi.person(personid,firstname,surname,password) " + 
		"VALUES (?,?,?,?)";
		String query2 = "INSERT INTO Testi.student (studentid) VALUES (?)";
		//No dependencies
		try{
			//prepare the two queries.
			PreparedStatement ps = conn.prepareStatement(query);
			PreparedStatement ps2 = conn.prepareStatement(query2);
			ps.setInt(1, personid_int);
			ps.setString(2,s.getFirstname());
			ps.setString(3,s.getSurname());
			ps.setString(4, s.getPassword());
			ps2.setInt(1,personid_int);
			Debug.logln("GradesDB: Executing prepared statement 1.");
			ps.executeUpdate();
			Debug.logln("GradesDB: Executing prepared statement 2.");
			ps2.executeUpdate();
			ps.close();
			ps2.close();
		} catch (SQLException e) {
			Debug.logln("GradesDB: Oops: " + e.getMessage());
			Debug.logln("GradesDB: SQLState: " + e.getSQLState());
		}		
	}	
	
	//TODO: test
	public void addCourse(Course c){
		String query = "INSERT INTO Testi.Course(coursecode,name,weight,year) " + 
		"VALUES (?,?,?,?)";
		//No dependencies
		try{
			//prepare the query
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1,c.getCode());
			ps.setString(2,c.getName());
			ps.setInt(3,c.getWeight());
			ps.setInt(4, c.getYear());
			Debug.logln("GradesDB: Executing statement: " + ps.toString());
			ps.executeUpdate();
			ps.close();
		}  catch (SQLException e) {
			Debug.logln("GradesDB: Oops: " + e.getMessage());
			Debug.logln("GradesDB: SQLState: " + e.getSQLState());
		}	
		
	}
	
	//TODO: test
	public void addAssignment(Assignment a){
		String query = "INSERT INTO Testi.assignment(assignmentid, coursecode, courseyear, name,isgradedassignment, weight, minimumresult)" + 
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

	//TODO: test
	public void addAssignmentOccasion(AssignmentOccasion ao){
		String query = "INSERT INTO Testi.assignmentoccasion(assignmentid,occasiondate) " + 
		"VALUES (?,?)";
		//Assignmentid for which the ao counts must exist beforehand
		try{
			//prepare the query
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1,ao.getAssignmentid());
			ps.setDate(2,ao.getOccasiondate());
			//execute it
			Debug.logln("GradesDB: Executing statement : " + ps.toString());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			Debug.logln("GradesDB: Oops: " + e.getMessage());
			Debug.logln("GradesDB: SQLState: " + e.getSQLState());
		}
	}
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
	//TODO: test
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
	//TODO: test
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
	//TODO: test
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
		}
		
		
		
	}
	
}
