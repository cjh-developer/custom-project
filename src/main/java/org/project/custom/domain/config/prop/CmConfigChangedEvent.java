package org.project.custom.domain.config.prop;

/**
 * 정책 변경 신호 ( 커밋 이후 처리 )
 * @param changedTime
 */
public record CmConfigChangedEvent(long changedTime) {
}
