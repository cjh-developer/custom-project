package org.project.custom.common.constants;

import org.project.custom.common.util.StringCheck;

public enum ConfigValueType {
    STRING,
    NUMBER,
    BOOLEAN
    ;

    /**
     * 저장 시 형식 검증 ( 빈 값은 default_value로 대체 통과 )
     * @param value
     * @return
     */
    public boolean isValid(String value){
        if(StringCheck.isEmpty(value)){
            return true;
        }
        String target = value.trim();
        return switch (this){
            case STRING -> true;
            case NUMBER -> target.matches("^-?\\d+$");
            case BOOLEAN -> "Y".equalsIgnoreCase(target) || "N".equalsIgnoreCase(target) || "TRUE".equalsIgnoreCase(target) || "FALSE".equalsIgnoreCase(target)
                            || "1".equals(target) || "0".equals(target);
        };
    }

    public static ConfigValueType of(String value){
        if(StringCheck.isEmpty(value)){
            return STRING;
        }
        return ConfigValueType.valueOf(value.trim().toUpperCase());
    }
}
