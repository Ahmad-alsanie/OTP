package main.java.org.totp.service;


import main.java.org.totp.util.HashType;
import main.java.org.totp.util.SHAs;

public class TOTPSHA1 implements TOTP {
	private static final String EXCEPTION_MSG ="Something went wrong with SHA1";
	@Override
	public String generateTOTP(long seconds, int numberOfDigits, String username, char[] password) {
		
		return SHAs.TOTP(EXCEPTION_MSG, HashType.SHA1.getValue(), seconds, numberOfDigits, username, password);
	}
}

