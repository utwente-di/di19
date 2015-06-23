package nl.utwente.di.gradeManager.helpers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Security {

	public static String secret = "TestiGradeManagerSuperSecret";
	/**
	 * Returns an md5 representation of the input + our secret to make hash less predicatble
	 * @param in The string to be hashed
	 * @return A hash of the string + our secret.
	 */
	public static String MD5(String in){
		in += secret;
		String digestedMessage = "";
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(in.getBytes());
			byte[] digestedBytes = md.digest();
			StringBuffer sb = new StringBuffer();
			for (byte b : digestedBytes) {
				sb.append(String.format("%02x", b & 0xff));
			}
			digestedMessage = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// Tuurlijk bestaat MD5 wel...
			e.printStackTrace();
		}
		
		return digestedMessage;
	}
	
}
