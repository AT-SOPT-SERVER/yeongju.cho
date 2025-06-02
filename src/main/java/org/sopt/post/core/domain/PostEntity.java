package org.sopt.post.core.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sopt.common.domain.BaseTimeEntity;

import static org.sopt.post.core.domain.PostTableConstants.*;

@Entity
@Table(name = TABLE_POST, indexes = {
        @Index(name = "idx_post_title", columnList = "title", unique = true)
})
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PostEntity extends BaseTimeEntity {

    @Id
    @Column(name = COLUMN_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = COLUMN_USER_ID, nullable = false)
    private Long userId;

    @Column(name = COLUMN_TITLE, nullable = false)
    private String title;

    @Column(name = COLUMN_CONTENT, nullable = false)
    private String content;

    private PostEntity(Long userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }

    // 신규 저장용
    public static PostEntity forCreate(Post post) {
        return new PostEntity(
                post.getUserId(),
                post.getTitle(),
                post.getContent());
    }

    // Entity -> Domain
    public Post toDomain() {
        return Post.builder()
                .id(id)
                .userId(userId)
                .title(title)
                .content(content)
                .createdAt(getCreatedAt())
                .updatedAt(getUpdatedAt())
                .build();
    }

//    // Domain -> Entity (조회 및 수정)
//    public static PostEntity fromDomain(Post post) {
//        return new PostEntity(
//                post.getId(),
//                post.getUserId(),
//                post.getTitle(),
//                post.getContent());
//    }

    public void update(String newTitle, String newContent) {
        this.title = newTitle;
        this.content = newContent;
    }

}