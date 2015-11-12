package utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class HashingPassword {
	public static String hash(String password) throws Exception{
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		md.reset();
		md.update(password.getBytes("UTF-8"));
		String hashPass = new BigInteger(1, md.digest()).toString(16);
		return hashPass;
	}
}
