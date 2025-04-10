package org.sopt.post.service.logic;

import org.sopt.post.domain.Post;
import org.sopt.post.repository.PostBasicRepository;

public class PostSaver {
    private final PostBasicRepository postRepository;

    public PostSaver(PostBasicRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void save(Post post) {
        postRepository.save(post);
    }
}
