package org.sopt.post.core.repository;

import org.sopt.post.core.domain.Post;

import java.util.List;

public interface PostRepository {
    public void save(Post post);
    public List<Post> findAll();
    public Post getPostById(int id);
    public void update(Post post, String newTitle);
    public void delete(int id);
    public List<Post> searchPostsByKeyword(String keyword);
    public boolean existsById(int id);
    public boolean existByTitle(String title);
}
