package org.sopt.post.core.service;

import org.sopt.post.core.domain.Post;
import org.sopt.post.core.repository.PostRepository;

import java.util.List;

public class PostRetriever {
    private final PostRepository postRepository;

    public PostRetriever(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findAll(){
        return postRepository.findAll();
    }

    public boolean existsById(final int id) {
        return postRepository.existsById(id);
    }

    public Post getPostById(final int id) {
        return postRepository.getPostById(id);
    }

    public List<Post> searchPostsByKeyword(final String keyword) {
        return postRepository.searchPostsByKeyword(keyword);
    }

    public boolean existByTitle(final String title) {
        return postRepository.existByTitle(title);
    }
}
