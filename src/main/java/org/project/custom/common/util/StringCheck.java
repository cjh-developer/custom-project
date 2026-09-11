package org.project.custom.common.util;

public class StringCheck {

    private StringCheck(){}

    public static boolean isEmpty(String value){
        if( value == null || value.trim().length() == 0 || value.isEmpty()){
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(String value){
        if( !isEmpty(value) ){
            return true;
        }
        return false;
    }

    /**
     * 빈 문자열 Null 통일
     * @param value
     * @return
     */
    public static String emptyToNull(String value){
        if( isEmpty(value) ){
            return null;
        }

        return value.trim();
    }

    /**
     * value 값이 없는 경우, defaultValue 정보 전달
     * @param value
     * @param defaultValue
     * @return
     */
    public static String nullToDefault(String value, String defaultValue){
        return isEmpty(value) ? defaultValue : value.trim();
    }
}
