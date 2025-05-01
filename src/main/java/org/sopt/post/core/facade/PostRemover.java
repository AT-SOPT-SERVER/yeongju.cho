package org.sopt.post.core.facade;

import org.sopt.post.core.domain.PostEntity;
import org.sopt.post.core.repository.PostRepository;
import org.springframework.stereotype.Component;

@Component
public class PostRemover {
    private final PostRepository postRepository;

    public PostRemover(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void deletePost(
            final PostEntity postEntity
    ) {
        postRepository.delete(postEntity);
    }
}
