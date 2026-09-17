package org.project.custom.domain.config.service;

import org.project.custom.domain.config.policy.*;
import org.springframework.stereotype.Component;

/**
 * 정책 단일 주입 
 * - 호출 시 마다 새 객체 생성, 참조가 하나 뿐이라 비용이 적음 ( 값은 항상 최신 캐시 확인 )
 */
@Component
public class PolicyProvider {

    private final CmCacheService config;

    public PolicyProvider(CmCacheService config) {
        this.config = config;
    }

    public UserIdPolicy userIdPolicy(){
        return UserIdPolicy.of(config);
    }

    public PasswordPolicy passwordPolicy(){
        return PasswordPolicy.of(config);
    }

    public LoginPolicy loginPolicy(){
        return LoginPolicy.of(config);
    }

    public LogoutPolicy  logoutPolicy(){
        return LogoutPolicy.of(config);
    }

    public UserPolicy userPolicy(){
        return UserPolicy.of(config);
    }

    public CachePolicy cachePolicy(){
        return CachePolicy.of(config);
    }
    
}
