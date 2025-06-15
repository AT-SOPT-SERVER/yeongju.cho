package org.sopt.post.core.domain;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Post {

    @EqualsAndHashCode.Include
    private final long id;
    private final long userId;
    private final String title;
    private final String content;

    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    // 불변성 private final (생성 이후 수정 불가)
    @Builder
    private Post(long id, long userId, String title, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /*
        자기 자신을 변경하는게 x
        새로운 Post 객체 생성해서 변환 o
     */
    public Post update(String newTitle, String newContent) {
        return Post.builder()
                .id(id)
                .userId(userId)
                .title(newTitle)
                .content(newContent)
                .createdAt(createdAt)
                .updatedAt(LocalDateTime.now())
                .build();
    }

}