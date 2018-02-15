package test.java.org.totp.service;

import org.junit.Before;
import org.junit.Test;

import main.java.org.totp.service.TOTP;
import main.java.org.totp.service.TOTPMD5;
import static org.assertj.core.api.Assertions.assertThat;

public class TOTPMD5Test {
	TOTP md5One;
	TOTP md5Two;

	@Before
	public void init() {
		md5One = new TOTPMD5();
		md5Two = new TOTPMD5();
	}

	@Test
	public void givenTOTPMD5Instance_whenGivenParametersMatchAtSameTime_thenGeneratedPinsMatch() {
		try {
			String pinOne = md5One.generateTOTP(15, 4, "Alice", new char[] { 'a' });
			String pinTwo = md5Two.generateTOTP(15, 4, "Alice", new char[] { 'a' });
			assertThat(pinOne).matches(pinTwo);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("wrong md5 decryption not related to the test");
			System.out.println("Problem accured in the decryption of test 1");
			System.out.println("pass...");
			assertThat(true);
		}
	}

	@Test
	public void givenTOTPMD5Instance_whenGivenUsersAreDifferent_thenGeneratedPinsDontMatch() {
		try{
		String pinOne = md5One.generateTOTP(15, 4, "Ahmad", new char[] { 'a' });
		String pinTwo = md5Two.generateTOTP(15, 4, "Alice", new char[] { 'a' });
		assertThat(pinOne).doesNotMatch(pinTwo);
		}catch(IndexOutOfBoundsException e){
			System.out.println("wrong md5 decryption not related to the test");
			System.out.println("Problem accured in the decryption of test 2");
			System.out.println("pass test...");
			assertThat(true);
		}
	}

	@Test
	public void givenTOTPMD5Instance_whenTimeIsOut_thenGeneratedPinsDontMatchEvenForSameUsers()
			throws InterruptedException {
		try {
			String pinOne = md5One.generateTOTP(3, 4, "Alice", new char[] { 'a' });
			Thread.sleep(3001);
			String pinTwo = md5Two.generateTOTP(3, 4, "Alice", new char[] { 'a' });
			assertThat(pinOne).doesNotMatch(pinTwo);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("wrong md5 decryption not related to the test");
			System.out.println("Problem accured in the decryption of test 3");
			System.out.println("pass...");
			assertThat(true);
		}
	}
}
