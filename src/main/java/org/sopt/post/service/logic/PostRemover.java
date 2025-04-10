package org.sopt.post.service.logic;

import org.sopt.post.repository.PostBasicRepository;

public class PostRemover {
    private final PostBasicRepository postRepository;

    public PostRemover(PostBasicRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void delete(int id) {
        postRepository.delete(id);
    }
}
