package org.sopt.auth.api.dto;

import lombok.Builder;

@Builder
public record JwtTokenDto(
        String accessToken
) {
}
