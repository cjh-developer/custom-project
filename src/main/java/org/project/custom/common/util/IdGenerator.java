package org.project.custom.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

/**
 * OID 발급기
 * ParamConsts.PREFIX = "ism_"
 * ParamConsts.OID_BODY_LENGTH =
 */
@Component
public class IdGenerator {

    public static final int BODY_LENGTH = 26;
    private static final String TABLE = "0123456789ABCDEFGHJKMNPQRSTVWXYZ";
    private static final SecureRandom RANDOM = new SecureRandom();

    private final String prefix;
    private final int oidLength;

    public IdGenerator(@Value("${custom.generate.oid.format:cus_}") String prefix) {
        this.prefix = (prefix == null) ? "" : prefix;
        this.oidLength = this.prefix.length() + BODY_LENGTH;
    }

    /**
     * oid 발급
     * 엔티티 팩토리 메서드 내부에서만 동작
     * @return
     */
    public String generate() {
        byte[] buffer = new byte[BODY_LENGTH];
        RANDOM.nextBytes(buffer);
        StringBuilder sb = new StringBuilder(oidLength).append(prefix);
        for (byte b : buffer) {
            sb.append(TABLE.charAt(b & 0x1F));
        }
        return sb.toString();
    }

    /**
     * 외부 OID 검증(PathVariable)
     * @param oid
     * @return
     */
    public boolean isValid(String oid) {
        if (oid == null || oid.length() != oidLength || !oid.startsWith(prefix)) return false;
        for (int i = prefix.length(); i < oid.length(); i++) {
            if (TABLE.indexOf(oid.charAt(i)) < 0) return false;
        }
        return true;
    }
}
