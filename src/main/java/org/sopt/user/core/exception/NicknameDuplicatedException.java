package org.sopt.user.core.exception;

import org.sopt.common.code.ErrorCode;
import org.springframework.http.HttpStatus;

public class NicknameDuplicatedException extends UserCoreException{
    public NicknameDuplicatedException(ErrorCode errorCode) {
        super(errorCode);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}