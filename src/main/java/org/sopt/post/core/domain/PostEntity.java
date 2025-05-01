package org.sopt.post.core.domain;

import jakarta.persistence.*;
import org.sopt.common.constants.PostTableConstants;

import java.time.LocalDateTime;

import static org.sopt.common.constants.PostTableConstants.*;

@Entity
@Table(
        name = PostTableConstants.TABLE_POST,
        indexes = {
                @Index(name = "uk1", columnList = "title", unique = true)
        }
)
public class PostEntity {

    @Id
    @Column(name = COLUMN_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = COLUMN_USER_ID)
    private long userId;

    @Column(name = COLUMN_TITLE, nullable = false)
    private String title;

    @Column(name = COLUMN_CONTENT)
    private String content;

    @Column(name = COLUMN_CREATED_AT, nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    protected PostEntity(){

    }

    public PostEntity(long userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }

    public void updateTitle(String newTitle) {
        this.title = newTitle;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public long getId() {
        return this.id;
    }
    public long getUserId(){
        return userId;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent(){
        return this.content;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }
}