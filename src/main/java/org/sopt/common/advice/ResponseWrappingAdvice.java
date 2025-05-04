package org.sopt.common.advice;

import lombok.NonNull;
import org.sopt.common.code.ErrorCode;
import org.sopt.common.response.ResponseDto;
import org.sopt.common.code.SuccessCode;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice(
        basePackages = "org.sopt"
)
public class ResponseWrappingAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, @NonNull Class converterType) {
        return !(returnType.getParameterType() == ResponseDto.class)
                && MappingJackson2HttpMessageConverter.class.isAssignableFrom(converterType);
    }

    @Override
    public Object beforeBodyWrite(
            Object body,
            @NonNull MethodParameter returnType,
            @NonNull MediaType selectedContentType,
            @NonNull Class selectedConverterType,
            @NonNull ServerHttpRequest request,
            @NonNull ServerHttpResponse response
    ) {
        if (body instanceof ResponseDto<?>) {
            return body;
        }
        if (body instanceof ErrorCode)
            return ResponseDto.fail((ErrorCode) body);

        // 기본으로는 OK로 감싸고, 다른 것으로 처리하고 싶으면 Controller 에서 직접 감싸서 반환
        return ResponseDto.success(SuccessCode.OK, body);
    }
}