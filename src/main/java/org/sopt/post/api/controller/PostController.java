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

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDetailsResponse> getPost (
            @PathVariable final int postId
    ) {
        final Post post = postService.getPostDetails(postId);
        return ResponseEntity.ok(PostDetailsResponse.from(post));
    }

    @GetMapping("/posts")
    public ResponseEntity<PostListResponse> getPostList() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<Void> updatePostTitle (
            @PathVariable final int postId,
            @RequestBody @Valid final PostUpdateDto postUpdateDto
    ) {
        postService.updatePostTitle(postId, postUpdateDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<Void> deletePostById (
            @PathVariable final int postId
    )
    {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/post/keyword")
//    public List<PostEntity> searchPostsByKeyword(final String keyword) {
//        return postService.searchPostsByKeyword(keyword);
//    }
}