package org.sopt.user.core.exception;

import org.sopt.common.code.ErrorCode;
import org.sopt.user.api.exception.UserBaseException;

public abstract class UserCoreException extends UserBaseException {

    protected UserCoreException(ErrorCode errorCode) {
        super(errorCode);
    }

}