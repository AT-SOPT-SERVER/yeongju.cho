package org.sopt.post.core.exception;

import org.sopt.common.code.ErrorCode;
import org.sopt.post.api.exception.PostBaseException;
import org.springframework.http.HttpStatus;

public abstract class PostCoreException extends PostBaseException {

    protected PostCoreException(ErrorCode errorCode) {
        super(errorCode);
    }

}
