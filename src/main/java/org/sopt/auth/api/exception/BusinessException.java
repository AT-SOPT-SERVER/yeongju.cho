package org.sopt.auth.api.exception;

import org.sopt.common.code.GlobalErrorCode;

public abstract class BusinessException extends AuthBaseException {
    protected BusinessException(GlobalErrorCode errorCode) {
        super(errorCode);
    }

    @Override
    public GlobalErrorCode getErrorCode() {
        return (GlobalErrorCode) super.getErrorCode();
    }
}
