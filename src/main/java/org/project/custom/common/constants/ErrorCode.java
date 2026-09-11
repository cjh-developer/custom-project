package org.project.custom.common.constants;

import java.io.Serializable;

public interface ErrorCode extends Serializable {

    String getCode();

    String getMsgCode();

    boolean isEqual(String code);

    boolean isEqual(ErrorCode errorCode);
}
