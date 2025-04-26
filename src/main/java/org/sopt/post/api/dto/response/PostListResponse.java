package org.sopt.post.api.dto.response;

import org.sopt.post.core.domain.Post;

import java.util.List;

public record PostListResponse(
        List<PostDto> postsLists
) {
    public record PostDto(
            int id,
            String title
    ) {
        public static PostDto from(Post post) {
            return new PostDto(
                    post.getId(), post.getTitle()
            );
        }
    }
}
