package org.project.custom.common.exception;

import org.project.custom.common.constants.ErrorCode;

public interface ICustomException {

    ErrorCode getErrorCode();
    String getMessage();
}
