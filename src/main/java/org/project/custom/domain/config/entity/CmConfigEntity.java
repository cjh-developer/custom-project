package org.project.custom.domain.config.entity;

import jakarta.persistence.*;
import org.jspecify.annotations.Nullable;
import org.project.custom.common.constants.CommonErrorCode;
import org.project.custom.common.constants.ConfigValueType;
import org.project.custom.common.converter.BooleanToYnConverter;
import org.project.custom.common.exception.CustomException;
import org.project.custom.common.jpa.entity.AbstractBaseEntity;
import org.project.custom.common.util.StringCheck;
import org.springframework.data.domain.Persistable;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * 공통 정책.
 *
 * 이 테이블의 oid 는 IdGenerator 가 발급하지 않는 <b>자연키</b>다.
 * 정책 키 문자열 자체가 식별자이므로 의도적으로 규칙에서 예외를 둔다. (예: userid.max.length)
 */
@Entity
@Table(name = "cus_cm_config",
        indexes = {
                @Index(name = "index_cus_cm_config_group",  columnList = "config_group"),
                @Index(name = "index_cus_cm_config_use_yn", columnList = "use_yn")
        }
)
public class CmConfigEntity extends AbstractBaseEntity implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    /** 소문자 마디를 '.' 로 연결. 최소 2마디. */
    private static final Pattern KEY_PATTERN = Pattern.compile("^[a-z][a-z0-9]*(\\.[a-z0-9]+)+$");

    protected CmConfigEntity() {
    }

    @Id
    @Column(name = "oid", length = 255, nullable = false, updatable = false)
    private String oid;

    @Column(name = "config_group", length = 50, nullable = false, updatable = false)
    private String configGroup;

    @Column(name = "config_value", length = 4000)
    private String configValue;

    @Column(name = "default_value", length = 4000)
    private String defaultValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "value_type", length = 20, nullable = false)
    private ConfigValueType valueType = ConfigValueType.STRING;

    @Convert(converter = BooleanToYnConverter.class)
    @Column(name = "use_yn", length = 1, nullable = false)
    private Boolean useYn = true;

    @Column(name = "sort_order", nullable = false)
    private int sortOrder;

    @Column(name = "description", length = 255)
    private String description;

    @Transient
    private boolean isNew = true;

    public static CmConfigEntity create(String oid, String configValue, String defaultValue,
                                        ConfigValueType valueType, int sortOrder, String description) {
        CmConfigEntity entity = new CmConfigEntity();
        entity.oid          = validateKey(oid);
        entity.configGroup  = toGroup(entity.oid);
        entity.valueType    = valueType == null ? ConfigValueType.STRING : valueType;
        entity.defaultValue = StringCheck.emptyToNull(defaultValue);
        entity.sortOrder    = sortOrder;
        entity.description  = StringCheck.emptyToNull(description);
        entity.changeValue(configValue);
        return entity;
    }

    public void changeValue(String configValue) {
        if (!valueType.isValid(configValue)) {
            throw new CustomException(CommonErrorCode.COMMON_ERROR,
                    "정책 값 형식이 올바르지 않습니다. oid=" + oid + ", type=" + valueType + ", value=" + configValue);
        }
        this.configValue = StringCheck.emptyToNull(configValue);
    }

    public void changeDefaultValue(String defaultValue) {
        this.defaultValue = StringCheck.emptyToNull(defaultValue);
    }

    public void changeUseYn(boolean useYn) {
        this.useYn = useYn;
    }

    public void changeDescription(String description) {
        this.description = StringCheck.emptyToNull(description);
    }

    /** 실제 적용값. config_value 가 있으면 그 값, 없으면 default_value. 둘 다 없으면 null */
    @Transient
    public String resolveValue() {
        return StringCheck.isNotEmpty(configValue) ? configValue.trim() : defaultValue;
    }

    /** userid.max.length → USERID */
    private static String toGroup(String oid) {
        int index = oid.indexOf('.');
        return (index < 0 ? oid : oid.substring(0, index)).toUpperCase();
    }

    private static String validateKey(String oid) {
        String target = StringCheck.emptyToNull(oid);
        if (target == null || !KEY_PATTERN.matcher(target).matches()) {
            throw new CustomException(CommonErrorCode.COMMON_ERROR,
                    "정책 키 형식이 올바르지 않습니다. 예) userid.max.length, oid=" + oid);
        }
        return target;
    }

    @Override public boolean isNew() { return isNew; }

    @PostLoad @PostPersist
    void markNotNew() { this.isNew = false; }

    @Override public @Nullable String getId() { return oid; }

    public String getOid()                { return oid; }
    public String getConfigGroup()        { return configGroup; }
    public String getConfigValue()        { return configValue; }
    public String getDefaultValue()       { return defaultValue; }
    public ConfigValueType getValueType() { return valueType; }
    public Boolean getUseYn()             { return useYn; }
    public int getSortOrder()             { return sortOrder; }
    public String getDescription()        { return description; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CmConfigEntity other)) return false;
        return oid != null && oid.equals(other.oid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(CmConfigEntity.class);
    }
}