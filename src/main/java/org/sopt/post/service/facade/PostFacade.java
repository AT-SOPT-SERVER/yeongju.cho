package org.sopt.post.service.facade;

import org.sopt.post.domain.Post;
import org.sopt.post.service.logic.PostRemover;
import org.sopt.post.service.logic.PostRetriever;
import org.sopt.post.service.logic.PostSaver;
import org.sopt.post.service.logic.PostUpdater;

import java.util.List;

public class PostFacade {
    private final PostSaver saver;
    private final PostRetriever retriever;
    private final PostRemover remover;
    private final PostUpdater updater;

    public PostFacade(PostSaver saver, PostRetriever retriever, PostRemover remover, PostUpdater updater) {
        this.saver = saver;
        this.retriever = retriever;
        this.remover = remover;
        this.updater = updater;
    }

    public void savePost(Post post) {
        saver.save(post);
    }

    public List<Post> findAllPosts() {
        return retriever.findAll();
    }

    public Post findPostById(int id) {
        return retriever.getPostById(id);
    }

    public boolean existsById(int id) {
        return retriever.existsById(id);
    }

    public boolean existsByTitle(String title) {
        return retriever.existByTitle(title);
    }

    public void updatePost(Post post, String newTitle) {
        updater.update(post, newTitle);
    }

    public void deletePost(int id) {
        remover.delete(id);
    }

    public List<Post> searchPostsByKeyword(String keyword) {
        return retriever.searchPostsByKeyword(keyword);
    }
}
