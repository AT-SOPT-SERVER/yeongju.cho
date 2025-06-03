package org.sopt.post.core.exception;

import lombok.RequiredArgsConstructor;
import org.sopt.common.code.ErrorCode;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum PostCoreErrorCode implements ErrorCode {
    // 404
    NOT_FOUND_POST(HttpStatus.NOT_FOUND, 40401, "게시글이 존재하지 않습니다."),

    // 409
    DUPLICATED_POST(HttpStatus.CONFLICT, 40901, "이미 존재하는 게시글 제목입니다.")
    ;

    public final HttpStatus httpStatus;
    private final int code;
    private final String message;

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