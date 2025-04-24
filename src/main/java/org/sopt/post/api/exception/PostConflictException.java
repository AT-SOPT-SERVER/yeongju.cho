package org.sopt.post.api.exception;

import org.sopt.common.code.ErrorCode;
import org.springframework.http.HttpStatus;

public class PostConflictException extends PostApiException {
    public PostConflictException(ErrorCode errorCode) {
        super(errorCode);
    }

    @Override
    public HttpStatus getStatus(){
        return HttpStatus.CONFLICT;
    }
}
