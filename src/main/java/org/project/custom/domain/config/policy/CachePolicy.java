package org.project.custom.domain.config.policy;

import org.project.custom.domain.config.constatns.ConfigKeys;
import org.project.custom.domain.config.service.CmCacheService;

/**
 * 캐시 정책
 */
public final class CachePolicy {

    private final CmCacheService config;

    public CachePolicy(CmCacheService config) {
        this.config = config;
    }

    public static CachePolicy of(CmCacheService config) {
        return new CachePolicy(config);
    };

    /**
     * 사용자 캐시 사용 여부
     * @return
     */
    public boolean useUserCache(){
        return config.getBoolean(ConfigKeys.CONFIG_ALLOW_USER_CACHE, true);
    }

}
