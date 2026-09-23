package org.project.custom.common.constants;

public enum CommonErrorCode implements ErrorCode {
    COMMON_ERROR("error.common"),
    SERVER_ERROR("error.server"),
    DATA_NOT_FOUND("error.data.not.found"),
    TIME_EXPIRED("error.time.expired"),
    NOT_AUTHORITY("error.not.authority"),

    USER_ID_VALIDATE_ERROR("error.user.id.validate"),
    USER_ID_VALIDATE_WHITE_SPACE("error.user.id.validate.whitespace"),
    USER_ID_VALIDATE_KOREAN("error.user.id.validate.korean"),
    USER_ID_VALIDATE_PATTERN("error.user.id.validate.pattern"),
    USER_ID_VALIDATE_FIRST_WORD("error.user.id.validate.first.word"),
    USER_ID_VALIDATE_DENY("error.user.id.validate.deny"),
    USER_ID_IS_EMPTY("error.user.id.empty"),
    ;

    private String msgCode;

    CommonErrorCode(String msgCode) {
        this.msgCode = msgCode;
    }

    @Override
    public String getCode() {
        return this.name();
    }

    @Override
    public String getMsgCode() {
        return msgCode;
    }

    @Override
    public boolean isEqual(String code) {
        return this.getCode().equals(code);
    }

    @Override
    public boolean isEqual(ErrorCode errorCode) {
        return this.isEqual(errorCode.getCode());
    }
}
