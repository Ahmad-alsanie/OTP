package test.java.org.totp.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import main.java.org.totp.service.TOTP;
import main.java.org.totp.service.TOTPSHA512;

public class TOTPSHA512Test {
	TOTP sha512One;
	TOTP sha512Two;

	@Before
	public void init() {
		sha512One = new TOTPSHA512();
		sha512Two = new TOTPSHA512();
	}

	@Test
	public void givenTOTPMD5Instance_whenGivenParametersMatchAtSameTime_thenGeneratedPinsMatch() {
		
			String pinOne = sha512One.generateTOTP(15, 4, "Alice", new char[] { 'a' });
			String pinTwo = sha512Two.generateTOTP(15, 4, "Alice", new char[] { 'a' });
			assertThat(pinOne).matches(pinTwo);
	}

	@Test
	public void givenTOTPMD5Instance_whenGivenUsersAreDifferent_thenGeneratedPinsDontMatch() {
		
		String pinOne = sha512One.generateTOTP(15, 4, "Ahmad", new char[] { 'a' });
		String pinTwo = sha512Two.generateTOTP(15, 4, "Alice", new char[] { 'a' });
		assertThat(pinOne).doesNotMatch(pinTwo);
	}

	@Test
	public void givenTOTPMD5Instance_whenTimeIsOut_thenGeneratedPinsDontMatchEvenForSameUsers()
			throws InterruptedException {
			String pinOne = sha512One.generateTOTP(3, 4, "Alice", new char[] { 'a' });
			Thread.sleep(3010);
			String pinTwo = sha512Two.generateTOTP(3, 4, "Alice", new char[] { 'a' });
			assertThat(pinOne).doesNotMatch(pinTwo);
	}
}
