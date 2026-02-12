package edu.neumont.csc150.models.encryption;

public class CaesarShifter implements StringEncryptor {
    private int shift;

    public CaesarShifter(int shift) {
        setShift(shift);
    }

    public int getShift() {
        return shift;
    }

    public void setShift(int shift) {
        this.shift = shift;
    }

    @Override
    public String encrypt(String text) {
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (chars[i] + shift);
        }
        return new String(chars);
    }

    @Override
    public String decrypt(String text) {
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (chars[i] - shift);
        }
        return new String(chars);
    }
}
