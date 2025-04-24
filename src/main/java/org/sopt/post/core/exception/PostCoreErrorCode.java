package org.sopt.post.core.exception;

import org.sopt.common.code.ErrorCode;
import org.springframework.http.HttpStatus;

public enum PostCoreErrorCode implements ErrorCode {
    // 404
    NOT_FOUND_POST(HttpStatus.NOT_FOUND, 40401, "게시글이 존재하지 않습니다."),
    ;

    public final HttpStatus httpStatus;
    private final int code;
    private final String message;

    PostCoreErrorCode(HttpStatus httpStatus, int code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message =  message;
    }

    @Override
    public HttpStatus getHttpStatus(){
        return httpStatus;
    }

    @Override
    public int getCode(){
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}