package org.sopt.auth.api.jwt;

import lombok.extern.slf4j.Slf4j;
import org.sopt.common.code.GlobalErrorCode;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ExceptionLogger {

    public void log(GlobalErrorCode errorCode, Exception e) {
        if (errorCode.getHttpStatus().is4xxClientError()) {
            log.warn("Client error [{}]: {}", errorCode.getCode(), e.getMessage());
        } else if (errorCode.getHttpStatus().is5xxServerError()) {
            log.error("Server error [{}]: {}", errorCode.getCode(), e.getMessage(), e);
        }
    }
}