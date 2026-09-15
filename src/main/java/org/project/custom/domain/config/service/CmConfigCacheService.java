package org.project.custom.domain.config.service;

import org.project.custom.common.util.StringCheck;
import org.project.custom.domain.config.entity.CmConfigEntity;
import org.project.custom.domain.config.prop.CmConfigChangedEvent;
import org.project.custom.domain.config.repository.CmConfigRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * cus_cm_config 전체를 한 번에 읽어 메모리에 저장
 * 개별 키 조회로 DB를 왕복하지 않는다.
 */
@Service("cmConfigCacheService")
@Transactional(readOnly=true)
public class CmConfigCacheService {

    private static final Logger logger = LoggerFactory.getLogger(CmConfigCacheService.class);

    private final CmConfigRepository cmConfigRepository;

    /**
     * 불변 맵 전체 교체
     */
    private volatile Map<String, String> cache = Map.of();

    /**
     * 마지막으로 반영한 cus_cm_config의 버전 정보
     */
    private volatile String cacheVersion = "";

    public CmConfigCacheService(CmConfigRepository cmConfigRepository) {
        this.cmConfigRepository = cmConfigRepository;
    }

    public String getValue(String oid){
        return getValue(oid, null);
    }

    public String getValue(String oid, String defaultValue) {
        if(StringCheck.isEmpty(oid)){
            return defaultValue;
        }
        String value = cache.get(oid);
        return StringCheck.isNotEmpty(value) ? value : defaultValue;
    }

    public Map<String, String> getAll() {
        return cache;
    }

    public int size(){
        return cache.size();
    }

    /**
     * DataSource 준비 전에 돌지 않음
     */
    @EventListener(ApplicationReadyEvent.class)
    public void initCache(){
        reload();
        logger.info("정책 캐시 초기 적재, count = {}", cache.size());
    }

    public void reload(){
        Map<String, String> loaded = new HashMap<>();
        try{
            for(CmConfigEntity row : cmConfigRepository.findAllByUseYnOrderByConfigGroupAscSortOrderAscOidAsc(true)){
                String value = row.resolveValue();
                if(StringCheck.isNotEmpty(value)){
                    loaded.put(row.getOid(), value.trim());
                }
            }
        }catch(RuntimeException e){
            logger.error("정책 캐시 적재 실패. 직전 캐시 유지, count = {}", cache.size());
            return;
        }
        this.cache = Map.copyOf(loaded);
    }

    /**
     * 정책 변경 반영. 커밋 이후 처리
     * @param event
     */
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onConfigChanged(CmConfigChangedEvent event){
        reload();
        logger.info("정책 변경으로 캐시를 갱신하였스빈다. count = {}", cache.size());
    }
}
