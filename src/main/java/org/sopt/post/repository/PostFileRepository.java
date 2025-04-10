package org.sopt.post.repository;

import org.sopt.post.domain.Post;

import java.util.List;

public class PostFileRepository implements PostRepository{

    @Override
    public void save(Post post) {}

    @Override
    public List<Post> findAll() {
        return null;
    }

    @Override
    public Post getPostById(int id) {
        return null;
    }

    @Override
    public void update(Post post, String newTitle) {}

    @Override
    public void delete(int id) {}

    @Override
    public List<Post> searchPostsByKeyword(String keyword) {
        return null;
    }

    @Override
    public boolean existsById(int id) {
        return false;
    }

    @Override
    public boolean existByTitle(String title) {
        return false;
    }
}
