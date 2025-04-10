package org.sopt.post.controller;

import org.sopt.post.domain.Post;
import org.sopt.post.service.PostService;

import java.util.List;

public class PostController {
    private final PostService postService;
    private final PostValidator postValidator;

    public PostController(PostService postService, PostValidator postValidator) {
        this.postService = postService;
        this.postValidator = postValidator;
    }

    public boolean createPost(final String title) {
        return postService.createPost(title);
    }

    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    public Post getPostById(int id) {
        return postService.getPostById(id);
    }
    public Boolean updatePostTitle(int id, String newTitle) {
        return postService.updatePostTitle(id, newTitle);
    }

    public boolean deletePostById(int id) {
        return postService.deletePostById(id);
    }

    public List<Post> searchPostsByKeyword(String keyword) {
        return postService.searchPostsByKeyword(keyword);
    }

    public boolean validateTitle(String title) {
        return postValidator.validateTitle(title);
    }
}