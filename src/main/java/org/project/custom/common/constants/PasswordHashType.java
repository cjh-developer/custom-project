package org.project.custom.common.constants;

import org.project.custom.common.util.StringCheck;

public enum PasswordHashType {
    SHA_256("SHA-256"),
    SHA_512("SHA-512"),
    NONE("NONE")
    ;

    private final String hashName;

    PasswordHashType(String name) {
        this.hashName = name;
    }

    public String getHashName() {
        return hashName;
    }

    public boolean isRaw(){
        return this == NONE;
    }

    public static PasswordHashType of( String value){
        if(StringCheck.isEmpty(value)){
            return SHA_512;
        }
        return PasswordHashType.valueOf(value.trim().toUpperCase().replace('-', '_'));
    }
}
