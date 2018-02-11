package main.java.org.totp.service;

import main.java.org.totp.util.HashType;
import main.java.org.totp.util.Hashs;

public class TOTPSHA256 implements TOTP {
	private static final String EXCEPTION_MSG ="Something went wrong with SHA256";
	@Override
	public String generateTOTP(long seconds, int numberOfDigits, String username,
			char[] password) {
		return Hashs.TOTP(EXCEPTION_MSG, HashType.SHA256.getValue(), seconds, numberOfDigits, username, password);
	}
}
