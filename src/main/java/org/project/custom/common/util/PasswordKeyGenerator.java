package org.project.custom.common.util;

import org.project.custom.common.crypto.cipher.AesGcmCipher;

import java.util.Base64;

public class PasswordKeyGenerator {

    public static final int AES_128 = 16;
    public static final int AES_192 = 24;
    public static final int AES_256 = 32;

    public PasswordKeyGenerator() {
        throw new AssertionError("passwordKeyGenerator not instance");
    }

    public static String generate(){
        return generate(AES_256);
    }

    public static String generate(int byteLength){
        if(byteLength != AES_128 && byteLength != AES_192 && byteLength != AES_256){
            throw new IllegalArgumentException("password key length must be 16, 24, or 32 bytes");
        }
        return Base64.getEncoder().encodeToString(RandomUtil.getByte(byteLength));
    }

    public static boolean verify(String base64Key){
        try{
            AesGcmCipher cipher = AesGcmCipher.getByBase64(base64Key);
            String sample = "password-key-self-test";
            return sample.equals(cipher.decrypt(cipher.encrypt(sample)));
        }catch(RuntimeException e){
            return false;
        }
    }
}
