package org.sopt.post.core.repository;

import org.sopt.common.IdGenerator;
import org.sopt.post.core.domain.Post;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PostBasicRepository implements PostRepository {

    private final Map<Integer, Post> storage = new ConcurrentHashMap<>();

    @Override
    public void save(final Post post) {
        int id = IdGenerator.generateId();
        storage.put(id, new Post(id, post.getTitle()));
    }

    @Override
    public List<Post> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Post getPostById(final int id) {
        return storage.get(id);
    }

    @Override
    public void update(final Post post, final String newTitle) {
        post.updateTitle(newTitle);
    }

    @Override
    public void delete(final int id) {
        storage.remove(id);
    }

    @Override
    public List<Post> searchPostsByKeyword(String keyword) {
        final List<Post> postList = new ArrayList<>();
        for (Post post : storage.values()) {
            if (post.getTitle().contains(keyword)) {
                postList.add(post);
            }
        }
        return postList;
    }

    @Override
    public boolean existsById(final int id) {
        return storage.containsKey(id);
    }

    @Override
    public boolean existByTitle(final String title) {
        for (Post post : storage.values()) {
            if (post.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }
}