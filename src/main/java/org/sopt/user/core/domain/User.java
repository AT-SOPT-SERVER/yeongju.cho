package org.sopt.user.core.domain;

public class User {
    private final Long id;
    private final String nickname;

    public User(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    public Long getId(){
        return id;
    }

    public String getNickname(){
        return nickname;
    }

    public static User fromEntity(final UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getNickname()
        );
    }
}