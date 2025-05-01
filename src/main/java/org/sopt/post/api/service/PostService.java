package org.sopt.post.api.service;

import org.sopt.post.api.dto.request.PostUpdateDto;
import org.sopt.post.api.dto.response.PostListResponse;
import org.sopt.post.api.exception.PostConflictException;
import org.sopt.post.api.exception.PostApiErrorCode;
import org.sopt.post.api.exception.PostForBiddenException;
import org.sopt.post.core.domain.Post;
import org.sopt.post.core.domain.PostEntity;
import org.sopt.post.core.exception.PostDuplicatedException;
import org.sopt.post.api.dto.request.PostCreateDto;
import org.sopt.post.core.facade.PostRemover;
import org.sopt.post.core.facade.PostRetriever;
import org.sopt.post.core.facade.PostSaver;
import org.sopt.post.core.facade.PostUpdater;
import org.sopt.user.core.facade.UserFacade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class PostService {

    private final UserFacade userFacade;
//    private LocalDateTime lastCreateTime = null;
    private final PostRemover postRemover;
    private final PostRetriever postRetriever;
    private final PostUpdater postUpdater;
    private final PostSaver postSaver;

    public PostService(UserFacade userFacade, PostRemover postRemover, PostRetriever postRetriever, PostUpdater postUpdater, PostSaver postSaver) {
        this.userFacade = userFacade;
        this.postRemover = postRemover;
        this.postRetriever = postRetriever;
        this.postUpdater = postUpdater;
        this.postSaver = postSaver;
    }

    @Transactional
    public long createPost(final long userId, PostCreateDto postCreateDto) {

        userFacade.findUser(userId);

//        if (!checkLatest3Minute()) {
//            throw new PostConflictException(PostErrorCode.CONFLICT_CREATE_LIMIT);
//        }

        final Post createPost;
        try {
            createPost = postSaver.save(userId, postCreateDto.title(), postCreateDto.content());
        } catch (PostDuplicatedException e) {
            throw new PostConflictException(PostApiErrorCode.CONFLICT_DUPLICATE_TITLE);
        }
//        lastCreateTime = LocalDateTime.now();
        return createPost.getId();
    }

//    public boolean checkLatest3Minute() {
//        if (lastCreateTime == null) return true;
//
//        LocalDateTime now = LocalDateTime.now();
//        return java.time.Duration.between(lastCreateTime, now).getSeconds() >= 180;
//    }

    public Post getPostDetails(final long userId, final int postId) {
        validatePostOwner(userId, postId);
        return postRetriever.findById(userId, postId);
    }

    public PostListResponse getAllPosts(
            final String keyword
    ) {
//        List<PostListResponse.PostDto> posts = (keyword == null)
//                ? postRetriever.findAllPosts()
//                : postRetriever.findAllPostsByKeyword(keyword);
        List<Post> posts = postRetriever.findAllOrderByCreatedAtDesc();

        Map<Long, String> nicknameMap = userFacade.findWriterNickname(posts);
        return PostListResponse.from(posts, nicknameMap);
    }

    @Transactional
    public void updatePostTitle (
            final long userId,
            final int postId,
            final PostUpdateDto postUpdateDto
    ) {
        PostEntity postEntity = validatePostOwner(userId, postId);
        postUpdater.updatePost(postEntity, postUpdateDto);
    }

    @Transactional
    public void deletePost (
            final long userId,
            final int postId
    ) {
        PostEntity postEntity = validatePostOwner(userId, postId);
        postRemover.deletePost(postEntity);
    }

    private PostEntity validatePostOwner(final long userId, final int postId) {
        userFacade.findUser(userId);
        PostEntity postEntity = postRetriever.findEntityById(userId, postId);
        if (postEntity.getUserId() != userId) {
            throw new PostForBiddenException(PostApiErrorCode.FORBIDDEN_POST);
        }
        return postEntity;
    }
}