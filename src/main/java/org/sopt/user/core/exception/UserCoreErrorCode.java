package org.sopt.user.core.exception;

import org.sopt.common.code.ErrorCode;
import org.springframework.http.HttpStatus;

public enum
UserCoreErrorCode implements ErrorCode {
    // 404
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, 40410, "존재하지 않는 사용자입니다."),
    ;

    public final HttpStatus httpStatus;
    private final int code;
    private final String message;

    UserCoreErrorCode(HttpStatus httpStatus, int code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
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