package org.sopt.post.service.logic;

import org.sopt.post.domain.Post;
import org.sopt.post.repository.PostRepository;

import java.util.List;

public class PostRetriever {
    private final PostRepository postRepository;

    public PostRetriever(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findAll(){
        return postRepository.findAll();
    }

    public boolean existsById(int id) {
        return postRepository.existsById(id);
    }

    public Post getPostById(int id) {
        return postRepository.getPostById(id);
    }

    public List<Post> searchPostsByKeyword(String keyword) {
        return postRepository.searchPostsByKeyword(keyword);
    }

    public boolean existByTitle(String title) {
        return postRepository.existByTitle(title);
    }
}
