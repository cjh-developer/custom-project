package org.project.custom.domain.config.service;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 캐시를 바라보는 기본 구현체
 */
@Service("defaultCmConfigCacheService")
public class DefaultCmConfigCacheService implements CmCacheService {

    private final CmConfigCacheService cmConfigCacheService;

    public DefaultCmConfigCacheService(CmConfigCacheService cmConfigCacheService) {
        this.cmConfigCacheService = cmConfigCacheService;
    }

    @Override
    public String getValue(String oid, String defaultValue) {
        return cmConfigCacheService.getValue(oid, defaultValue);
    }

    @Override
    public Map<String, String> getAllValues() {
        return cmConfigCacheService.getAll();
    }
}
