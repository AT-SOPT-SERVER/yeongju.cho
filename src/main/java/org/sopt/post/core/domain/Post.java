package org.sopt.post.core.domain;

public class Post {
    private final int id;
    private final String title;

    public Post(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId(){
        return id;
    }

    public String getTitle() {
        return title;
    }

    public static Post fromEntity(final PostEntity postEntity) {
        return new Post(
                postEntity.getId(),
                postEntity.getTitle()
        );
    }
}