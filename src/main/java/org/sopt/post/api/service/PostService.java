package org.sopt.post.api.service;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class PostService {

    private final UserFacade userFacade;
    private final PostRemover postRemover;
    private final PostRetriever postRetriever;
    private final PostUpdater postUpdater;
    private final PostSaver postSaver;

    @Transactional
    public long createPost(final long userId, PostCreateDto postCreateDto) {

        userFacade.findUser(userId);

        final Post postToCreate = Post.builder()
                .userId(userId)
                .title(postCreateDto.title())
                .content(postCreateDto.content())
                .build();

        try {
            Post createdPost = postSaver.save(postToCreate);
            return createdPost.getId();
        } catch (PostDuplicatedException e) {
            throw new PostConflictException(PostApiErrorCode.CONFLICT_DUPLICATE_TITLE);
        }
    }

    public Post getPostDetails(final long userId, final long postId) {
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
    public void updatePost (
            final long userId,
            final long postId,
            final PostUpdateDto postUpdateDto
    ) {
        Post originalPost = validatePostOwner(userId, postId);
        Post updatedPost = originalPost.update(
                postUpdateDto.newTitle() != null ? postUpdateDto.newTitle() : originalPost.getTitle(),
                postUpdateDto.newContent() != null ? postUpdateDto.newContent() : originalPost.getContent()
        );

        postUpdater.updatePost(updatedPost);
    }

    @Transactional
    public void deletePost (
            final long userId,
            final long postId
    ) {
        Post post = validatePostOwner(userId, postId);
        postRemover.deletePost(post);
    }

    private Post validatePostOwner(final long userId, final long postId) {
        userFacade.findUser(userId);
        Post post = postRetriever.findById(userId, postId);
        if (post.getUserId() != userId) {
            throw new PostForBiddenException(PostApiErrorCode.FORBIDDEN_POST);
        }
        return post;
    }
}