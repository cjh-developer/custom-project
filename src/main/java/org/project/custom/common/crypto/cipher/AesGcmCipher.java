package org.project.custom.common.crypto.cipher;

import org.project.custom.common.constants.CommonErrorCode;
import org.project.custom.common.exception.CustomException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * DB 정보 암호화
 */
public class AesGcmCipher {

    private static final String TRANS_FORMATION = "AES/GCM/NoPadding";
    private static final int IV_LENGTH = 12;
    private static final int TAG_BITS  = 128;
    private static final SecureRandom RANDOM = new SecureRandom();

    private final SecretKey key;

    /**
     * AES 암호화 비밀키 생성 및 검증
     * @param rawKey
     */
    private AesGcmCipher(byte[] rawKey){
        if(rawKey == null || (rawKey.length != 16 && rawKey.length != 24 && rawKey.length != 32)){
            throw new IllegalArgumentException("Invalid key length");
        }
        this.key = new SecretKeySpec(rawKey, "AES");
    }

    /**
     * Base64 형태로 AesGcmCipher 객체 생성
     * @param base64Key
     * @return
     */
    public static AesGcmCipher getByBase64(String base64Key){
        return new AesGcmCipher(Base64.getDecoder().decode(base64Key));
    }

    /**
     * 암호화
     * @param plainText
     * @return
     */
    public String encrypt(String plainText){
        try{
            byte[] iv = new byte[IV_LENGTH];
            RANDOM.nextBytes(iv);

            Cipher cipher = Cipher.getInstance(TRANS_FORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, key, new GCMParameterSpec(TAG_BITS, iv));
            byte[] cipherText = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
            byte[] out = new byte[iv.length + cipherText.length];
            System.arraycopy(iv, 0, out, 0, IV_LENGTH);
            System.arraycopy(cipherText, 0, out, iv.length, cipherText.length);

            return Base64.getEncoder().encodeToString(out);
        }catch(GeneralSecurityException e){
            throw new CustomException(CommonErrorCode.SERVER_ERROR, "암호화에 실패하였습니다.");
        }
    }

    /**
     * 복호화
     * @param encText
     * @return
     */
    public String decrypt(String encText){
        try{
            byte[] all = Base64.getDecoder().decode(encText);
            if(all.length <= IV_LENGTH){
                throw new IllegalArgumentException("암호문 형식이 틀렸습니다.");
            }

            Cipher cipher = Cipher.getInstance(TRANS_FORMATION);
            cipher.init(Cipher.DECRYPT_MODE, key, new GCMParameterSpec(TAG_BITS, all, 0, IV_LENGTH));
            byte[] plain = cipher.doFinal(all, IV_LENGTH, all.length - IV_LENGTH);

            return new String(plain, StandardCharsets.UTF_8);
        }catch (GeneralSecurityException e){
            throw new CustomException(CommonErrorCode.SERVER_ERROR, "복호화에 실패하였습니다.");
        }
    }
}
