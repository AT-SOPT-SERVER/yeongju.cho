package org.sopt.post.service.logic;

import org.sopt.post.domain.Post;
import org.sopt.post.repository.PostRepository;

public class PostSaver {
    private final PostRepository postRepository;

    public PostSaver(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void save(Post post) {
        postRepository.save(post);
    }
}
