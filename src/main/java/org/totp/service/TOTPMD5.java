package main.java.org.totp.service;


import main.java.org.totp.util.HashType;
import main.java.org.totp.util.Hashs;

public class TOTPMD5 implements TOTP{
	private static final String EXCEPTION_MSG = "Something went wrong with MD5";
	@Override
	public String generateTOTP(long seconds, int numberOfDigits, String username, char[] password) {
		return Hashs.TOTP(EXCEPTION_MSG, HashType.MD5.getValue(), seconds, numberOfDigits, username, password);
	}
	public static void main(String[] args) {
		TOTPMD5 t = new TOTPMD5();
		System.out.println(t.generateTOTP(5, 3, "A", new char[]{'a'}));
	}
}