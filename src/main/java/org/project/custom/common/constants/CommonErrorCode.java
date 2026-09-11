package org.project.custom.common.constants;

public enum CommonErrorCode implements ErrorCode {
    COMMON_ERROR("error.common"),
    SERVER_ERROR("error.server"),
    DATA_NOT_FOUND("error.data.not.found"),
    TIME_EXPIRED("error.time.expired"),
    NOT_AUTHORITY("error.not.authority")
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
