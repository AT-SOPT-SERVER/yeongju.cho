package org.sopt.post.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostUpdateDto(
        @NotBlank(message = "제목은 필수로 입력되어야 합니다.")
        @Size(max = 30, message = "제목은 30자 이하여야 합니다.")
        String newTitle,
        @Size(max = 1000, message = "게시글의 내용은 1000자 이하여야 합니다.")
        String content
) {
}
