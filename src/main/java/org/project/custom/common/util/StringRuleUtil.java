package org.project.custom.common.util;

/**
 * 문자열 규칙 검사
 */
public final class StringRuleUtil {

    private StringRuleUtil() {
        throw new AssertionError("StringRuleUtil cannot be instantiated.");
    }

    /**
     * 연속된 문자가 limit개 이상 들어 있는가.
     * @param value
     * @param limit
     * @return
     */
    public static boolean hasConsecutive(String value, int limit){
        if(StringCheck.isEmpty(value)){
            return false;
        }

        if(limit <= 1){
            return false;
        }

        if(value.length() < limit){
            return false;
        }

        int up = 1;
        int down = 1;

        for( int i = 1; i < value.length(); i++ ){
            int diff = value.charAt(i) - value.charAt(i - 1);

            if(diff == 1){
                up++;
            }else{
                up = 1;
            }

            if(diff == -1){
                down++;
            }else{
                down = 1;
            }

            if(up >= limit){
                return true;
            }

            if(down >= limit){
                return true;
            }
        }
        return false;
    }

    /**
     * 같은 문자가 limit 개 이상 연속되는 가
     * @param value
     * @param limit
     * @return
     */
    public static boolean hasRepeated(String value, int limit){
        if(StringCheck.isEmpty(value)){
            return false;
        }

        if(limit <= 1){
            return false;
        }

        if(value.length() < limit){
            return false;
        }

        int count = 1;

        for(int i = 1; i < value.length(); i++){
            if(value.charAt(i) == value.charAt(i-1)){
                count++;
            }else{
                count = 1;
            }

            if( count >= limit){
                return true;
            }
        }
        return false;
    }

    /**
     * 영문자 개수
     * @param value
     * @return
     */
    public static int countAlpha(String value){
        if(StringCheck.isEmpty(value)){
            return 0;
        }

        int count = 0;
        for(char c : value.toCharArray()){
            if( c >= 'a' && c <= 'z'){
                count++;
                continue;
            }
            if(c >= 'A' && c <= 'Z'){
                count++;
            }
        }
        return count;
    }

    /**
     * 숫자 개수
     * @param value
     * @return
     */
    public static int countNumber(String value){
        if(StringCheck.isEmpty(value)){
            return 0;
        }

        int count = 0;
        for(char c : value.toCharArray()){
            if(c >= '0' && c <= '9'){
                count++;
            }
        }
        return count;
    }

    /**
     * 공백 문자가 들어있는가
     * @param value
     * @return
     */
    public static boolean hasSpace(String value){
        if(StringCheck.isEmpty(value) || value == null){
            return false;
        }

        for(char c : value.toCharArray()){
            if( Character.isWhitespace(c)){
                return true;
            }
        }
        return false;
    }

    /**
     * 특수문자가 들어있는가 ( 한글은 판단 불가 hasKorean 사용 )
     * @param value
     * @return
     */
    public static boolean hasSpecial(String value){
        if(StringCheck.isEmpty(value)){
            return false;
        }

        for(char c : value.toCharArray()){
            if( c > 127){
                continue;
            }
            if( Character.isLetterOrDigit(c)){
                continue;
            }
            if(Character.isWhitespace(c)){
                continue;
            }
            return true;
        }
        return false;
    }

    /**
     * 한글이 포함되어 있는가
     * @param value
     * @return
     */
    public static boolean hasKorean(String value){
        if(StringCheck.isEmpty(value)){
            return false;
        }

        for(char c : value.toCharArray()){
            if (c >= 0xAC00 && c <= 0xD7A3){
                return true;
            }

            if( c >= 0x3131 && c <= 0x318E){
                return true;
            }
        }
        return false;
    }

    /**
     * 대소문자 무시가 된 상태인가
     * @param value
     * @param keyword
     * @return
     */
    public static boolean containsIgnoreCase(String value, String keyword){
        if(StringCheck.isEmpty(value) || StringCheck.isEmpty(keyword)){
            return false;
        }

        return value.toLowerCase().contains(keyword.toLowerCase());
    }
}
