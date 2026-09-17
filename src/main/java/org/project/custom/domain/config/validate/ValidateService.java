package org.project.custom.domain.config.validate;

import lombok.RequiredArgsConstructor;
import org.project.custom.common.util.StringCheck;
import org.project.custom.domain.config.policy.UserIdPolicy;
import org.project.custom.domain.config.service.PolicyProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidateService {

    private final PolicyProvider policyProvider;

    /**
     * 사용자 아이디 검사
     * @param userId
     * @return
     */
    public boolean validateUserId(String userId){
        UserIdPolicy policy = policyProvider.userIdPolicy();

        if(StringCheck.isEmpty(userId)){
            return false;
        }



        return true;
    }
}
