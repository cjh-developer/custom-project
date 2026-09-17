-- ================================================== 사용자 아이디 정책 (9)
INSERT INTO cus_cm_config
(oid, config_group, config_value, default_value, value_type, use_yn, sort_order, description, insert_time, update_time) VALUES
('userid.min.length',        'USERID', NULL, '5',                'NUMBER',  'Y',  10, '사용자 아이디 최소 길이',                                0, 0),
('userid.max.length',        'USERID', NULL, '30',               'NUMBER',  'Y',  20, '사용자 아이디 최대 길이',                                0, 0),
('userid.check.pattern',     'USERID', NULL, '^[a-zA-Z0-9_-]+$', 'STRING',  'Y',  30, '사용자 아이디 검사 정규식',                              0, 0),
('userid.check.first.word',  'USERID', NULL, 'ANY',              'STRING',  'Y',  40, '아이디 시작 문자(UPPER/LOWER/ALPHA/NUMBER/SPECIAL/ANY)',  0, 0),
('userid.allow.space',       'USERID', NULL, 'N',                'BOOLEAN', 'Y',  50, '아이디 공백 문자 허용 여부',                             0, 0),
('userid.allow.korean',      'USERID', NULL, 'N',                'BOOLEAN', 'Y',  60, '아이디 한글 허용 여부',                                  0, 0),
('userid.allow.consecutive', 'USERID', NULL, 'N',                'BOOLEAN', 'Y',  70, '아이디 연속 문자열 허용 여부',                           0, 0),
('userid.check.consecutive', 'USERID', NULL, '3',                'NUMBER',  'Y',  80, '아이디 최대 사용 가능 연속 문자열 길이',                 0, 0),
('userid.check.word',        'USERID', NULL, 'admin,administrator,root,system,test,guest', 'STRING', 'Y', 90, '사용 불가 아이디 목록(콤마 구분)', 0, 0);

-- ================================================== 비밀번호 정책 (22)
INSERT INTO cus_cm_config
(oid, config_group, config_value, default_value, value_type, use_yn, sort_order, description, insert_time, update_time) VALUES
('password.min.length',          'PASSWORD', NULL, '9',  'NUMBER',  'Y',  10, '비밀번호 최소 입력 길이',                 0, 0),
('password.max.length',          'PASSWORD', NULL, '20', 'NUMBER',  'Y',  20, '비밀번호 최대 입력 길이',                 0, 0),
('password.check.alpha',         'PASSWORD', NULL, 'Y',  'BOOLEAN', 'Y',  30, '영문자 포함 필수 여부',                   0, 0),
('password.input.alpha',         'PASSWORD', NULL, '1',  'NUMBER',  'Y',  40, '최소 입력 영문자 개수',                   0, 0),
('password.check.number',        'PASSWORD', NULL, 'Y',  'BOOLEAN', 'Y',  50, '숫자 포함 필수 여부',                     0, 0),
('password.input.number',        'PASSWORD', NULL, '1',  'NUMBER',  'Y',  60, '최소 입력 숫자 개수',                     0, 0),
('password.allow.space',         'PASSWORD', NULL, 'N',  'BOOLEAN', 'Y',  70, '공백 문자 사용 가능 여부',                0, 0),
('password.allow.special',       'PASSWORD', NULL, 'Y',  'BOOLEAN', 'Y',  80, '특수문자 사용 가능 여부(필수 아님)',      0, 0),
('password.allow.consecutive',   'PASSWORD', NULL, 'N',  'BOOLEAN', 'Y',  90, '연속 문자 사용 허용 여부',                0, 0),
('password.input.consecutive',   'PASSWORD', NULL, '3',  'NUMBER',  'Y', 100, '허용 연속 문자열 개수',                   0, 0),
('password.allow.repeated',      'PASSWORD', NULL, 'N',  'BOOLEAN', 'Y', 110, '동일 문자 반복 허용 여부',                0, 0),
('password.input.repeated',      'PASSWORD', NULL, '3',  'NUMBER',  'Y', 120, '허용 동일 문자 개수',                     0, 0),
('password.allow.userid',        'PASSWORD', NULL, 'N',  'BOOLEAN', 'Y', 130, '사용자 아이디 포함 허용 여부',            0, 0),
('password.allow.user.name',     'PASSWORD', NULL, 'N',  'BOOLEAN', 'Y', 140, '사용자 이름 포함 허용 여부',              0, 0),
('password.allow.email',         'PASSWORD', NULL, 'N',  'BOOLEAN', 'Y', 150, '사용자 이메일 포함 허용 여부',            0, 0),
('password.allow.history',       'PASSWORD', NULL, 'N',  'BOOLEAN', 'Y', 160, '이전 비밀번호 재사용 허용 여부',          0, 0),
('password.check.history.count', 'PASSWORD', NULL, '3',  'NUMBER',  'Y', 170, '이전 비밀번호 제한 개수',                 0, 0),
('password.check.expired',       'PASSWORD', NULL, '180', 'NUMBER',  'Y', 180, '비밀번호 만료 일수(0=만료 없음)',         0, 0),
('password.check.init',          'PASSWORD', NULL, 'Y',  'BOOLEAN', 'Y', 190, '최초 로그인 시 비밀번호 변경 강제 여부',  0, 0),
('password.check.init.expired',  'PASSWORD', NULL, '7',  'NUMBER',  'Y', 200, '초기 비밀번호 유효 기간(일)',             0, 0),
('password.allow.salt',          'PASSWORD', NULL, 'N',  'BOOLEAN', 'Y', 210, 'salt 사용 여부',                          0, 0),
('password.check.salt.length',   'PASSWORD', NULL, '16', 'NUMBER',  'Y', 220, 'salt 바이트 길이(16~32)',                 0, 0);

-- ================================================== 로그인 정책 (12)
INSERT INTO cus_cm_config
(oid, config_group, config_value, default_value, value_type, use_yn, sort_order, description, insert_time, update_time) VALUES
('login.allow.limit.fail.count',   'LOGIN', NULL, 'Y',   'BOOLEAN', 'Y',  10, '로그인 실패 횟수 제한 사용 여부',                0, 0),
('login.check.limit.fail.count',   'LOGIN', NULL, '5',   'NUMBER',  'Y',  20, '로그인 실패 최대 허용 횟수',                     0, 0),
('login.allow.locked',             'LOGIN', NULL, 'Y',   'BOOLEAN', 'Y',  30, '실패 초과 시 계정 잠금 사용 여부',               0, 0),
('login.check.locked.time',        'LOGIN', NULL, '5',  'NUMBER',  'Y',  40, '잠긴 계정 자동 해제 시간(분, 0=수동)',           0, 0),
('login.allow.duplicated',         'LOGIN', NULL, 'N',   'BOOLEAN', 'Y',  50, '중복 로그인 허용 여부',                          0, 0),
('login.check.duplicated.method',  'LOGIN', NULL, 'BEFORE', 'STRING',  'Y',  60, '중복 로그인 처리(BEFORE=기존종료/AFTER=신규차단)',   0, 0),
('login.check.duplicated.count',   'LOGIN', NULL, '1',   'NUMBER',  'Y',  70, '동시 로그인 최대 개수',                          0, 0),
('login.allow.auto.logout',        'LOGIN', NULL, 'N',   'BOOLEAN', 'Y',  80, '미사용 시 자동 로그아웃 사용 여부',              0, 0),
('login.check.auto.logout',        'LOGIN', NULL, '120', 'NUMBER',  'Y',  90, '자동 로그아웃까지의 미사용 시간(분)',            0, 0),
('login.allow.remember',           'LOGIN', NULL, 'N',   'BOOLEAN', 'Y', 100, '로그인 유지 사용 여부',                          0, 0),
('login.allow.save.history',       'LOGIN', NULL, 'Y',   'BOOLEAN', 'Y', 110, '로그인 이력 저장 여부',                          0, 0),
('login.check.save.history',       'LOGIN', NULL, '12',  'NUMBER',  'Y', 120, '로그인 이력 유지 기간(달)',                      0, 0);

-- ================================================== 로그아웃 정책 (3)
INSERT INTO cus_cm_config
(oid, config_group, config_value, default_value, value_type, use_yn, sort_order, description, insert_time, update_time) VALUES
('logout.allow.session.invalidate', 'LOGOUT', NULL, 'Y',      'BOOLEAN', 'Y', 10, '로그아웃 시 세션 삭제 여부', 0, 0),
('logout.allow.cookie.invalidate',  'LOGOUT', NULL, 'Y',      'BOOLEAN', 'Y', 20, '로그아웃 시 쿠키 삭제 여부', 0, 0),
('logout.check.redirect.uri',       'LOGOUT', NULL, '/login', 'STRING',  'Y', 30, '로그아웃 후 이동 주소',      0, 0);

-- ================================================== 캐시 정책 (1)
INSERT INTO cus_cm_config
(oid, config_group, config_value, default_value, value_type, use_yn, sort_order, description, insert_time, update_time) VALUES
('config.allow.user.cache', 'CONFIG', NULL, 'Y', 'BOOLEAN', 'Y', 10, '사용자 캐시 사용 여부', 0, 0);

-- ================================================== 사용자 정책 (6)
INSERT INTO cus_cm_config
(oid, config_group, config_value, default_value, value_type, use_yn, sort_order, description, insert_time, update_time) VALUES
('user.allow.check.sleeper',        'USER', NULL, 'Y',      'BOOLEAN', 'Y', 10, '휴면 계정 검사 사용 여부',          0, 0),
('user.check.sleeper.time',         'USER', NULL, '12',     'NUMBER',  'Y', 20, '휴면 전환 기준 미사용 기간(달)',    0, 0),
('user.check.init.status',          'USER', NULL, 'ACTIVE', 'STRING',  'Y', 30, '사용자 등록 초기 상태(UserStatus)', 0, 0),
('user.check.delete.user.info',     'USER', NULL, 'N',      'BOOLEAN', 'Y', 40, '사용자 삭제 시 정보 삭제 여부',     0, 0),
('user.check.enc.delete.user.info', 'USER', NULL, 'Y',      'BOOLEAN', 'Y', 50, '삭제 사용자 정보 암호화 여부',      0, 0),
('user.check.out.time',             'USER', NULL, '12',      'NUMBER',  'Y', 60, '탈퇴 사용자 유지 기간(달)',         0, 0);
