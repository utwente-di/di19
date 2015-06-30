package nl.utwente.di.gradeManager;

import nl.utwente.di.gradeManager.db.GradesDB;
import nl.utwente.di.gradeManager.helpers.Security;
import nl.utwente.di.gradeManager.model.Student;
import nl.utwente.di.gradeManager.model.Teacher;

import org.junit.*;

public class JunitTest {
	private Teacher t;
	private Student s;
	
	
	@Before
	public void setup() throws Exception{
		String salt = Security.getSalt();
		String pass = Security.getSHA512("wachtwoord", salt);
		t = new Teacher(14325, "Henk", "Henkmans", pass, salt, false);
		String salt2 = Security.getSalt();
		String pass2 = Security.getSHA512("w8w00rd", salt2);
		s = new Student(1234534, "Harry", "Henkmans", pass2, salt2);
	}


	
	@Test
	public void AddTest(){
		GradesDB gradesDB = new GradesDB();
		gradesDB.addTeacher(t);
		gradesDB.addStudent(s);
		gradesDB.DeleteStudent(s.getPersonID());
		gradesDB.DeleteTeacher(t.getPersonID());
		gradesDB.DeletePerson(s.getPersonID());
		gradesDB.DeletePerson(t.getPersonID());
		}
		
}
