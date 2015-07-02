package nl.utwente.di.gradeManager.helpers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Security {

	
	/**
	 * Generates a SHA-512 hash, based on a given input/salt.
	 * @param input The string to be hashed.
	 * @param salt The salt to be added, before it is hashed.
	 * @return The SHA-512 hash of the input + the hash.
	 * @throws NoSuchAlgorithmException
	 */
	public static String getSHA512(String input, String salt) throws NoSuchAlgorithmException{
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(salt.getBytes());
			byte[] bytes = md.digest(input.getBytes());
			StringBuilder sb = new StringBuilder();
			for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
			
	}
	
	/**
	 * Generates a random salt, of 16 bytes.
	 * @return A random salt, in the format of a string.
	 * @throws NoSuchAlgorithmException
	 */
	public static String getSalt() throws NoSuchAlgorithmException{
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt.toString();
	}
	
}
