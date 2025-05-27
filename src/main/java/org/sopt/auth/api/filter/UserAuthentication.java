package org.sopt.auth.api.filter;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class UserAuthentication extends AbstractAuthenticationToken {

    private final Long userId;

    public UserAuthentication(Long userId) {
        super(null);
        this.userId = userId;
        setAuthenticated(true);
    }

    @Override
    public Object getPrincipal() {
        return userId;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    // userId 만 사용해서 UserAuthentication 객체 생성
    public static UserAuthentication createUserAuthentication(Long userId) {
        return new UserAuthentication(userId);
    }
}