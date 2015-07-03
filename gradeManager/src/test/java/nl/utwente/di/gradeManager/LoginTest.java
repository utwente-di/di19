package nl.utwente.di.gradeManager;

import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;

import nl.utwente.di.gradeManager.db.GradesDB;
import nl.utwente.di.gradeManager.db.LoginDB;
import nl.utwente.di.gradeManager.helpers.Security;
import nl.utwente.di.gradeManager.model.*;

import org.junit.*;

public class LoginTest {

	private Student student;
	private Teacher teacher;
	private Teacher manager;
	
	private String salt_student;
	private String salt_teacher;
	private String salt_manager;
	
	@Before
	public void setup(){
		try {
			//Generate salts for the student, teacher and manager
			salt_student = Security.getSalt();
			salt_teacher = Security.getSalt();
			salt_manager = Security.getSalt();
			//Now, generate a secure password for them.
			String pw_student = Security.getSHA512("student", salt_student);
			String pw_teacher = Security.getSHA512("teacher", salt_teacher);
			String pw_manager = Security.getSHA512("manager", salt_manager);
			
			student = new Student(9999999, "Test", "Student", pw_student, salt_student);
			teacher = new Teacher(9999998, "Test", "Teacher", pw_teacher, salt_teacher, false);
			manager = new Teacher(9999997, "Test", "Manager", pw_manager, salt_manager, true);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testLogin(){

		LoginDB loginDB = new LoginDB();
		GradesDB gradesDB = new GradesDB();
		try{
			//add the accounts to the database.
			gradesDB.addStudent(student);
			gradesDB.addTeacher(teacher);
			gradesDB.addTeacher(manager);
			
			//generate objects based on the database information.
			Student student_db = gradesDB.getStudent(9999999);
			Teacher teacher_db = gradesDB.getTeacher(9999998);
			Teacher manager_db = gradesDB.getTeacher(9999997);
			
			//for the student, teacher and manager test whether the id and password were correctly stored
			assertEquals(student_db.getPersonID(), student.getPersonID());
			assertEquals(student_db.getPassword(), student.getPassword());
			
			assertEquals(teacher_db.getPersonID(), teacher.getPersonID());
			assertEquals(teacher_db.getPassword(), teacher.getPassword());
			
			assertEquals(manager_db.getPersonID(), manager.getPersonID());
			assertEquals(manager_db.getPassword(), manager.getPassword());
			
			//assert that it does not match nonsense.
			assertFalse(student_db.getPersonID().equals("s1581538"));
			assertFalse(student_db.getPassword().equals("not even a hashed password"));
			
			//assert that roles are stored
			assertTrue(loginDB.isStudent(9999999));
			assertTrue(loginDB.isTeacher(9999998));
			assertTrue(loginDB.isManager(9999997));

			
			//assert that other roles are stored as false
			assertFalse(loginDB.isTeacher(9999999)); //students are not teachers
			assertFalse(loginDB.isManager(9999999)); //students are not managers
			assertFalse(loginDB.isManager(9999998)); //teachers are not managers
			
			//remove the test values from the database.
			gradesDB.deletePerson(student_db.getPersonID());
			gradesDB.deletePerson(teacher_db.getPersonID());
			gradesDB.deletePerson(manager_db.getPersonID());
			
			
		} finally {
			//Close the connections to the database.
			loginDB.closeConnection();
			gradesDB.closeConnection();
		}
		
		
	
	}
	
}
