package org.sopt.common.annotation;

import org.sopt.auth.api.constants.AuthConstant;
import org.sopt.auth.api.exception.UnAuthorizedException;
import org.sopt.common.code.GlobalErrorCode;

public class SecurityUtils {

    public static Object checkPrincipal(final Object principal) {
        if (AuthConstant.ANONYMOUS_USER.equals(principal)) {
            throw new UnAuthorizedException(GlobalErrorCode.UNAUTHORIZED);
        }
        return principal;
    }
}