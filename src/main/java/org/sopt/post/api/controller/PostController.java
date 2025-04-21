package org.sopt.post.api.controller;

import org.sopt.post.core.domain.Post;
import org.sopt.post.api.service.PostService;

import java.util.List;

public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    public boolean createPost(final String title) {
        return postService.createPost(title);
    }

    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    public Post getPostById(final int id) {
        return postService.getPostById(id);
    }
    public Boolean updatePostTitle(final int id, final String newTitle) {
        return postService.updatePostTitle(id, newTitle);
    }

    public boolean deletePostById(final int id) {
        return postService.deletePostById(id);
    }

    public List<Post> searchPostsByKeyword(final String keyword) {
        return postService.searchPostsByKeyword(keyword);
    }
}