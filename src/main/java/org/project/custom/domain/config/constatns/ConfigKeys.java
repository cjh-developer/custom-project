package org.project.custom.domain.config.constatns;

/** cus_cm_config 정책 키. DB 행이 원본이고, 이 상수는 오타 방지용이다. */
public final class ConfigKeys {

    private ConfigKeys() {
        throw new AssertionError("ConfigKeys not instance!!");
    }

    /* ============================== 사용자 아이디 */
    public static final String USERID_PREFIX            = "userid.";
    public static final String USERID_MIN_LENGTH        = "userid.min.length";
    public static final String USERID_MAX_LENGTH        = "userid.max.length";
    public static final String USERID_CHECK_PATTERN     = "userid.check.pattern";
    public static final String USERID_CHECK_FIRST_WORD  = "userid.check.first.word";
    public static final String USERID_ALLOW_SPACE       = "userid.allow.space";
    public static final String USERID_ALLOW_KOREAN      = "userid.allow.korean";
    public static final String USERID_ALLOW_CONSECUTIVE = "userid.allow.consecutive";
    public static final String USERID_CHECK_CONSECUTIVE = "userid.check.consecutive";
    public static final String USERID_CHECK_WORD        = "userid.check.word";

    /* ============================== 비밀번호 */
    public static final String PASSWORD_PREFIX              = "password.";
    public static final String PASSWORD_MIN_LENGTH          = "password.min.length";
    public static final String PASSWORD_MAX_LENGTH          = "password.max.length";
    public static final String PASSWORD_CHECK_ALPHA         = "password.check.alpha";
    public static final String PASSWORD_INPUT_ALPHA         = "password.input.alpha";
    public static final String PASSWORD_CHECK_NUMBER        = "password.check.number";
    public static final String PASSWORD_INPUT_NUMBER        = "password.input.number";
    public static final String PASSWORD_ALLOW_SPACE         = "password.allow.space";
    public static final String PASSWORD_ALLOW_SPECIAL       = "password.allow.special";
    public static final String PASSWORD_ALLOW_CONSECUTIVE   = "password.allow.consecutive";
    public static final String PASSWORD_INPUT_CONSECUTIVE   = "password.input.consecutive";
    public static final String PASSWORD_ALLOW_REPEATED      = "password.allow.repeated";
    public static final String PASSWORD_INPUT_REPEATED      = "password.input.repeated";
    public static final String PASSWORD_ALLOW_USERID        = "password.allow.userid";
    public static final String PASSWORD_ALLOW_USER_NAME     = "password.allow.user.name";
    public static final String PASSWORD_ALLOW_EMAIL         = "password.allow.email";
    public static final String PASSWORD_ALLOW_HISTORY       = "password.allow.history";
    public static final String PASSWORD_CHECK_HISTORY_COUNT = "password.check.history.count";
    public static final String PASSWORD_CHECK_EXPIRED       = "password.check.expired";
    public static final String PASSWORD_CHECK_INIT          = "password.check.init";
    public static final String PASSWORD_CHECK_INIT_EXPIRED  = "password.check.init.expired";
    public static final String PASSWORD_ALLOW_SALT          = "password.allow.salt";
    public static final String PASSWORD_CHECK_SALT_LENGTH   = "password.check.salt.length";

    /* ============================== 로그인 */
    public static final String LOGIN_PREFIX                  = "login.";
    public static final String LOGIN_ALLOW_LIMIT_FAIL_COUNT  = "login.allow.limit.fail.count";
    public static final String LOGIN_CHECK_LIMIT_FAIL_COUNT  = "login.check.limit.fail.count";
    public static final String LOGIN_ALLOW_LOCKED            = "login.allow.locked";
    public static final String LOGIN_CHECK_LOCKED_TIME       = "login.check.locked.time";
    public static final String LOGIN_ALLOW_DUPLICATED        = "login.allow.duplicated";
    public static final String LOGIN_CHECK_DUPLICATED_METHOD = "login.check.duplicated.method";
    public static final String LOGIN_CHECK_DUPLICATED_COUNT  = "login.check.duplicated.count";
    public static final String LOGIN_ALLOW_AUTO_LOGOUT       = "login.allow.auto.logout";
    public static final String LOGIN_CHECK_AUTO_LOGOUT       = "login.check.auto.logout";
    public static final String LOGIN_ALLOW_REMEMBER          = "login.allow.remember";
    public static final String LOGIN_ALLOW_SAVE_HISTORY      = "login.allow.save.history";
    public static final String LOGIN_CHECK_SAVE_HISTORY      = "login.check.save.history";

    /* ============================== 로그아웃 */
    public static final String LOGOUT_PREFIX                   = "logout.";
    public static final String LOGOUT_ALLOW_SESSION_INVALIDATE = "logout.allow.session.invalidate";
    public static final String LOGOUT_ALLOW_COOKIE_INVALIDATE  = "logout.allow.cookie.invalidate";
    public static final String LOGOUT_CHECK_REDIRECT_URI       = "logout.check.redirect.uri";

    /* ============================== 캐시 */
    public static final String CONFIG_PREFIX           = "config.";
    public static final String CONFIG_ALLOW_USER_CACHE = "config.allow.user.cache";

    /* ============================== 사용자 */
    public static final String USER_PREFIX                     = "user.";
    public static final String USER_ALLOW_CHECK_SLEEPER        = "user.allow.check.sleeper";
    public static final String USER_CHECK_SLEEPER_TIME         = "user.check.sleeper.time";
    public static final String USER_CHECK_INIT_STATUS          = "user.check.init.status";
    public static final String USER_CHECK_DELETE_USER_INFO     = "user.check.delete.user.info";
    public static final String USER_CHECK_ENC_DELETE_USER_INFO = "user.check.enc.delete.user.info";
    public static final String USER_CHECK_OUT_TIME             = "user.check.out.time";
}