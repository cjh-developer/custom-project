

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

-- 비밀번호 이력 (password.allow.history / check.history.count)
CREATE TABLE cus_cm_password_history (
                                         oid           VARCHAR(255) NOT NULL,
                                         user_oid      VARCHAR(255) NOT NULL,
                                         password      VARCHAR(255) NOT NULL,
                                         password_salt VARCHAR(255),
                                         insert_user   VARCHAR(255),
                                         insert_time   BIGINT NOT NULL,
                                         update_user   VARCHAR(255),
                                         update_time   BIGINT NOT NULL,
                                         PRIMARY KEY (oid),
                                         KEY index_cus_cm_password_history_user (user_oid, insert_time DESC)
);

-- 로그인 이력 (login.allow.save.history / check.save.history)
CREATE TABLE cus_cm_login_history (
                                      oid          VARCHAR(255) NOT NULL,
                                      user_oid     VARCHAR(255),
                                      user_id      VARCHAR(255) NOT NULL,
                                      login_ip     VARCHAR(50),
                                      login_result VARCHAR(20)  NOT NULL,   -- SUCCESS / FAIL_PASSWORD / FAIL_LOCKED ...
                                      user_agent   VARCHAR(500),
                                      insert_user  VARCHAR(255),
                                      insert_time  BIGINT NOT NULL,
                                      update_user  VARCHAR(255),
                                      update_time  BIGINT NOT NULL,
                                      PRIMARY KEY (oid),
                                      KEY index_cus_cm_login_history_user (user_id, insert_time DESC),
                                      KEY index_cus_cm_login_history_time (insert_time)
);

-- 로그인 유지 (login.allow.remember) — Spring Security 표준 스키마
CREATE TABLE persistent_logins (
                                   username  VARCHAR(64)  NOT NULL,
                                   series    VARCHAR(64)  NOT NULL,
                                   token     VARCHAR(64)  NOT NULL,
                                   last_used TIMESTAMP    NOT NULL,
                                   PRIMARY KEY (series)
);