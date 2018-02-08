package main.java.org.totp.service;
public interface TOTP {
	String generateTOTP(long seconds, int numberOfDigits, String username,
			char[] password);
}
