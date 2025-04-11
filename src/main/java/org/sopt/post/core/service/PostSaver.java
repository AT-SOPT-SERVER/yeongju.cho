package org.sopt.post.core.service;

import org.sopt.post.core.domain.Post;
import org.sopt.post.core.repository.PostRepository;

public class PostSaver {
    private final PostRepository postRepository;

    public PostSaver(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void save(final Post post) {
        postRepository.save(post);
    }
}
