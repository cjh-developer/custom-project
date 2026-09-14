package org.project.custom.domain.config.repository;

import org.project.custom.domain.config.entity.CmConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CmConfigRepository extends JpaRepository<CmConfigEntity,String> {

    /**
     * 캐시 적재용
     */
    List<CmConfigEntity> findAllByUseYnOrderByConfigGroupAscSortOrderAscOidAsc(Boolean useYn);

    /**
     * 관리화면 : 구분별 (사용안함 포함)
     */
    List<CmConfigEntity> findAllByConfigGroupOrderBySortOrderAscOidAsc(String configGroup);

    /**
     * 키 접두사 조회
     * @return
     */
    List<CmConfigEntity> findAllByOrderByConfigGroupAscSortOrderAscOidAsc();
    
}
