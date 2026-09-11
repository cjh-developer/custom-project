package org.project.custom.common.constants;

import org.jspecify.annotations.Nullable;
import org.project.custom.common.util.StringCheck;
import org.springframework.security.core.GrantedAuthority;

public enum UserType implements GrantedAuthority {

    USER("일반 사용자"),
    ADMIN("관리자")
    ;

    public static final String PREFIX = "ROLE_";
    public final String label;

    UserType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public String getCode(){
        return name();
    }


    @Override
    public @Nullable String getAuthority() {
        return PREFIX + name();
    }

    public boolean isAdmin(){
        return this == ADMIN;
    }

    public static UserType of(String value){
        if(StringCheck.isEmpty(value)){
            throw new IllegalArgumentException("not Authority");
        }
        String v = value.trim().toUpperCase();
        if (v.startsWith(PREFIX)) {
            v = v.substring(PREFIX.length());
        }
        return UserType.valueOf(v);
    }
}
