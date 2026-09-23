package org.project.custom.domain.config.validate;

import org.project.custom.common.constants.CommonErrorCode;
import org.project.custom.common.exception.CustomException;
import org.project.custom.common.util.StringCheck;
import org.project.custom.common.util.StringRuleUtil;
import org.project.custom.domain.config.constatns.UserIdFirstWordType;
import org.project.custom.domain.config.policy.UserIdPolicy;
import org.project.custom.domain.config.service.PolicyProvider;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 사용자 아이디 정책 검증
 * 검사 사용 여부는 해당 서비스에서 관할 ( 실제로 어떻게 세는지는 StringRuleUtil 이 안다.)
 */
@Service
public class UserIdValidateService {

    private final PolicyProvider policyProvider;

    public UserIdValidateService(PolicyProvider policyProvider) {
        this.policyProvider = policyProvider;
    }

    private UserIdPolicy policy(){
        return policyProvider.userIdPolicy();
    }

    /* ==================== 검사 수행 여부 ==================== */

    /**
     * 연속 문자 검사를 해야 하는 가
     * @return
     */
    public boolean isConsecutiveCheck(){
        UserIdPolicy policy = policy();

        // 연속 문자 허용 시, 검사 진행 X
        if(policy.getAllowConsecutive()){
            return false;
        }

        if(policy.getMaxConsecutive() <= 0){
            return false;
        }

        return true;
    }

    /**
     * 시작 문자 검사를 수행하는 가
     * @return
     */
    public boolean isFirstWordCheck(){
        if(UserIdFirstWordType.ANY == policy().getFirstWord()){
            return false;
        }
        return true;
    }

    /**
     * 길이가 정책 범위 이내인지 검사 ( 최소/최대 길이 포함 )
     * @param userId
     * @return
     */
    public boolean isLengthValid(String userId){
        if(StringCheck.isEmpty(userId)){
            return false;
        }

        if(userId.length() < policy().getMinLength()){
            return false;
        }

        if(userId.length() > policy().getMaxLength()){
            return false;
        }

        return true;
    }

    /**
     * 정규식에 맞는지
     * @param userId
     * @return
     */
    public boolean isPatternValid(String userId){
        if(StringCheck.isEmpty(userId)){
            return false;
        }

        String pattern = policy().getPattern();
        if(StringCheck.isEmpty(pattern)){
            return false;
        }

        return userId.matches(pattern);
    }

    /**
     * 첫 글자가 정책에 맞는가
     * @param userId
     * @return
     */
    public boolean isFirstWordValid(String userId){
        if(StringCheck.isEmpty(userId)){
            return false;
        }

        if( !isFirstWordCheck()){
            return true;
        }

        char first = userId.charAt(0);
        UserIdFirstWordType type = policy().getFirstWord();

        if(UserIdFirstWordType.UPPER == type){
            return first >= 'A' && first <= 'Z';
        }

        if(UserIdFirstWordType.LOWER == type){
            return first >= 'a' && first <= 'z';
        }

        if(UserIdFirstWordType.ALPHA == type){
            if( first > 127){
                return false;
            }
            return Character.isLetter(first);
        }

        if(UserIdFirstWordType.NUMBER == type){
            return first >= '0' && first <= '9';
        }

        if(UserIdFirstWordType.SPECIAL == type){
            if(Character.isLetterOrDigit(first)){
                return false;
            }
            if(Character.isWhitespace(first)){
                return false;
            }
            return true;
        }
        return true;
    }

    /**
     * 금지어에 해당하는가
     * @param userId
     * @return
     */
    public boolean isDenyWord(String userId){
        if(StringCheck.isEmpty(userId)){
            return false;
        }

        List<String> denyWords = policy().getDenyWords();
        if( denyWords == null || denyWords.isEmpty()){
            return false;
        }

        for(String denyWord : denyWords){
            if(userId.equalsIgnoreCase(denyWord)){
                return true;
            }
        }
        return false;
    }

    /**
     * 공백을 사용할 수 없는데, 포함되어 있는가
     * @param userId
     * @return
     */
    public boolean isSpaceViolated(String userId){
        if(policy().getAllowSpace()){
            return false;
        }

        return StringRuleUtil.hasSpace(userId);
    }

    /**
     * 한글을 쓸 수 없는데 포함되어 있는가
     * @param userId
     * @return
     */
    public boolean isKoreanViolated(String userId){
        if(policy().getAllowKorean()){
            return false;
        }
        return StringRuleUtil.hasKorean(userId);
    }

    /**
     * 연속 문자 제한을 검사
     * @param userId
     * @return
     */
    public boolean isConsecutiveViolated(String userId){
        if( !isConsecutiveCheck()){
            return false;
        }

        return StringRuleUtil.hasConsecutive(userId, policy().getMaxConsecutive());
    }

    /**
     * 아이디 정책 전체 검증
     * @param userId
     */
    public void validate(String userId){
        UserIdPolicy policy = policy();

        if(StringCheck.isEmpty(userId)){
            throw new CustomException(CommonErrorCode.USER_ID_IS_EMPTY);
        }

        if( !isLengthValid(userId)){
            throw new CustomException(CommonErrorCode.USER_ID_VALIDATE_ERROR, "아이디는 " + policy.getMinLength() + "~" + policy.getMaxLength() + "사이입니다.");
        }

        if( isSpaceViolated(userId)){
            throw new CustomException(CommonErrorCode.USER_ID_VALIDATE_ERROR);
        }

        if( isKoreanViolated(userId)){
            throw new CustomException(CommonErrorCode.USER_ID_VALIDATE_KOREAN);
        }

        if( !isPatternValid(userId)){
            throw new CustomException(CommonErrorCode.USER_ID_VALIDATE_PATTERN);
        }

        if( !isFirstWordValid(userId)){
            throw new CustomException(CommonErrorCode.USER_ID_VALIDATE_FIRST_WORD);
        }

        if( isConsecutiveViolated(userId)){
            throw new CustomException(CommonErrorCode.USER_ID_VALIDATE_ERROR, "아이디는 " + policy.getMaxConsecutive() + "자 이상 연속된 문자를 사용할 수 없습니다.");
        }

        if( isDenyWord(userId)){
            throw new CustomException(CommonErrorCode.USER_ID_VALIDATE_DENY);
        }
    }

}
