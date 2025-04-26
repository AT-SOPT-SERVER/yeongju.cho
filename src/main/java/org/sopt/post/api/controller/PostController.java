package org.sopt.post.api.controller;

import jakarta.validation.Valid;
import org.sopt.post.api.dto.request.PostCreateDto;
import org.sopt.post.api.dto.response.PostDetailsResponse;
import org.sopt.post.api.dto.request.PostUpdateDto;
import org.sopt.post.api.dto.response.PostListResponse;
import org.sopt.post.api.service.PostService;
import org.sopt.post.core.domain.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts")
    public ResponseEntity<Void> createPost(
            @RequestBody @Valid final PostCreateDto postCreateDto
    ) {
        int postId = postService.createPost(postCreateDto);
        return ResponseEntity.created(URI.create(Long.valueOf(postId).toString())).build();
    }

    @GetMapping("/posts/{post-id}")
    public ResponseEntity<PostDetailsResponse> getPost (
            @PathVariable(name = "post-id") final int postId
    ) {
        final Post post = postService.getPostDetails(postId);
        return ResponseEntity.ok(PostDetailsResponse.from(post));
    }

    @GetMapping("/posts")
    public ResponseEntity<PostListResponse> getPostList(
            @RequestParam(required = false) final String keyword
    ) {
        return ResponseEntity.ok(postService.getAllPosts(keyword));
    }

    @PutMapping("/posts/{post-id}")
    public ResponseEntity<Void> updatePostTitle (
            @PathVariable(name = "post-id") final int postId,
            @RequestBody @Valid final PostUpdateDto postUpdateDto
    ) {
        postService.updatePostTitle(postId, postUpdateDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/posts/{post-id}")
    public ResponseEntity<Void> deletePostById (
            @PathVariable(name = "post-id") final int postId
    ) {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }
}