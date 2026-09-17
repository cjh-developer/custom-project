package org.project.custom.domain.config.policy;

import org.project.custom.domain.config.constatns.ConfigKeys;
import org.project.custom.domain.config.service.CmCacheService;

/**
 * 비밀번호 정책
 */
public final class PasswordPolicy {


    private final CmCacheService config;

    public PasswordPolicy(CmCacheService config) {
        this.config = config;
    }

    public static PasswordPolicy of(CmCacheService config) {
        return new PasswordPolicy(config);
    }

    public static final int SALT_LENGTH_MIN = 16;
    public static final int SALT_LENGTH_MAX = 32;

    /**
     * 비밀번호 최소 길이
     * @return
     */
    public int getMinLength(){
        return config.getInt(ConfigKeys.PASSWORD_MIN_LENGTH, 6);
    }

    /**
     * 비밀번호 최대 길이
     * @return
     */
    public int getMaxLength(){
        return config.getInt(ConfigKeys.PASSWORD_MAX_LENGTH, 50);
    }

    /**
     * 영문 허용 여부
     * @return
     */
    public boolean getCheckAlpha(){
        return config.getBoolean(ConfigKeys.PASSWORD_CHECK_ALPHA, true);
    }

    /**
     * 영문 최소 개수
     * @return
     */
    public int getCheckAlphaCnt(){
        return config.getInt(ConfigKeys.PASSWORD_INPUT_ALPHA, 1);
    }

    /**
     * 숫자 허용 여부
     * @return
     */
    public boolean getCheckNumber(){
        return config.getBoolean(ConfigKeys.PASSWORD_CHECK_NUMBER, true);
    }

    /**
     * 최소 숫자 개수
     * @return
     */
    public int getCheckNumberCnt(){
        return config.getInt(ConfigKeys.PASSWORD_INPUT_NUMBER, 1);
    }

    /**
     * 공백 문자 허용 여부
     * @return
     */
    public boolean getCheckSpace(){
        return config.getBoolean(ConfigKeys.PASSWORD_ALLOW_SPACE, false);
    }

    /**
     * 특수 문자 허용 여부
     * @return
     */
    public boolean getCheckSpecial(){
        return config.getBoolean(ConfigKeys.PASSWORD_ALLOW_SPECIAL, true);
    }

    /**
     * 연속 문자열 허용 여부
     * @return
     */
    public boolean getCheckConsecutive(){
        return config.getBoolean(ConfigKeys.PASSWORD_ALLOW_CONSECUTIVE, false);
    }

    /**
     * 연속 문자열 허용 최소 개수
     * @return
     */
    public int getCheckConsecutiveCnt(){
        return config.getInt(ConfigKeys.PASSWORD_INPUT_CONSECUTIVE, 3);
    }

    /**
     * 반복 문자열 허용 여부
     * @return
     */
    public boolean getCheckRepeated(){
        return config.getBoolean(ConfigKeys.PASSWORD_ALLOW_REPEATED, false);
    }

    /**
     * 반복 문자열 최소 개수
     * @return
     */
    public int getCheckRepeatedCnt(){
        return config.getInt(ConfigKeys.PASSWORD_INPUT_REPEATED, 3);
    }

    /**
     * 비밀번호 사용자 아이디 포함 가능 여부
     * @return
     */
    public boolean getCheckUserId(){
        return config.getBoolean(ConfigKeys.PASSWORD_ALLOW_USERID, false);
    }

    /**
     * 비밀번호 사용자 이름 포함 가능 여부
     * @return
     */
    public boolean getCheckUserName(){
        return config.getBoolean(ConfigKeys.PASSWORD_ALLOW_USER_NAME, false);
    }

    /**
     * 비밀번호 사용자 이메일 포함 가능 여부
     * @return
     */
    public boolean getCheckEmail(){
        return config.getBoolean(ConfigKeys.PASSWORD_ALLOW_EMAIL, false);
    }

    /**
     * 이전 비밀번호 사용 허용 여부
     * @return
     */
    public boolean getCheckHistory(){
        return config.getBoolean(ConfigKeys.PASSWORD_ALLOW_HISTORY, false);
    }

    /**
     * 이전 비밀번호 확인 개수
     * @return
     */
    public int getCheckHistoryCnt(){
        return config.getInt(ConfigKeys.PASSWORD_CHECK_HISTORY_COUNT, 3);
    }

    /**
     * 비밀번호 만료일
     * @return
     */
    public int getExpiredDay(){
        return config.getInt(ConfigKeys.PASSWORD_CHECK_EXPIRED, 180);
    }

    /**
     * 최초 로그인 시 비밀번호 강제 여부
     * @return
     */
    public boolean getForceInitChange(){
        return config.getBoolean(ConfigKeys.PASSWORD_CHECK_INIT, true);
    }

    /**
     * 최초 로그인 시, 최초 비밀번호 만료 기간
     * @return
     */
    public int getInitExpireDay(){
        return config.getInt(ConfigKeys.PASSWORD_CHECK_INIT_EXPIRED, 7);
    }

    /**
     * 비밀번호 SALT 사용 여부
     * @return
     */
    public boolean getCheckSalt(){
        return config.getBoolean(ConfigKeys.PASSWORD_ALLOW_SALT, false);
    }

    /**
     * 비밀번호 SALT 길이
     * @return
     */
    public int getSaltLength(){
        return config.getInt(ConfigKeys.PASSWORD_CHECK_SALT_LENGTH, 16);
    }
    
}

