package edu.neumont.csc150.models.encryption;

public class Cutter implements StringEncryptor {

	@Override
	public String encrypt(String text) {
		int half = Math.round(text.length() / 2);
		String secondHalf = text.substring(text.length() - half);
		String firstHalf = text.substring(0, text.length() - half);

		return secondHalf + firstHalf;
	}

	@Override
	public String decrypt(String text) {
		int half = Math.round(text.length() / 2);
		String secondHalf = text.substring(text.length() - half);
		String firstHalf = text.substring(0, text.length() - half);

		return secondHalf + firstHalf;
	}
}
