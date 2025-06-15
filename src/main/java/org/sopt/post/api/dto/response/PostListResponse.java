package org.sopt.post.api.dto.response;

import org.sopt.post.core.domain.Post;

import java.util.List;
import java.util.Map;

public record PostListResponse(
        List<PostDto> postsLists
) {
    public record PostDto(
            long postId,
            String title,
            String nickname
    ) {
        public static PostDto from(Post post, String nickname) {
            return new PostDto(
                    post.getId(),
                    post.getTitle(),
                    nickname
            );
        }
    }
    public static PostListResponse from(
            List<Post> posts,
            Map<Long, String> nicknameMap
    ) {
        final List<PostDto> postDtoList = posts.stream()
                .map(post -> {
                    String nickname = nicknameMap.getOrDefault(post.getUserId(), null);
                    return PostDto.from(post, nickname);
                }).toList();

        return new PostListResponse(postDtoList);
    }
}
