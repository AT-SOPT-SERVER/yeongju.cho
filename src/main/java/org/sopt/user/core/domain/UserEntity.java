package org.sopt.user.core.domain;

import jakarta.persistence.*;
import org.sopt.common.constants.UserTableConstants;

import static org.sopt.common.constants.UserTableConstants.*;

@Entity
@Table(name = UserTableConstants.TABLE_USER)
public class UserEntity {

    @Id
    @Column(name = COLUMN_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = NICKNAME)
    private String nickname;

    protected UserEntity(){

    }

    public UserEntity(String nickname) {
        this.nickname = nickname;
    }

    public Long getId(){
        return this.id;
    }

    public String getNickname(){
        return this.nickname;
    }
}