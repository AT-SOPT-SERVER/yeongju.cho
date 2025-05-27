package org.sopt.common.code;

import org.springframework.http.HttpStatus;

public enum GlobalErrorCode implements ErrorCode {
    // 400
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, 40000, "잘못된 요청 값입니다."),
    NOT_FOUND_END_POINT(HttpStatus.NOT_FOUND, 40400, "요청한 API 엔드포인트가 존재하지 않습니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, 40500, "지원하지 않는 HTTP 메서드입니다."),

    // 401
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, 401, "인증되지 않은 사용자입니다."),
    MALFORMED_JWT_TOKEN(HttpStatus.BAD_REQUEST, 401, "잘못된 형식의 토큰입니다."),
    TYPE_ERROR_JWT_TOKEN(HttpStatus.BAD_REQUEST, 401, "올바른 타입의 JWT 토큰이 아닙니다."),
    EXPIRED_JWT_TOKEN(HttpStatus.UNAUTHORIZED, 401, "만료된 토큰입니다."),
    EXPIRED_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, 401, "유효하지 않은 리프레시 토큰입니다."),
    UNSUPPORTED_JWT_TOKEN(HttpStatus.BAD_REQUEST, 401, "지원하지 않는 형식의 토큰입니다."),
    UNKNOWN_JWT_TOKEN(HttpStatus.UNAUTHORIZED, 401, "알 수 없는 인증 토큰 오류가 발생했습니다."),

    // 500
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 50000, "알 수 없는 서버 내부 오류입니다"),
    ;

    private final HttpStatus status;
    private final int code;
    private final String message;

    GlobalErrorCode(HttpStatus status, int code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return status;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}