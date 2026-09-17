package org.project.custom.domain.config.policy;

import org.project.custom.domain.config.constatns.DuplicateLoginMethod;
import org.project.custom.domain.config.constatns.ConfigKeys;
import org.project.custom.domain.config.service.CmCacheService;

/**
 * 로그인 정책
 */
public final class LoginPolicy {

    private final CmCacheService config;

    public LoginPolicy(CmCacheService config) {
        this.config = config;
    }

    public static LoginPolicy of(CmCacheService config) {
        return new LoginPolicy(config);
    }

    /**
     * 로그인 실패 확인 사용 여부
     * @return
     */
    public boolean getCheckLoginFail(){
        return config.getBoolean(ConfigKeys.LOGIN_ALLOW_LIMIT_FAIL_COUNT, true);
    }

    /**
     * 로그인 실패 최대 개수
     * @return
     */
    public int getCheckLoginFailCnt(){
        return config.getInt(ConfigKeys.LOGIN_CHECK_LIMIT_FAIL_COUNT, 5);
    }

    /**
     * 계정 잠금 여부
     * @return
     */
    public boolean getCheckLock(){
        return config.getBoolean(ConfigKeys.LOGIN_ALLOW_LOCKED, true);
    }

    /**
     * 계정 잠금 시, 해제 시간
     * @return
     */
    public int getLockTime(){
        return config.getInt(ConfigKeys.LOGIN_CHECK_LOCKED_TIME, 5);
    }

    /**
     * 중복 로그인 검사 여부
     * @return
     */
    public boolean getCheckDuplicated(){
        return config.getBoolean(ConfigKeys.LOGIN_ALLOW_DUPLICATED, false);
    }

    /**
     * 중복 로그인 가능 최대 개수
     * @return
     */
    public int getDuplicatedCnt(){
        return config.getInt(ConfigKeys.LOGIN_CHECK_DUPLICATED_COUNT, 1);
    }

    /**
     * 중복 로그인 시 로그아웃 시킬 대상
     * @return
     */
    public DuplicateLoginMethod getDuplicateLoginMethod(){
        return config.getEnum(DuplicateLoginMethod.class, ConfigKeys.LOGIN_CHECK_DUPLICATED_METHOD, DuplicateLoginMethod.BEFORE);
    }

    /**
     * 자동 로그아웃 사용 여부
     * @return
     */
    public boolean getCheckAutoLogout(){
        return config.getBoolean(ConfigKeys.LOGIN_ALLOW_AUTO_LOGOUT, false);
    }

    /**
     * 자동 로그아웃 시간
     * @return
     */
    public int getAutoLogoutTime(){
        return config.getInt(ConfigKeys.LOGIN_CHECK_AUTO_LOGOUT, 120);
    }

    /**
     * 로그인 유지(자동 로그아웃 false)
     * @return
     */
    public boolean getCheckRemember(){
        return config.getBoolean(ConfigKeys.LOGIN_ALLOW_REMEMBER, false);
    }

    /**
     * 로그인 이력 저장 여부
     * @return
     */
    public boolean getSaveHistory(){
        return config.getBoolean(ConfigKeys.LOGIN_ALLOW_SAVE_HISTORY, true);
    }

    /**
     * 로그인 이력 저장(달)
     * @return
     */
    public int getSaveHistoryDay(){
        return config.getInt(ConfigKeys.LOGIN_CHECK_SAVE_HISTORY, 12);
    }

}
