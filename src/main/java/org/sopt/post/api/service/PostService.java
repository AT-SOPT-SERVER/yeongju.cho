package org.sopt.post.api.service;

import org.sopt.common.IdGenerator;
import org.sopt.post.core.domain.Post;
import org.sopt.post.core.service.PostRemover;
import org.sopt.post.core.service.PostRetriever;
import org.sopt.post.core.service.PostSaver;
import org.sopt.post.core.service.PostUpdater;

import java.time.LocalDateTime;
import java.util.List;

public class PostService {

    private LocalDateTime lastCreateTime = null;
    private final PostRemover postRemover;
    private final PostRetriever postRetriever;
    private final PostUpdater postUpdater;
    private final PostSaver postSaver;

    public PostService(PostRemover postRemover, PostRetriever postRetriever, PostSaver postSaver, PostUpdater postUpdater) {
        this.postRemover = postRemover;
        this.postRetriever = postRetriever;
        this.postUpdater = postUpdater;
        this.postSaver = postSaver;
    }

    public boolean createPost(final String title) {
//        if (!checkLatest3Minute()) {
//            return false;
//        }

        int id = IdGenerator.generateId();
        Post post = new Post(id, title);
        postSaver.save(post);
        lastCreateTime = LocalDateTime.now();
        return true;
    }

    public boolean checkLatest3Minute() {
        LocalDateTime now = LocalDateTime.now();
        return lastCreateTime == null ||
                java.time.Duration.between(lastCreateTime, now).getSeconds() >= 180;
    }

    public List<Post> getAllPosts() {
        return postRetriever.findAll();
    }

    public Post getPostById(final int id) {
        if (!postRetriever.existsById(id)) {
            return null;
        }
        return postRetriever.getPostById(id);
    }

    public boolean updatePostTitle(final int id, final String newTitle) {
        Post post = postRetriever.getPostById(id);
        if (post == null) {
            return false;
        }
        postUpdater.update(post, newTitle);
        return true;
    }

    public boolean deletePostById(final int id) {
        if (!postRetriever.existsById(id)) {
            return false;
        }
        postRemover.delete(id);
        return true;
    }

    public List<Post> searchPostsByKeyword(final String keyword) {
        return postRetriever.searchPostsByKeyword(keyword);
    }

    public boolean existByTitle(final String title) {
        return postRetriever.existByTitle(title);
    }
}