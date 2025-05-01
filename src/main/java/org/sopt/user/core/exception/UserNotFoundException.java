package org.sopt.user.core.exception;

import org.sopt.common.code.ErrorCode;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends UserCoreException{
    public UserNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}