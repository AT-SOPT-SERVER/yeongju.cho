package org.sopt.user.core.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.sopt.common.domain.BaseTimeEntity;

import static org.sopt.user.core.domain.UserTableConstants.*;

@Entity
@Table(name = TABLE_USER, indexes = {
        @Index(name = "idx_user_nickname", columnList = "nickname", unique = true)
})
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserEntity extends BaseTimeEntity {

    @Id
    @Column(name = COLUMN_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = NICKNAME, nullable = false)
    private String nickname;

    public UserEntity(String nickname) {
        this.nickname = nickname;
    }

    // 신규 저장용
    public static UserEntity forCreate(User user) {
        return new UserEntity(user.getNickname());
    }

    // Entity -> Domain
    public User toDomain() {
        return User.builder()
                .id(id)
                .nickname(nickname)
                .createdAt(getCreatedAt())
                .updatedAt(getUpdatedAt())
                .build();
    }

//    // Domain -> Entity (조회 및 수정)
//    public static UserEntity fromDomain(User user) {
//        return new UserEntity(
//                user.getId(),
//                user.getNickname());
//    }

}