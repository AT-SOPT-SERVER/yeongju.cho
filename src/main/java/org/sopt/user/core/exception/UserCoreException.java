package org.sopt.user.core.exception;

import org.sopt.common.code.ErrorCode;
import org.springframework.http.HttpStatus;

public abstract class UserCoreException extends RuntimeException{

    private final ErrorCode errorCode;

    protected UserCoreException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode(){
        return errorCode;
    }

    public abstract HttpStatus getStatus();
}