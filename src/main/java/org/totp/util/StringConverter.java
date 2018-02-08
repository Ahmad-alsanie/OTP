package main.java.org.totp.util;

public class StringConverter {
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
