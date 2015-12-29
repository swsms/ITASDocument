package server.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

public class PasswordEncoder {
	private static final String SALT = "NaCl";

	public static String encode(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(SALT.getBytes());
			byte[] bytes = md.digest(password.getBytes());
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
						.substring(1));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			Logger.getLogger(PasswordEncoder.class.getName()).severe(
					e.getMessage());
		}
		return password;
	}
}
