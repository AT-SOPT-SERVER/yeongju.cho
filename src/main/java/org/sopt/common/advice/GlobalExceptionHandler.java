package org.sopt.common.advice;

import org.sopt.auth.api.exception.AuthBaseException;
import org.sopt.common.code.ErrorCode;
import org.sopt.common.code.GlobalErrorCode;
import org.sopt.common.response.ResponseDto;
import org.sopt.post.api.exception.PostBaseException;
import org.sopt.user.api.exception.UserBaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthBaseException.class)
    public ResponseEntity<ResponseDto<Void>> handleAuthBaseException(AuthBaseException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(ResponseDto.fail(e.getErrorCode()));
    }

    @ExceptionHandler(UserBaseException.class)
    public ResponseEntity<ResponseDto<Void>> handleUserCoreException(UserBaseException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(ResponseDto.fail(e.getErrorCode()));
    }

    @ExceptionHandler(PostBaseException.class)
    public ResponseEntity<ResponseDto<Void>> handlePostBaseException(PostBaseException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(ResponseDto.fail(e.getErrorCode()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto<Map<String, String>>> handleValidationException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(err ->
                errors.put(err.getField(), err.getDefaultMessage())
        );

        return ResponseEntity
                .status(GlobalErrorCode.INVALID_INPUT_VALUE.getHttpStatus())
                .body(ResponseDto.fail(GlobalErrorCode.INVALID_INPUT_VALUE.getCode(), errors));
    }

    // 존재하지 않는 요청에 대한 예외
    @ExceptionHandler(value = {NoHandlerFoundException.class, HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<ResponseDto<Void>> handleNoPageFoundException(Exception e) {
        ErrorCode errorCode = e instanceof HttpRequestMethodNotSupportedException
                ? GlobalErrorCode.METHOD_NOT_ALLOWED
                : GlobalErrorCode.NOT_FOUND_END_POINT;

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ResponseDto.fail(errorCode));
    }

    // 기본 예외
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto<Void>> handleException(Exception e){
        return ResponseEntity
                .status(GlobalErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus())
                .body(ResponseDto.fail(GlobalErrorCode.INTERNAL_SERVER_ERROR));
    }
}