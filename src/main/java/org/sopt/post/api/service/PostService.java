package org.sopt.post.api.service;

import org.sopt.post.api.dto.request.PostUpdateDto;
import org.sopt.post.api.dto.response.PostListResponse;
import org.sopt.post.api.exception.PostConflictException;
import org.sopt.post.api.exception.PostApiErrorCode;
import org.sopt.post.core.domain.Post;
import org.sopt.post.core.domain.PostEntity;
import org.sopt.post.core.exception.PostDuplicatedException;
import org.sopt.post.api.dto.request.PostCreateDto;
import org.sopt.post.core.service.PostRemover;
import org.sopt.post.core.service.PostRetriever;
import org.sopt.post.core.service.PostSaver;
import org.sopt.post.core.service.PostUpdater;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PostService {

    private LocalDateTime lastCreateTime = null;
    private final PostRemover postRemover;
    private final PostRetriever postRetriever;
    private final PostUpdater postUpdater;
    private final PostSaver postSaver;

    public PostService(PostRemover postRemover, PostRetriever postRetriever, PostUpdater postUpdater, PostSaver postSaver) {
        this.postRemover = postRemover;
        this.postRetriever = postRetriever;
        this.postUpdater = postUpdater;
        this.postSaver = postSaver;
    }

    @Transactional
    public int createPost(PostCreateDto postCreateDto) {

//        if (!checkLatest3Minute()) {
//            throw new PostConflictException(PostErrorCode.CONFLICT_CREATE_LIMIT);
//        }

        final Post createPost;

        try {
            createPost = postSaver.save(postCreateDto.title());
        } catch (PostDuplicatedException e) {
            throw new PostConflictException(PostApiErrorCode.CONFLICT_DUPLICATE_TITLE);
        }
        lastCreateTime = LocalDateTime.now();
        return createPost.getId();
    }

    public boolean checkLatest3Minute() {
        if (lastCreateTime == null) return true;

        LocalDateTime now = LocalDateTime.now();
        return java.time.Duration.between(lastCreateTime, now).getSeconds() >= 180;
    }

    public Post getPostDetails(final int postId) {
        return postRetriever.findById(postId);
    }

    public PostListResponse getAllPosts(
            final String keyword
    ) {
        List<PostListResponse.PostDto> posts = (keyword == null)
                ? postRetriever.findAllPosts()
                : postRetriever.findAllPostsByKeyword(keyword);
            return new PostListResponse(posts);
    }

    @Transactional
    public void updatePostTitle (
            final int postId,
            final PostUpdateDto postUpdateDto
    ) {
        PostEntity postEntity = postRetriever.findEntityById(postId);
        postUpdater.updatePost(postEntity, postUpdateDto);
    }

    @Transactional
    public void deletePost (
            final int postId
    ) {
        PostEntity postEntity = postRetriever.findEntityById(postId);
        postRemover.deletePost(postEntity);
    }
}