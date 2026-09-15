package org.project.custom.domain.config.prop;

public final class CmCacheName {

    private CmCacheName(){
        throw new AssertionError("CacheName is not instance");
    }

    public static final String CONFIG = "CUS_CONFIG_CACHE";
    public static final String NAME_VERSION = "VERSION";
}
