package org.sopt.auth.api.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sopt.auth.api.constants.AuthConstant;
import org.sopt.common.code.GlobalErrorCode;
import org.sopt.common.response.ResponseDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@Component
@RequiredArgsConstructor
public class ErrorResponseWriter {

    private final ObjectMapper objectMapper;

    public void write(HttpServletResponse response, GlobalErrorCode errorCode) {
        try {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(AuthConstant.CHARACTER_TYPE);
            response.setStatus(errorCode.getHttpStatus().value());

            PrintWriter writer = response.getWriter();
            writer.write(objectMapper.writeValueAsString(ResponseDto.fail(errorCode)));
        } catch (IOException e) {
            log.error("Error writing response: {}", e.getMessage(), e);
        }
    }
}