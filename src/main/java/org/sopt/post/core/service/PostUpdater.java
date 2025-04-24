package org.sopt.post.core.service;

import org.sopt.post.api.dto.request.PostUpdateDto;
import org.sopt.post.core.domain.PostEntity;
import org.sopt.post.core.repository.PostRepository;
import org.springframework.stereotype.Component;

@Component
public class PostUpdater {
    private final PostRepository postRepository;

    public PostUpdater(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void updatePost(
            final PostEntity postEntity,
            final PostUpdateDto postUpdateDto
    ) {
        String newTitle = postUpdateDto.newTitle() != null ? postUpdateDto.newTitle() : postEntity.getTitle();
        postEntity.updateTitle(newTitle);
    }
}