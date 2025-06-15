package org.sopt.post.core.facade;

import lombok.RequiredArgsConstructor;
import org.sopt.post.core.domain.Post;
import org.sopt.post.core.domain.PostEntity;
import org.sopt.post.core.exception.PostCoreErrorCode;
import org.sopt.post.core.exception.PostDuplicatedException;
import org.sopt.post.core.repository.PostRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PostSaver {
    private final PostRepository postRepository;

    @Transactional
    public Post save(final Post post) {
        try {
            PostEntity savedEntity = postRepository.save(PostEntity.forCreate(post));
            return savedEntity.toDomain();
        } catch (DataIntegrityViolationException e) {
            throw new PostDuplicatedException(PostCoreErrorCode.DUPLICATED_POST);
        }
    }
}
