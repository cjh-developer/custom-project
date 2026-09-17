package org.project.custom.domain.config.constatns;

public enum DuplicateLoginMethod {
    /** 먼저 로그인한 세션을 끊는다. 나중 로그인이 이긴다. */
    BEFORE,

    /** 나중 로그인을 막는다. 먼저 로그인한 세션이 이긴다. */
    AFTER
    ;
}
