package org.sopt.post.api.controller;

import jakarta.validation.Valid;
import org.sopt.post.api.dto.request.PostCreateDto;
import org.sopt.post.api.dto.response.PostDetailsResponse;
import org.sopt.post.api.dto.request.PostUpdateDto;
import org.sopt.post.api.dto.response.PostListResponse;
import org.sopt.post.api.service.PostService;
import org.sopt.post.core.domain.Post;
import org.sopt.user.api.service.UserService;
import org.sopt.user.core.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;
    private final UserService userService;

    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> createPost(
            @RequestHeader("Authorization") final long userId,
            @RequestBody @Valid final PostCreateDto postCreateDto
    ) {
        long postId = postService.createPost(userId, postCreateDto);
        return ResponseEntity.created(URI.create(Long.valueOf(postId).toString())).build();
    }

    // 게시글 상세 조회
    @GetMapping("/{post-id}")
    public ResponseEntity<PostDetailsResponse> getPostDetails (
            @RequestHeader("Authorization") final long userId,
            @PathVariable(name = "post-id") final int postId
    ) {
        final Post post = postService.getPostDetails(userId, postId);
        final User user = userService.findById(post.getUserId());
        return ResponseEntity.ok(PostDetailsResponse.from(post, user));
    }

    // 게시글 전체 조회
    @GetMapping
    public ResponseEntity<PostListResponse> getPostList(
            @RequestParam(required = false) final String keyword
    ) {
        return ResponseEntity.ok(postService.getAllPosts(keyword));
    }

    @PutMapping("/{post-id}")
    public ResponseEntity<Void> updatePostTitle (
            @RequestHeader("Authorization") final long userId,
            @PathVariable(name = "post-id") final int postId,
            @RequestBody @Valid final PostUpdateDto postUpdateDto
    ) {
        postService.updatePostTitle(userId, postId, postUpdateDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{post-id}")
    public ResponseEntity<Void> deletePostById (
            @RequestHeader("Authorization") final long userId,
            @PathVariable(name = "post-id") final int postId
    ) {
        postService.deletePost(userId, postId);
        return ResponseEntity.noContent().build();
    }
}