package org.project.custom.domain.config.policy;

import org.project.custom.domain.config.constatns.ConfigKeys;
import org.project.custom.domain.config.service.CmCacheService;

/**
 * 로그아웃 정책
 */
public final class LogoutPolicy {

    private final CmCacheService config;

    public LogoutPolicy(CmCacheService config) {
        this.config = config;
    }

    public static LogoutPolicy of(CmCacheService config) {
        return new LogoutPolicy(config);
    }

    /**
     * 로그아웃 시 세션 삭제 여부
     * @return
     */
    public boolean invalidateSession() {
        return config.getBoolean(ConfigKeys.LOGOUT_ALLOW_SESSION_INVALIDATE, true);
    }

    /**
     * 로그아웃 시 쿠키 삭제 여부
     * @return
     */
    public boolean invalidateCookie()  {
        return config.getBoolean(ConfigKeys.LOGOUT_ALLOW_COOKIE_INVALIDATE, true);
    }

    /**
     * 로그아웃 시 이동할 주소
     * @return
     */
    public String redirectUri() {
        return config.getString(ConfigKeys.LOGOUT_CHECK_REDIRECT_URI, "/login");
    }
}
