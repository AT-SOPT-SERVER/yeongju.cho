package org.sopt.post.api.dto.response;

import org.sopt.post.core.domain.Post;
import org.sopt.user.core.domain.User;

public record PostDetailsResponse(
        long id,
        String title,
        String content,
        String nickname
) {
    public static PostDetailsResponse from(Post post, User user) {
        return new PostDetailsResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                user.getNickname()
        );
    }
}
