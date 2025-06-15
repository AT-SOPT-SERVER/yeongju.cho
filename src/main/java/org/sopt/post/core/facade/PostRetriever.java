package org.sopt.post.core.facade;

import lombok.RequiredArgsConstructor;
import org.sopt.post.core.domain.Post;
import org.sopt.post.core.domain.PostEntity;
import org.sopt.post.core.exception.PostCoreErrorCode;
import org.sopt.post.core.exception.PostNotFoundException;
import org.sopt.post.core.repository.PostRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PostRetriever {
    private final PostRepository postRepository;

    public Post findById(final long userId, final long postId) {
        return postRepository.findByUserIdAndId(userId, postId)
                .map(PostEntity::toDomain)
                .orElseThrow(()-> new PostNotFoundException(PostCoreErrorCode.NOT_FOUND_POST));
    }

    public List<Post> findAllOrderByCreatedAtDesc() {
        return postRepository.findAllOrderByCreatedAtDesc().stream()
                .map(PostEntity::toDomain)
                .toList();
    }

}
