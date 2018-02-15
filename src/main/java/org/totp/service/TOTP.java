package main.java.org.totp.service;
/**
 * @author Ahmad-Alsanie 10-2-2018 
 * Github: https://github.com/Ahmad-alsanie
 *         ----------------------------------------------------------- 
 *         The {@code TOTP} represents An
 *         interface that works as a facade and contains 1 method used 
 *         to generate OTP based on time, user name and password 
 *         with a specific number of digits
 */
public interface TOTP {
	/**Abstract method used by the implementor to 
	 * allocates a new {@code String} that contains the generated pin (OTP)
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
	String generateTOTP(long seconds, int numberOfDigits, String username,
			char[] password);
}
