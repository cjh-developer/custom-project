package org.project.custom.domain.config.policy;

import org.project.custom.common.constants.UserStatus;
import org.project.custom.domain.config.constatns.ConfigKeys;
import org.project.custom.domain.config.service.CmCacheService;

/**
 * 사용자 정책
 */
public final class UserPolicy {

    private final CmCacheService config;

    public UserPolicy(CmCacheService config) {
        this.config = config;
    }

    public static UserPolicy of(CmCacheService config) {
        return new UserPolicy(config);
    }

    /**
     * 휴면 계정 검사
     * @return
     */
    public boolean getCheckSleep(){
        return config.getBoolean(ConfigKeys.USER_ALLOW_CHECK_SLEEPER, true);
    }

    /**
     * 휴면 계정 미사용 시간 검사(달)
     * @return
     */
    public int getCheckSleepTime(){
        return config.getInt(ConfigKeys.USER_CHECK_SLEEPER_TIME, 12);
    }

    /**
     * 사용자 등록 시 초기 상태
     * @return
     */
    public UserStatus getInitStatus(){
        return config.getEnum(UserStatus.class, ConfigKeys.USER_CHECK_INIT_STATUS, UserStatus.ACTIVE);
    }

    /**
     * 삭제 시 사용자 정보 삭제 여부
     * @return
     */
    public boolean getDeleteUserInfo(){
        return config.getBoolean(ConfigKeys.USER_CHECK_DELETE_USER_INFO, false);
    }

    /**
     * 삭제 사용자 정보 암호화
     * @return
     */
    public boolean getDeleteUserInfoEnc(){
        return config.getBoolean(ConfigKeys.USER_CHECK_ENC_DELETE_USER_INFO, true);
    }

    /**
     * 탈퇴 사용자 유지 기간(달)
     * @return
     */
    public int getDeleteOutUserTime(){
        return config.getInt(ConfigKeys.USER_CHECK_OUT_TIME, 6);
    }
}
