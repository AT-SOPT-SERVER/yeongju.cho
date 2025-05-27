package org.sopt.auth.api.constants;

public class AuthConstant {
    public static final String USER_ID_CLAIM_NAME = "uid";
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String ANONYMOUS_USER = "anonymousUser";
    public static final String CHARACTER_TYPE = "utf-8";
    public static final String CONTENT_TYPE = "application/json";

    private AuthConstant() { // 이 클래스의 인스턴스 생성을 막기 위해 private 생성자
    }

    public static final String[] AUTH_WHITE_LIST = {
            "/auth/users/signup",
            "/auth/users/login",
    };
}
