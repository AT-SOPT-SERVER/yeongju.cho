package org.sopt.post.core.facade;

import org.sopt.post.core.domain.Post;
import org.sopt.post.core.domain.PostEntity;
import org.sopt.post.core.exception.PostCoreErrorCode;
import org.sopt.post.core.exception.PostDuplicatedException;
import org.sopt.post.core.repository.PostRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PostSaver {
    private final PostRepository postRepository;

    public PostSaver(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public Post save(final long userId, final String title, final String content) {
        try {
            final PostEntity postEntity = postRepository.save(new PostEntity(userId, title, content));
            return Post.fromEntity(postEntity);
        } catch (DataIntegrityViolationException e) {
            throw new PostDuplicatedException(PostCoreErrorCode.DUPLICATED_POST);
        }
    }
}
