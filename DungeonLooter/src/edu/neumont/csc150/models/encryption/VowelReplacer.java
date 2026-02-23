package edu.neumont.csc150.models.encryption;

public class VowelReplacer implements StringEncryptor {
	char[] vowels = {'a', 'e', 'i', 'o', 'u'};

	@Override
	public String encrypt(String text) {
		StringBuilder newText = new StringBuilder();
		char[] charArray = text.toCharArray();

		for (int i = 0; i < charArray.length; i++) {
			char currentChar = charArray[i];
			boolean isUpper = Character.isUpperCase(currentChar);

			for (int x = 0; x < vowels.length; x++) {
				if (Character.toLowerCase(currentChar) == vowels[x]) {
					charArray[i] = vowels[(x + 1 == vowels.length ? 0 : x + 1)];
					break;
				}
			}
			newText.append((isUpper ? Character.toUpperCase(charArray[i]) : charArray[i]));
		}
		return newText.toString();
	}

	@Override
	public String decrypt(String text) {
		StringBuilder originalText = new StringBuilder();
		char[] charArray = text.toCharArray();

		for (int i = 0; i < charArray.length; i++) {
			char currentChar = charArray[i];
			boolean isUpper = Character.isUpperCase(currentChar);

			for (int x = 0; x < vowels.length; x++) {
				if (Character.toLowerCase(currentChar) == vowels[x]) {
					charArray[i] = vowels[(x - 1 < 0 ? vowels.length - 1 : x - 1)];
					break;
				}
			}
			originalText.append((isUpper ? Character.toUpperCase(charArray[i]) : charArray[i]));
		}
		return originalText.toString();
	}
}
