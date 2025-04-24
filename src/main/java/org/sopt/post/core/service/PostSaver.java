package org.sopt.post.core.service;

import org.sopt.post.core.domain.Post;
import org.sopt.post.core.domain.PostEntity;
import org.sopt.post.core.repository.PostRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PostSaver {
    private final PostRepository postRepository;

    public PostSaver(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public Post save(final String title) {
        final PostEntity postEntity = postRepository.save(new PostEntity(title));
        return Post.fromEntity(postEntity);
    }
}
