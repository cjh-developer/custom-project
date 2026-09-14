package org.project.custom.domain.config.service;

import org.project.custom.common.constants.CommonErrorCode;
import org.project.custom.common.exception.CustomException;
import org.project.custom.domain.config.entity.CmConfigEntity;
import org.project.custom.domain.config.repository.CmConfigRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 정책 CRUD 전용
 */
@Service("cmConfigManageService")
@Transactional(readOnly=true)
public class CmConfigManageService {

    private final CmConfigRepository cmConfigRepository;
    private final ApplicationEventPublisher eventPublisher;

    public CmConfigManageService(CmConfigRepository cmConfigRepository, ApplicationEventPublisher eventPublisher) {
        this.cmConfigRepository = cmConfigRepository;
        this.eventPublisher = eventPublisher;
    }

    public List<CmConfigEntity> listAll(){
        return this.cmConfigRepository.findAllByOrderByConfigGroupAscSortOrderAscOidAsc();
    }

    public List<CmConfigEntity> listGroup(String configGroup){
        return cmConfigRepository.findAllByConfigGroupOrderBySortOrderAscOidAsc(configGroup);
    }

    public CmConfigEntity get(String oid){
        return cmConfigRepository.findById(oid).orElseThrow() -> new CustomException(CommonErrorCode.DATA_NOT_FOUND);
    }
}
