package main.java.org.totp.util;


/**
 * @author Ahmad-Alsanie 10-2-2018 
 * Github: https://github.com/Ahmad-alsanie
 *         ----------------------------------------------------------- 
 *         The {@code StringConverter} class provides a util 
 *         method to convert String to byte[] 
 */
public class StringConverter {
	/**Converts received String to a byte[]
	 * by calling {@link util.Hashs} TOTP util method
	 * @param  hex
     *         String with a sequence of character 
     * @return
     * 		   byte[] of the received String
	 * **/
	public static byte[] strToByteArray(String hex) {
		byte[] array = new String(hex).getBytes();
		byte[] result = new byte[array.length - 1];
		int counter = 0;
		while (counter < result.length){
			result[counter] = array[counter + 1];
			counter++;
			}
		return result;
	}
}
