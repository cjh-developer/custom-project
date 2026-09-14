INSERT INTO cus_cm_config (oid, config_group, config_value, default_value, value_type, use_yn, sort_order, description, insert_time, update_time) VALUES
  ('userid.min.length',       'USERID',   '5',  '5',  'NUMBER',  'Y', 10, '아이디 최소 길이',        0, 0),
  ('userid.max.length',       'USERID',   '20', '20', 'NUMBER',  'Y', 20, '아이디 최대 길이',        0, 0),
  ('userid.pattern',          'USERID',   NULL, '^[a-zA-Z0-9_]+$', 'STRING', 'Y', 30, '아이디 허용 패턴', 0, 0),
  ('login.fail.limit',        'LOGIN',    '5',  '5',  'NUMBER',  'Y', 10, '로그인 실패 잠금 횟수',   0, 0),
  ('login.lock.minute',       'LOGIN',    '30', '30', 'NUMBER',  'Y', 20, '잠금 유지 시간(분)',      0, 0),
  ('login.duplicate.allow',   'LOGIN',    'N',  'N',  'BOOLEAN', 'Y', 30, '중복 로그인 허용 여부',   0, 0),
  ('password.min.length',     'PASSWORD', '9',  '9',  'NUMBER',  'Y', 10, '비밀번호 최소 길이',      0, 0),
  ('password.change.cycle.day','PASSWORD','90', '90', 'NUMBER',  'Y', 20, '비밀번호 변경 주기(일)',  0, 0),
  ('password.salt.use',       'PASSWORD', 'N',  'N',  'BOOLEAN', 'Y', 30, 'salt 사용 여부',          0, 0),
  ('password.salt.value',     'PASSWORD', NULL, NULL, 'STRING',  'Y', 40, 'salt 문자열',             0, 0);