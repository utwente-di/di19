package nl.utwente.di.gradeManager;

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
		m = new Module(201300170, 2014, "Parels der Informatica");
		c = new Course(1002314, 2014, "Parel 1: Cryptografie", 15);
		a = new Assignment(1111, c.getCourseCode(), 2014, "Parel 1 Toets", false, 40, BigDecimal.valueOf(4.5));
		ao = new AssignmentOccasion(123, a.getAssignmentID(), Date.valueOf("2014-04-06"));
		ar = new AssignmentResult(ao.getOccasionid(), Integer.parseInt(s.getPersonID().substring(1)), ao.getOccasiondate(), BigDecimal.valueOf(8.5), ao.getAssignmentid());
		mr = new ModuleResult(Integer.parseInt(s.getPersonID().substring(1)), m.getModulecode(), m.getYear(), BigDecimal.valueOf(7,2));
		tm = new TeacherModule(t,m, "Docent");
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
