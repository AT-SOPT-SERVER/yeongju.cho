package org.sopt.auth.api.exception;

import org.sopt.common.code.GlobalErrorCode;
import org.springframework.http.HttpStatus;

public class UnAuthorizedException extends AuthBaseException {
    public UnAuthorizedException(GlobalErrorCode errorCode) {
        super(errorCode);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}