package org.project.custom.domain.user.entity;

import jakarta.persistence.*;
import org.jspecify.annotations.Nullable;
import org.project.custom.common.constants.UserStatus;
import org.project.custom.common.constants.UserType;
import org.project.custom.common.converter.BooleanToYnConverter;
import org.project.custom.common.jpa.entity.AbstractBaseEntity;
import org.springframework.data.domain.Persistable;

import java.util.Objects;

@Entity
@Table( name = "ism_cm_user", uniqueConstraints = @UniqueConstraint( name = "ux_ism_cm_user_user_id", columnNames = "user_id"),
        indexes = {
                @Index(name = "index_ism_cm_user_status", columnList = "status"),
                @Index( name = "index_ism_cm_user_use_yn", columnList = "use_yn")
        }
)
public class CmUserEntity extends AbstractBaseEntity implements Persistable<String> {

    private static final long serialVersionUID = 1L;

    protected CmUserEntity() {
    }

    @Id
    @Column(name = "oid", length = 255, nullable = false, updatable = false)
    private String oid;

    @Column(name = "user_id", length = 255, nullable = false, updatable = false)
    private String userId;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "password_salt", length = 255)
    private String passwordSalt;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "hand_phone", length = 255)
    private String handPhone;

    @Column(name = "gender", length = 1)
    private String gender;

    @Column(name = "birthdate", length = 50)
    private String birthdate;

    @Column(name = "last_name", length = 255)
    private String lastName;

    @Column(name = "first_name", length = 255)
    private String firstName;

    @Column(name = "picture", length = 255)
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 20, nullable = false)
    private UserType userType = UserType.USER;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 50, nullable = false)
    private UserStatus userStatus = UserStatus.ACTIVE;

    @Convert(converter = BooleanToYnConverter.class)
    @Column(name = "use_yn", length = 1, nullable = false)
    private Boolean useYn = true;

    @Convert(converter = BooleanToYnConverter.class)
    @Column(name = "locked", length = 1, nullable = false)
    private Boolean locked = false;

    @Convert(converter = BooleanToYnConverter.class)
    @Column(name = "two_factor")
    private Boolean twoFactor = false;

    @Column(name = "login_fail_count", nullable = false)
    private int loginFailCount;

    @Column(name = "last_fail_time")
    private Long lastFailTime;

    @Column(name = "last_login_time")
    private Long lastLoginTime;

    @Column(name = "last_login_ip", length = 50)
    private String lastLoginIp;

    @Column(name = "start_time")
    private Long startTime;

    @Column(name = "end_time")
    private Long endTime;

    @Column(name = "password_change_time")
    private Long passwordChangeTime;

    @Column(name = "delete_user", length = 255)
    private String deleteUser;

    @Column(name = "delete_time")
    private Long deleteTime;

    @Column(name = "first_str")
    private String firstStr;

    @Column(name = "second_str")
    private String secondStr;

    @Column(name = "third_str")
    private String thirdStr;

    @Column(name = "custom_info")
    private String customInfo;

    @Transient
    private boolean isNew = true;

    @Override
    public boolean isNew() {
        return isNew;
    }

    @PostLoad
    @PostPersist
    void markNotNew(){
        this.isNew = false;
    }

    @Override
    public @Nullable String getId() {
        return oid;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CmUserEntity other)) return false;
        return oid != null && oid.equals(other.oid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(CmUserEntity.class);
    }
}
