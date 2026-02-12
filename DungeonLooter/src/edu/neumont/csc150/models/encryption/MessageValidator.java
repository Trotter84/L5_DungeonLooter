package edu.neumont.csc150.models.encryption;

public class MessageValidator {
    private static final String EXPECTED_ENCRYPTED_PASSPHRASE = "w#d#irdwL#iarxjk";

    public static boolean validate(String encryptedResponse) {
        return EXPECTED_ENCRYPTED_PASSPHRASE.equals(encryptedResponse);
    }
}
