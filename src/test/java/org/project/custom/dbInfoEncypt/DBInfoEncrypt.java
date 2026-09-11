package org.project.custom.dbInfoEncypt;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.project.custom.common.crypto.cipher.AesGcmCipher;
import org.project.custom.common.util.StringCheck;

import java.security.SecureRandom;
import java.util.Base64;

public class DBInfoEncrypt {

    @Test
    @DisplayName("데이터베이스 정보 암호화")
    void encryptDBInfo(){
        String username = "ism";
        String password = "ism01";

        byte[] raw = new byte[32];
        new SecureRandom().nextBytes(raw);
        String base64Key = Base64.getEncoder().encodeToString(raw);

        System.out.println("COMMON_KEY : " + base64Key);

        if( !StringCheck.isEmpty(username)){
            System.out.println("USER_NAME_ENC = " + AesGcmCipher.getByBase64(base64Key).encrypt(username));
        }
        if( !StringCheck.isEmpty(password)){
            System.out.println("PASSWORD_ENC = " + AesGcmCipher.getByBase64(base64Key).encrypt(password));
        }
    }
}
