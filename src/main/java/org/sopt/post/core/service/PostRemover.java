package org.sopt.post.core.service;

import org.sopt.post.core.repository.PostRepository;

public class PostRemover {
    private final PostRepository postRepository;

    public PostRemover(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void delete(final int id) {
        postRepository.delete(id);
    }
}
