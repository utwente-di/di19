package nl.utwente.di.gradeManager;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.sql.Date;

import nl.utwente.di.gradeManager.db.GradesDB;
import nl.utwente.di.gradeManager.helpers.Security;
import nl.utwente.di.gradeManager.model.*;

import org.junit.*;

public class JunitTest {
	private Teacher t;
	private Student s;
	private Module m;
	private Course c;
	private Assignment a;
	private AssignmentOccasion ao;

	private SuperCourse sc;
	private SuperModule sm;

	private AssignmentResult ar;
	private ModuleResult mr;
	private TeacherModule tm;
	

	
	@Before
	public void setup() throws Exception{
		String salt = Security.getSalt();
		String pass = Security.getSHA512("wachtwoord", salt);
		t = new Teacher(14325, "Henk", "Henkmans", pass, salt, false);
		String salt2 = Security.getSalt();
		String pass2 = Security.getSHA512("w8w00rd", salt2);
		s = new Student(1234534, "Harry", "Henkmans", pass2, salt2);
		sc = new SuperCourse(1002314, "Math A1 + B", 15);
		sm = new SuperModule(948859, "Junit tests der Informatica");
		c = new Course(sc.getCourseCode(), 2015, sc.getName(), sc.getWeight());
		m = new Module(sm.getModulecode(), 2014, sm.getName());
		a = new Assignment(1111, c.getCourseCode(), 2014, "Parel 1 Toets", false, 40, BigDecimal.valueOf(4.5));
		ao = new AssignmentOccasion(123, a.getAssignmentID(), Date.valueOf("2014-04-06"));

		ar = new AssignmentResult(ao.getOccasionid(), Integer.parseInt(s.getPersonID().substring(1)), ao.getOccasiondate(), BigDecimal.valueOf(8.5), ao.getAssignmentid());
		mr = new ModuleResult(Integer.parseInt(s.getPersonID().substring(1)), m.getModulecode(), m.getYear(), BigDecimal.valueOf(7,2));
		tm = new TeacherModule(t,m, "Docent");

	}


	@Test
	public void addAndRemoveStudent(){
		GradesDB gradesDB = new GradesDB();
		try{
			//Test adding the student to the database.
			gradesDB.addStudent(s);
			Student s_db = gradesDB.getStudent(Integer.parseInt(s.getPersonID().substring(1)));
			assertEquals(s_db.getFirstname(), s.getFirstname());
			assertEquals(s_db.getPassword(), s.getPassword());
			assertEquals(s_db.getPersonID(), s.getPersonID());
			assertEquals(s_db.getSalt(), s.getSalt());
			assertEquals(s_db.getSurname(), s.getSurname());
			//Test removing a student from the database.
			gradesDB.deletePerson(s.getPersonID());
			assertEquals(gradesDB.getStudent(Integer.parseInt(s.getPersonID().substring(1))), null);
		} finally {
			//Close the connection.
			gradesDB.closeConnection();
		}
	}
	
	@Test
	public void addAndRemoveTeacher(){
		GradesDB gradesDB = new GradesDB();
		try{
			//Test adding the teacher to the database.
			gradesDB.addTeacher(t);
			Teacher t_db = gradesDB.getTeacher(Integer.parseInt(t.getPersonID().substring(1)));
			assertEquals(t_db.getFirstname(), t.getFirstname());
			assertEquals(t_db.getPassword(), t.getPassword());
			assertEquals(t_db.getPersonID(), t.getPersonID());
			assertEquals(t_db.getSalt(), t.getSalt());
			assertEquals(t_db.getSurname(), t.getSurname());
			assertEquals(t_db.isManager(), t.isManager());
			//Test removing the teacher from the database.
			gradesDB.deletePerson(t.getPersonID());
			assertEquals(gradesDB.getTeacher(Integer.parseInt(t.getPersonID().substring(1))), null);
		} finally {
			//Close the connection.
			gradesDB.closeConnection();
		}
	}
	
	@Test
	public void addAndRemoveSuperCourse(){
		GradesDB gradesDB = new GradesDB();
		try{
			//Test for adding a supercourse to the database.
			gradesDB.addSuperCourse(sc);
			SuperCourse sc_db = gradesDB.getSuperCourse(sc.getCourseCode());
			assertEquals(sc_db.getCourseCode(),sc.getCourseCode());
			assertEquals(sc_db.getName(),sc.getName());
			assertEquals(sc_db.getWeight(),sc.getWeight());
			//Test for removing a supercourse from the database.
			gradesDB.deleteSuperCourse(sc.getCourseCode());
			assertEquals(gradesDB.getSuperCourse(c.getCourseCode()), null);
		} finally {
			//Close the connection.
			gradesDB.closeConnection();
		}
	}
	
	@Test
	public void addAndRemoveCourse(){
		GradesDB gradesDB = new GradesDB();
		try{
			//Test adding the course to the database.
			gradesDB.addSuperCourse(sc);
			gradesDB.addCourse(c);
			Course c_db = gradesDB.getCourse(c.getCourseCode(), c.getYear());
			assertEquals(c_db.getCourseCode(), c.getCourseCode());
			assertEquals(c_db.getName(), c.getName());
			assertEquals(c_db.getWeight(), c.getWeight());
			assertEquals(c_db.getYear(), c.getYear());
			//Test removing the course from the database.
			gradesDB.deleteCourse(c.getCourseCode(), c.getYear());
			assertEquals(gradesDB.getCourse(c.getCourseCode(), c.getYear()), null);
		} finally {
			gradesDB.closeConnection();
		}
	}
	
	@Test
	public void addAndRemoveSuperModule(){
		GradesDB gradesDB = new GradesDB();
		try {
			//Test adding the supercourse to the database.
			gradesDB.addSuperModule(sm);
			SuperModule sm_db = gradesDB.getSuperModule(sm.getModulecode());
			assertEquals(sm_db.getModulecode(), sm.getModulecode());
			assertEquals(sm_db.getName(), sm.getName());
			//Test removing the supercourse from the database.
			gradesDB.deleteSuperModule(sm.getModulecode());
			assertEquals(gradesDB.getSuperModule(sm.getModulecode()), null);
		} finally {
			gradesDB.closeConnection();
		}
	}
	
	@Test
	public void addAndRemoveModule(){
		GradesDB gradesDB = new GradesDB();
		try{
				//Test adding the module to the database.
				gradesDB.addSuperModule(sm);
				gradesDB.addModule(m);
				Module m_db = gradesDB.getModule(m.getModulecode(), m.getYear());
				assertEquals(m_db.getModulecode(),m.getModulecode());
				assertEquals(m_db.getName(), m.getName());
				assertEquals(m_db.getYear(), m.getYear());
				//Test removing the module from the database.
				gradesDB.deleteModule(m.getModulecode(), m.getYear());
				assertEquals(gradesDB.getModule(m.getModulecode(), m.getYear()), null);
		} finally {
				//close the connection
				gradesDB.closeConnection();
		}
		
	}
	
	@Test
	public void AddTest(){
		GradesDB gradesDB = new GradesDB();
		try {gradesDB.addTeacher(t);
		gradesDB.addStudent(s);
		gradesDB.addSuperModule(new SuperModule(m.getModulecode(), m.getName()));
		gradesDB.addModule(m);
		gradesDB.addSuperCourse(new SuperCourse(c.getCourseCode(), "Parel 1: Cryptografie", 15));
		gradesDB.addCourse(c);
		gradesDB.addAssignment(a);
		gradesDB.addAssignmentOccasion(ao);
		
		
		gradesDB.deletePerson(s.getPersonID());
		gradesDB.deletePerson(t.getPersonID());
		gradesDB.deleteAssignment(a.getAssignmentID());
		gradesDB.deleteSuperCourse(c.getCourseCode());
		gradesDB.deleteSuperModule(m.getModulecode());
		} finally {
			gradesDB.closeConnection();
		}
	}
		
}
