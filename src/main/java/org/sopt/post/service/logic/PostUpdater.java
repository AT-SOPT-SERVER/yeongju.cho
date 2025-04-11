package org.sopt.post.service.logic;

import org.sopt.post.domain.Post;
import org.sopt.post.repository.PostRepository;

public class PostUpdater {
    private final PostRepository postRepository;

    public PostUpdater(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void update(Post post, String newTitle) {
        postRepository.update(post, newTitle);
    }
}