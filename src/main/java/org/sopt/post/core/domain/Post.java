package org.sopt.post.core.domain;


import java.time.LocalDateTime;

public class Post {
    private final long id;
    private final long userId;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;

    public Post(long id, long userId, String title, String content, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public long getId(){
        return id;
    }

    public long getUserId(){
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent(){
        return content;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public static Post fromEntity(final PostEntity postEntity) {
        return new Post(
                postEntity.getId(),
                postEntity.getUserId(),
                postEntity.getTitle(),
                postEntity.getContent(),
                postEntity.getCreatedAt()
        );
    }
}