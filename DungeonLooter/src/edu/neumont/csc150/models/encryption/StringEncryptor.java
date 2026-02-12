package edu.neumont.csc150.models.encryption;

public interface StringEncryptor {
    String encrypt(String text);
    String decrypt(String text);
}
