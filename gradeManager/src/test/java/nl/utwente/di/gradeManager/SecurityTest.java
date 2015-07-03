package nl.utwente.di.gradeManager;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.security.NoSuchAlgorithmException;

import nl.utwente.di.gradeManager.helpers.Security;
import nl.utwente.di.gradeManager.model.Student;

import org.junit.Before;
import org.junit.Test;

public class SecurityTest {

	private Student account;
	private final String correctPassword = "correct";
	private final String wrongPassword = "wrong";
	
	@Before
	public void setup(){
		String salt;
		try {
			salt = Security.getSalt();
			String hashed_password = Security.getSHA512(correctPassword, salt);
			account = new Student(9999999, "firstname", "lastname", hashed_password, salt );
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	//test whether the encryption happens succcesfully
	public void testEncryption(){
		String salt = account.getSalt();
		try {
			String correctHashedPassword = Security.getSHA512(correctPassword, salt);
			String wrongHashedPassword = Security.getSHA512(wrongPassword, salt);
			System.out.println("salts: " + salt + " : " + account.getSalt());
			System.out.println(correctHashedPassword);
			System.out.println(account.getPassword());
			assertTrue(correctHashedPassword.equals(account.getPassword()));
			assertFalse(wrongHashedPassword.equals(account.getPassword()));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
