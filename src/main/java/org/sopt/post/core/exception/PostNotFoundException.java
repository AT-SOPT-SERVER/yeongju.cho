package org.sopt.post.core.exception;


import org.sopt.common.code.ErrorCode;
import org.springframework.http.HttpStatus;

public class PostNotFoundException extends PostCoreException {
    public PostNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
