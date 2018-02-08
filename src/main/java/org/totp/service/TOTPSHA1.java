package main.java.org.totp.service;

import java.security.GeneralSecurityException;
import java.time.Instant;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import main.java.org.totp.util.HashType;
import main.java.org.totp.util.StringConverter;

public class TOTPSHA1 implements TOTP {

	@Override
	public String generateTOTP(long seconds, int numberOfDigits, String username, char[] password) {
		Instant instant = Instant.now();
		long timeStamp = instant.toEpochMilli() / 1000;// per second
		long initValue = 0;
		String moves = "0";
		long timeSteps = (timeStamp - initValue) / seconds;
		moves = Long.toHexString(timeSteps).toUpperCase();

		String result = null;
		byte[] hash;
		// moving factor 16 bytes
		while (moves.length() < 16)
			moves = "0" + moves;

		byte[] timeCode = StringConverter.strToByteArray(moves);
		byte[] userKey = StringConverter.strToByteArray(username + new String(password));
		hash = sha1(HashType.SHA1.getValue(), timeCode, userKey);

		int offset = hash[hash.length - 1] & 0xf;

		int binary = ((hash[offset] & 0x7f) << 24) | ((hash[offset + 1] & 0xff) << 16) | ((hash[offset + 2] & 0xff) << 8)
				| (hash[offset + 3] & 0xff);

		int otp = (int) (binary % (Math.pow(10,numberOfDigits)));

		result = Integer.toString(otp);
		while (result.length() < numberOfDigits) {
			result = "0" + result;
		}
		return result;
	}

	private static byte[] sha1(String code, byte[] time, byte[] userKey) {
		try {
			Mac hashMsgCode;
			hashMsgCode = Mac.getInstance(code);
			SecretKeySpec secret = new SecretKeySpec(userKey, "RAW");
			hashMsgCode.init(secret);
			return hashMsgCode.doFinal(time);
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
			throw new RuntimeException("Something went wrong while using sh1()");
		}
	}
	
}

