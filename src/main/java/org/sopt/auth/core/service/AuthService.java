package org.sopt.auth.core.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sopt.auth.api.dto.JwtTokenDto;
import org.sopt.auth.api.jwt.JwtTokenProvider;
import org.sopt.user.core.domain.User;
import org.sopt.user.core.facade.UserRetriever;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRetriever userRetriever;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public JwtTokenDto login(final long userId) {
        User user = userRetriever.findById(userId);
        return jwtTokenProvider.issueToken(user.getId());
    }
}
