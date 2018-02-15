package test.java.org.totp.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import main.java.org.totp.service.TOTP;
import main.java.org.totp.service.TOTPSHA256;

public class TOTPSHA256Test {
	TOTP sha256One;
	TOTP sha256Two;

	@Before
	public void init() {
		sha256One = new TOTPSHA256();
		sha256Two = new TOTPSHA256();
	}

	@Test
	public void givenTOTPMD5Instance_whenGivenParametersMatchAtSameTime_thenGeneratedPinsMatch() {
		
			String pinOne = sha256One.generateTOTP(15, 4, "Alice", new char[] { 'a' });
			String pinTwo = sha256Two.generateTOTP(15, 4, "Alice", new char[] { 'a' });
			assertThat(pinOne).matches(pinTwo);
	}

	@Test
	public void givenTOTPMD5Instance_whenGivenUsersAreDifferent_thenGeneratedPinsDontMatch() {
		
		String pinOne = sha256One.generateTOTP(15, 4, "Ahmad", new char[] { 'a' });
		String pinTwo = sha256Two.generateTOTP(15, 4, "Alice", new char[] { 'a' });
		assertThat(pinOne).doesNotMatch(pinTwo);
	}

	@Test
	public void givenTOTPMD5Instance_whenTimeIsOut_thenGeneratedPinsDontMatchEvenForSameUsers()
			throws InterruptedException {
			String pinOne = sha256One.generateTOTP(3, 4, "Alice", new char[] { 'a' });
			Thread.sleep(3010);
			String pinTwo = sha256Two.generateTOTP(3, 4, "Alice", new char[] { 'a' });
			assertThat(pinOne).doesNotMatch(pinTwo);
	}
}
