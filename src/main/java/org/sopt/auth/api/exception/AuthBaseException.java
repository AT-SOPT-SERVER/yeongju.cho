package org.sopt.auth.api.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.sopt.common.code.ErrorCode;
import org.sopt.common.exception.MooserverBaseException;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AuthBaseException extends MooserverBaseException {

    private final ErrorCode errorCode;
    public abstract HttpStatus getStatus();

}