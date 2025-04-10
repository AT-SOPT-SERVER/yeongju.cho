package org.sopt.post.service.logic;

import org.sopt.post.domain.Post;
import org.sopt.post.repository.PostBasicRepository;

public class PostUpdater {
    private final PostBasicRepository postRepository;

    public PostUpdater(PostBasicRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void update(Post post, String newTitle) {
        postRepository.update(post, newTitle);
    }
}