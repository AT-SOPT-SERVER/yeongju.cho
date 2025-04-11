package org.sopt.post.service.logic;

import org.sopt.post.repository.PostRepository;

public class PostRemover {
    private final PostRepository postRepository;

    public PostRemover(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void delete(int id) {
        postRepository.delete(id);
    }
}
