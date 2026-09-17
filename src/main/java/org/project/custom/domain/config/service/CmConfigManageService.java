package org.project.custom.domain.config.service;

import org.project.custom.common.constants.CommonErrorCode;
import org.project.custom.domain.config.constatns.ConfigValueType;
import org.project.custom.common.exception.CustomException;
import org.project.custom.domain.config.entity.CmConfigEntity;
import org.project.custom.domain.config.prop.CmConfigChangedEvent;
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
        return cmConfigRepository.findAllByConfigGroupOrderBySortOrderAscOidAsc(configGroup.toUpperCase());
    }

    public CmConfigEntity get(String oid){
        return cmConfigRepository.findById(oid).orElseThrow( () -> new CustomException(CommonErrorCode.DATA_NOT_FOUND, "정책을 찾을 수 없습니다. oid = " + oid));
    }

    /**
     * 정책 등록
     * @param oid
     * @param configValue
     * @param defaultValue
     * @param valueType
     * @param sortOrder
     * @param description
     * @return
     */
    @Transactional
    public CmConfigEntity add(String oid, String configValue, String defaultValue, ConfigValueType valueType, int sortOrder, String description){
        if(cmConfigRepository.existsById(oid)){
            throw new CustomException(CommonErrorCode.COMMON_ERROR, "이미 존재하는 정책입니다.");
        }

        CmConfigEntity saved = cmConfigRepository.save(CmConfigEntity.create(oid, configValue, defaultValue, valueType, sortOrder, description));
        publishChanged();
        return saved;
    }

    /**
     * 정책 값 수정
     * @param oid
     * @param configValue
     */
    @Transactional
    public void edit(String oid, String configValue){
        get(oid).changeValue(configValue);
        publishChanged();
    }

    /**
     * 사용 여부 수정
     * @param oid
     * @param useYn
     */
    @Transactional
    public void editUseYn(String oid, boolean useYn){
        get(oid).changeUseYn(useYn);
        publishChanged();
    }

    @Transactional
    public void remove(String oid){
        cmConfigRepository.delete(get(oid));
        publishChanged();
    }

    private void publishChanged() {
        eventPublisher.publishEvent(new CmConfigChangedEvent(System.currentTimeMillis()));
    }
}
