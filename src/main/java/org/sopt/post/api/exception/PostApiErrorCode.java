package org.sopt.post.api.exception;


import org.sopt.common.code.ErrorCode;
import org.springframework.http.HttpStatus;

public enum PostApiErrorCode implements ErrorCode {

    // 403
    FORBIDDEN_POST(HttpStatus.FORBIDDEN, 40301, "해당 게시글에 접근할 수 있는 권한이 없습니다."),

    // 409
    CONFLICT_DUPLICATE_TITLE(HttpStatus.CONFLICT,40901, "중복되는 제목입니다."),
    CONFLICT_CREATE_LIMIT(HttpStatus.CONFLICT,40902, "작성 시간이 제한됩니다."),
    ;

    public final HttpStatus httpStatus;
    private final int code;
    private final String message;

    PostApiErrorCode(HttpStatus httpStatus, int code, String message) {
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