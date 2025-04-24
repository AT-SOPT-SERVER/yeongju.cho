package org.sopt.post.api.dto.response;

import org.sopt.post.core.domain.Post;

public record PostDetailsResponse(
        int id,
        String title
) {
    public static PostDetailsResponse from(Post post) {
        return new PostDetailsResponse(
                post.getId(), post.getTitle()
        );
    }
}
