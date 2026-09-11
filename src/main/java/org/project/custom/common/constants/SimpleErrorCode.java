package org.project.custom.common.constants;

import org.project.custom.common.util.StringCheck;

import java.util.Objects;

public final class SimpleErrorCode implements ErrorCode {

    private static final long serialVersionUID = 1L;

    private final String code;
    private final String msgCode;

    private SimpleErrorCode(String code, String msgCode) {
        this.code = Objects.requireNonNull(code, "code is null");
        this.msgCode = StringCheck.isEmpty(msgCode) ? CommonErrorCode.COMMON_ERROR.getMsgCode() : msgCode;
    }

    public static SimpleErrorCode create(String code){
        return new SimpleErrorCode(code, null);
    }

    public static SimpleErrorCode create(String code, String msgCode){
        return new SimpleErrorCode(code, msgCode);
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMsgCode() {
        return this.msgCode;
    }

    @Override
    public boolean isEqual(String code) {
        return this.code.equals(code);
    }

    @Override
    public boolean isEqual(ErrorCode errorCode) {
        return this.code.equals(errorCode.getCode());
    }

    @Override
    public String toString() {
        return this.code;
    }
}
