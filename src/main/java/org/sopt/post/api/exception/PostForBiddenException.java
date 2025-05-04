package org.sopt.post.api.exception;

import org.sopt.common.code.ErrorCode;
import org.springframework.http.HttpStatus;

public class PostForBiddenException extends PostApiException{
    public PostForBiddenException(ErrorCode errorCode) {
        super(errorCode);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.FORBIDDEN;
    }
}