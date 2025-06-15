package org.sopt.post.core.facade;

import lombok.RequiredArgsConstructor;
import org.sopt.post.core.domain.Post;
import org.sopt.post.core.domain.PostEntity;
import org.sopt.post.core.exception.PostCoreErrorCode;
import org.sopt.post.core.exception.PostNotFoundException;
import org.sopt.post.core.repository.PostRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostRemover {
    private final PostRepository postRepository;

    public void deletePost(final Post post) {
        postRepository.deleteById(post.getId());
    }

}
