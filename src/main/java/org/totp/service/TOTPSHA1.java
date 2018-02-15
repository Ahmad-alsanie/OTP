package main.java.org.totp.service;


import main.java.org.totp.util.HashType;
import main.java.org.totp.util.Hashs;
/**
 * @author Ahmad-Alsanie 10-2-2018 
 * Github: https://github.com/Ahmad-alsanie
 *         ----------------------------------------------------------- 
 *         The {@code TOTPSHA1} class represents SHA1 implementation 
 *         of {@link TOTP}
 */
public class TOTPSHA1 implements TOTP {
	private static final String EXCEPTION_MSG ="Something went wrong with SHA1";
	/**Allocates a new {@code String} that contains the generated pin (OTP)
	 * based on SHA1
	 * by calling {@link util.Hashs} TOTP util method
	 * @param  seconds
     *         long that defines the time slot in which the generated pin is changed
     *
     * @param  numberOfDigits 
     *         an int represents the number of digits generated - pin (OTP) length
     *
     * @param  username
     *         String with the value of the username that the pin is for
     *        
     * @param  password
     * 		   A char array holds the password of the user
	 * **/
	@Override
	public String generateTOTP(long seconds, int numberOfDigits, String username, char[] password) {
		
		return Hashs.TOTP(EXCEPTION_MSG, HashType.SHA1.getValue(), seconds, numberOfDigits, username, password);
	}
}

