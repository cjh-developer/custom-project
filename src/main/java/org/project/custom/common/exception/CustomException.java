package org.project.custom.common.exception;

import org.project.custom.common.constants.CommonErrorCode;
import org.project.custom.common.constants.ErrorCode;
import org.project.custom.common.constants.SimpleErrorCode;

import java.util.Map;

public class CustomException extends RuntimeException implements ICustomException {

    private static final long serialVersionUID = 1L;

    /** 에러코드 */
    protected ErrorCode errorCode = null;

    /** 사용자 정의 메시지 */
    protected String msg = null;

    /** 에러 메시지 추가 정보  */
    protected String[] msgArgs = null;

    /** 검사 필드 ID */
    protected String nameMsgId;

    /** 에러 추가 정보 */
    protected transient Map<String, Object> dataMap = null;

    public CustomException(Throwable cause) {
        super(cause);

        if(cause instanceof CustomException){
            CustomException e = (CustomException) cause;
            this.errorCode = e.getErrorCode();
            this.msg = e.msg;
            this.msgArgs = e.msgArgs;
            this.nameMsgId = e.nameMsgId;
            this.dataMap = e.dataMap;
        }else{
            this.errorCode = CommonErrorCode.COMMON_ERROR;
        }
    }

    public CustomException(CustomException e) {
        this(e.errorCode, e.msg, e.msgArgs, e.dataMap, e.getCause());
        this.nameMsgId = e.nameMsgId;
    }

    public CustomException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public CustomException(ErrorCode errorCode, Throwable cause ) {
        this(errorCode, (String)null, cause);
    }

    public CustomException(ErrorCode errorCode, String message) {
        this(errorCode, message, null, (Map<String, Object>)null);
    }

    public CustomException(ErrorCode errorCode, String[] msgArgs) {
        this(errorCode, (String)null, msgArgs, null);
    }

    public CustomException(ErrorCode errorCode, String[] msgArgs, Throwable cause) {
        this(errorCode, (String)null, msgArgs, null, cause);
    }

    public CustomException(ErrorCode errorCode, String message, Map<String, Object> dataMap) {
        this(errorCode, message, null, dataMap);
    }

    public CustomException(ErrorCode errorCode, String message, Throwable cause) {
        this(errorCode, message, null, null, cause);
    }

    public CustomException(ErrorCode errorCode, String message, String[] msgArgs, Map<String, Object> dataMap) {
        this.errorCode = errorCode;
        this.msg = message;
        this.msgArgs = msgArgs;
        this.dataMap = dataMap;

    }

    public CustomException(ErrorCode errorCode, String message, Map<String, Object> dataMap, Throwable cause) {
        this(errorCode, message, null, dataMap, cause);
    }

    public CustomException(ErrorCode errorCode, String message, String[] msgArgs, Map<String, Object> dataMap, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.msg = message;
        this.msgArgs = msgArgs;
        this.dataMap = dataMap;
    }

    /** 문자열 ErrorCode 생성자 */
    public CustomException(String code) {
        this(code, (String)null);
    }

    public CustomException(String code, Throwable cause ) {
        this(code, (String)null, cause);
    }

    public CustomException(String code, String message) {
        this(code, message, (Map<String, Object>)null);
    }

    public CustomException(String code, String message, Map<String, Object> dataMap) {
        this(code, message, dataMap, null);
    }

    public CustomException(String errorCode, String message, Throwable cause) {
        this(errorCode, message, null, cause);
    }

    public CustomException(String code, String message, Map<String, Object> dataMap, Throwable cause) {
        super(cause);
        ErrorCode resolved;
        try {
            resolved = CommonErrorCode.valueOf(code);
        }catch (Exception e) {
            // 문자열 ErrorCode 처리
            resolved = SimpleErrorCode.create(code);
        }

        this.errorCode = resolved;
        this.msg = message;
        this.dataMap = dataMap;
    }


    @Override
    public ErrorCode getErrorCode() {
        return this.errorCode;
    }

    public String getCode() {

        if( errorCode != null) {
            return errorCode.getCode();
        }

        return null;
    }

    @Override
    public String getMessage() {
        if( this.msg != null && this.msg.trim().length() > 0) {
            return this.nameMsgId != null ? "(" + this.nameMsgId + ")" + this.msg : this.msg;
        }

        String sMsg = super.getMessage();

        if( this.getCause() instanceof CustomException) {
            // msg 두번 출력 방지
            sMsg = null;
        }

        if( sMsg !=  null) {
            return this.nameMsgId != null ? "(" + this.nameMsgId + ")" + sMsg : sMsg;
        }

        String errCode = this.getCode();

        if( errCode != null  ) {
            return this.nameMsgId != null ? "(" + this.nameMsgId + ")" + errCode : errCode;
        }

        return null;
    }

    protected void setNameMsgId(String nameMsgId) {
        this.nameMsgId = nameMsgId;
    }

    public String getNameMsgId() {
        return this.nameMsgId;
    }
}
