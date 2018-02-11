package main.java.org.totp.util;

public enum HashType {
	SHA1("HmacSHA1"),SHA256("HmacSHA256"),SHA512("HmacSHA512"),MD5("MD5");
	private String value;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	private HashType(String value) {
		this.value = value;
	}
}
