package org.project.custom.domain.config.policy;

import org.project.custom.common.constants.UserIdFirstWordType;
import org.project.custom.domain.config.constatns.ConfigKeys;
import org.project.custom.domain.config.service.CmCacheService;

import java.util.List;

/**
 * 사용자 아이디 정책
 */
public final class UserIdPolicy {

    private final CmCacheService config;

    public UserIdPolicy(CmCacheService config) {
        this.config = config;
    }

    public static UserIdPolicy of(CmCacheService config) {
        return new UserIdPolicy(config);
    }

    /**
     * 아이디 최소 길이
     * @return
     */
    public int getMinLength(){
        return config.getInt(ConfigKeys.USERID_MIN_LENGTH, 5);
    }

    /**
     * 아이디 최대 길이
     * @return
     */
    public int getMaxLength(){
        return config.getInt(ConfigKeys.USERID_MAX_LENGTH, 30);
    }

    /**
     * 아이디 첫 번째 문장
     * @return
     */
    public UserIdFirstWordType getFirstWord(){
        return config.getEnum(UserIdFirstWordType.class, ConfigKeys.USERID_CHECK_FIRST_WORD, UserIdFirstWordType.ANY);
    }

    /**
     * 공백 문자 허용 여부
     * @return
     */
    public boolean getAllowSpace(){
        return config.getBoolean(ConfigKeys.USERID_ALLOW_SPACE, false);
    }

    /**
     * 아이디 한국어 사용 가능 여부
     * @return
     */
    public boolean getAllowKorean(){
        return config.getBoolean(ConfigKeys.USERID_ALLOW_KOREAN, false);
    }

    /**
     * 금지어 목록
     * @return
     */
    public List<String> getDenyWords(){
        return config.getList(ConfigKeys.USERID_CHECK_WORD);
    }

    public boolean getAllowConsecutive(){
        return config.getBoolean(ConfigKeys.USERID_ALLOW_CONSECUTIVE, false);
    }

    public int getMaxConsecutive(){
        return config.getInt(ConfigKeys.USERID_CHECK_CONSECUTIVE, 3);
    }

    /**
     * 연속 문자 검사 수행 여부
     * @return
     */
    public boolean isConsecutiveCheck(){
        return !getAllowConsecutive() && getMaxConsecutive() > 0;
    }

    /**
     * 시작 문자 검사 수행 여부
     * @return
     */
    public boolean isFirstWordCheck(){
        return UserIdFirstWordType.ANY != getFirstWord();
    }


}
