package org.project.custom.common.util;

import java.security.SecureRandom;
import java.util.Base64;

public final class RandomUtil {

    private static final SecureRandom RANDOM = new SecureRandom();

    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String CHAR_NUMBER = "0123456789";
    private static final String CHAR_SPECIAL = "!@#$%^&*()-_=+";

    private RandomUtil() {
        throw new AssertionError("randomUtil not instance!!");
    }

    public static byte[] getByte(int length) {
        if (length < 1) {
            throw new IllegalArgumentException("length must be >= 1");
        }
        byte[] bytes = new byte[length];
        RANDOM.nextBytes(bytes);
        return bytes;
    }

    public static String getBase64Url(int byteLength) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(getByte(byteLength));
    }

    public static String getAlphaNumber(int length) {
        return getBaseString(length, CHAR_LOWER + CHAR_UPPER + CHAR_NUMBER);
    }

    public static String getAlpha(int length) {
        return getBaseString(length, CHAR_LOWER + CHAR_UPPER);
    }

    public static String getNumber(int length) {
        return getBaseString(length, CHAR_NUMBER);
    }

    public static String getBaseString(int length, String limitChar) {
        if (length < 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(limitChar.charAt(RANDOM.nextInt(limitChar.length())));
        }
        return sb.toString();
    }
}
