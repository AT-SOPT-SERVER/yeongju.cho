package org.sopt.post.service;

import org.sopt.common.IdGenerator;
import org.sopt.post.domain.Post;
import org.sopt.post.service.facade.PostFacade;

import java.time.LocalDateTime;
import java.util.List;

public class PostService {
    private final PostFacade postFacade;
    private LocalDateTime lastCreateTime = null;

    public PostService(PostFacade postFacade) {
        this.postFacade = postFacade;
    }

    public boolean createPost(String title) {
//        if (!checkLatest3Minute()) {
//            return false;
//        }

        int id = IdGenerator.generateId();
        Post post = new Post(id, title);
        postFacade.savePost(post);
        lastCreateTime = LocalDateTime.now();
        return true;
    }

    public boolean checkLatest3Minute() {
        LocalDateTime now = LocalDateTime.now();
        return lastCreateTime == null ||
                java.time.Duration.between(lastCreateTime, now).getSeconds() >= 180;
    }

    public List<Post> getAllPosts() {
        return postFacade.findAllPosts();
    }

    public Post getPostById(int id) {
        if (!postFacade.existsById(id)) {
            return null;
        }
        return postFacade.findPostById(id);
    }

    public boolean updatePostTitle(int id, String newTitle) {
        Post post = postFacade.findPostById(id);
        if (post == null) {
            return false;
        }
        postFacade.updatePost(post, newTitle);
        return true;
    }

    public boolean deletePostById(int id) {
        if (!postFacade.existsById(id)) {
            return false;
        }
        postFacade.deletePost(id);
        return true;
    }

    public List<Post> searchPostsByKeyword(String keyword) {
        return postFacade.searchPostsByKeyword(keyword);
    }

    public boolean existByTitle(String title) {
        return postFacade.existsByTitle(title);
    }
}