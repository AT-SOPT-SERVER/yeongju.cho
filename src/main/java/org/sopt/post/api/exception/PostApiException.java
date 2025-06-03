package org.sopt.post.api.exception;

import org.sopt.common.code.ErrorCode;

public abstract class PostApiException extends PostBaseException {

    protected PostApiException(ErrorCode errorCode) {
        super(errorCode);
    }

}
