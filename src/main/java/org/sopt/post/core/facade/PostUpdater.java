package org.sopt.post.core.facade;

import lombok.RequiredArgsConstructor;
import org.sopt.post.api.dto.request.PostUpdateDto;
import org.sopt.post.core.domain.Post;
import org.sopt.post.core.domain.PostEntity;
import org.sopt.post.core.exception.PostCoreErrorCode;
import org.sopt.post.core.exception.PostNotFoundException;
import org.sopt.post.core.repository.PostRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostUpdater {
    private final PostRepository postRepository;


    public void updatePost(final Post updatedPost) {
        PostEntity postEntity = postRepository.findById(updatedPost.getId())
                .orElseThrow(() -> new PostNotFoundException(PostCoreErrorCode.NOT_FOUND_POST));

        postEntity.update(updatedPost.getTitle(), updatedPost.getContent());
    }
}