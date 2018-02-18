package main.java.org.totp.util;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
/**
 * @author Ahmad-Alsanie 10-2-2018 
 * Github: https://github.com/Ahmad-alsanie
 *         ----------------------------------------------------------- 
 *         The {@code Hashs} class provides a generic method TOTP 
 */
public class Hashs {
	
	private Hashs(){
		//intentionally left blank **Don't Modify**
	}
	/**Allocates a new {@code String} that contains the generated pin (OTP)
	 * based on time, username,password and type of encryption
	 * @param  expMSG
     *         Message asscoiated with a certain type of encryption 
     *         to be logged when something wrong happens
     *
     * @param  type 
     *         The used encryption algorithm 
     *
     * @param  seconds
     *         Time slot of the changing pin (OTP)
     *        
     * @param  numberOfDigits
     * 		   The length of the generated pin (OTP)
     * @param username 
     * 			String with the value of the username that the pin is for
     * @param password
     * 			A char array holds the password of the user
	 * **/
	public static String TOTP(String expMSG, String type, long seconds, int numberOfDigits, String username, char[] password) {
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

		byte[] timeCode = StringUtil.strToByteArray(moves);
		byte[] userKey = StringUtil.strToByteArray(username + new String(password));
		switch (type) {
		case "MD5":
			hash = md5(expMSG,type, timeCode, userKey);
			break;
		default:
			hash = sha(expMSG,type, timeCode, userKey);
			break;
		}

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
	/**Helper method to generate MD5
	 * @param  expMSG
     *         Message asscoiated with a certain type of encryption 
     *         to be logged when something wrong happens
     *
     * @param  type 
     *         The used encryption algorithm 
     *
     * @param  timecode
     *         byte array represents a time slot
     *        
     * @param  userKey
     * 		   byte array represents both user and password 
	 * **/
	private static byte[] md5(String expMSG, String type, byte[] timeCode, byte[] userKey) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance(HashType.MD5.getValue());
			 md.update(userKey);
			 return md.digest(timeCode);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException(expMSG);
		}
	}
	/**Helper method to generate SHA related byte[]
	 * @param  expMSG
     *         Message asscoiated with a certain type of encryption 
     *         to be logged when something wrong happens
     *
     * @param  type 
     *         The used encryption algorithm 
     *
     * @param  timecode
     *         byte array represents a time slot
     *        
     * @param  userKey
     * 		   byte array represents both user and password 
	 * **/
	private static byte[] sha(String expMSG, String code, byte[] time, byte[] userKey) {
		try {
			Mac hashMsgCode;
			hashMsgCode = Mac.getInstance(code);
			SecretKeySpec secret = new SecretKeySpec(userKey, "RAW");
			hashMsgCode.init(secret);
			return hashMsgCode.doFinal(time);
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
			throw new RuntimeException(expMSG);
		}
	}
}
