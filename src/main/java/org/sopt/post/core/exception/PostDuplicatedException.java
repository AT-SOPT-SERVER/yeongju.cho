package org.sopt.post.core.exception;

import org.sopt.common.code.ErrorCode;
import org.springframework.http.HttpStatus;

public class PostDuplicatedException extends PostCoreException {
    public PostDuplicatedException(ErrorCode errorCode) {
        super(errorCode);
    }

    @Override
    public HttpStatus getStatus() {
        return null;
    }
}
