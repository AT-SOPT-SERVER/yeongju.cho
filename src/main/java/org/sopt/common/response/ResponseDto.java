package org.sopt.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.sopt.common.code.ErrorCode;
import org.sopt.common.code.SuccessCode;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ResponseDto<T>(
        int code,
        T data,
        String message
) {
    public static <T> ResponseDto<T> success(SuccessCode code, final T data) {
        return new ResponseDto<>(code.getCode(), data, null);
    }

    public static <T> ResponseDto<T> fail(ErrorCode code) {
        return new ResponseDto<>(code.getCode(), null, code.getMessage());
    }

    public static <T> ResponseDto<T> fail(int code, final T data) {
        return new ResponseDto<>(code, data, null);
    }
}

