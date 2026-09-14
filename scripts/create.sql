

CREATE TABLE cus_cm_config (
                               oid           VARCHAR(255) NOT NULL COMMENT '정책 키. 예) userid.max.length',
                               config_group  VARCHAR(50)  NOT NULL COMMENT 'oid 첫 마디에서 파생. 예) USERID',
                               config_value  VARCHAR(4000)         COMMENT '운영자가 설정한 값',
                               default_value VARCHAR(4000)         COMMENT 'config_value 가 비었을 때 사용할 값',
                               value_type    VARCHAR(20)  NOT NULL COMMENT 'STRING / NUMBER / BOOLEAN',
                               use_yn        CHAR(1)      NOT NULL DEFAULT 'Y',
                               sort_order    INT          NOT NULL DEFAULT 0,
                               description   VARCHAR(255),
                               insert_user   VARCHAR(255),
                               insert_time   BIGINT       NOT NULL,
                               update_user   VARCHAR(255),
                               update_time   BIGINT       NOT NULL,
                               PRIMARY KEY (oid),
                               KEY index_cus_cm_config_group  (config_group),
                               KEY index_cus_cm_config_use_yn (use_yn)
) COMMENT='공통 정책 설정';


CREATE TABLE cus_cm_cache (
                              cache_group   VARCHAR(100) NOT NULL,
                              cache_name    VARCHAR(100) NOT NULL,
                              cache_value   VARCHAR(2000),
                              insert_user   VARCHAR(255),
                              insert_time   BIGINT       NOT NULL,
                              update_user   VARCHAR(255),
                              update_time   BIGINT       NOT NULL,
                              PRIMARY KEY (cache_group, cache_name)
) COMMENT='서버 간 캐시 무효화 신호';