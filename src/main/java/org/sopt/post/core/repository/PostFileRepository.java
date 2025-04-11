package org.sopt.post.core.repository;

import org.sopt.post.core.domain.Post;

import java.util.List;

public class PostFileRepository implements PostRepository{

    @Override
    public void save(final Post post) {

    }

    @Override
    public List<Post> findAll() {
        return null;
    }

    @Override
    public Post getPostById(final int id) {
        return null;
    }

    @Override
    public void update(final Post post, final String newTitle) {}

    @Override
    public void delete(final int id) {}

    @Override
    public List<Post> searchPostsByKeyword(final String keyword) {
        return null;
    }

    @Override
    public boolean existsById(final int id) {
        return false;
    }

    @Override
    public boolean existByTitle(final String title) {
        return false;
    }
}
