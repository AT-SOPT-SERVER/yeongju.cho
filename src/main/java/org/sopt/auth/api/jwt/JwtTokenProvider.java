package org.sopt.auth.api.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sopt.auth.api.constants.AuthConstant;
import org.sopt.auth.api.dto.JwtTokenDto;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Date;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtTokenProvider implements InitializingBean {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.access-token-expire-time}")
    private long ACCESS_TOKEN_EXPIRE_TIME;

    private Key key;

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public JwtTokenDto issueToken(final long userId) {
        return JwtTokenDto.builder()
                .accessToken(generateToken(userId, ACCESS_TOKEN_EXPIRE_TIME, "ACCESS_TOKEN"))
                .build();
    }

    public String generateToken(final long userId, final long tokenExpirationTime, final String tokenType) {
        final Date now = new Date(System.currentTimeMillis());
        final Date expirationTime = new Date(System.currentTimeMillis() + tokenExpirationTime);

        final Claims claims = Jwts.claims()
                .setIssuedAt(now)
                .setExpiration(expirationTime)
                .setSubject(tokenType);

        claims.put(AuthConstant.USER_ID_CLAIM_NAME, userId);
        claims.put("type", tokenType);

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(claims)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    // 토큰을 파싱하고 JWS 검증, JWT의 클레임 반환
    public Claims getBody(final String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getJwtFromRequest(final HttpServletRequest request) {
        String bearerToken = request.getHeader(AuthConstant.AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(AuthConstant.BEARER_PREFIX)) {
            return bearerToken.substring(AuthConstant.BEARER_PREFIX.length());
        }
        return null;
    }
}
