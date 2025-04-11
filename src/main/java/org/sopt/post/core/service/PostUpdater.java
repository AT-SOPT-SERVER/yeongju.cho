package org.sopt.post.core.service;

import org.sopt.post.core.domain.Post;
import org.sopt.post.core.repository.PostRepository;

public class PostUpdater {
    private final PostRepository postRepository;

    public PostUpdater(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void update(final Post post, final String newTitle) {
        postRepository.update(post, newTitle);
    }
}