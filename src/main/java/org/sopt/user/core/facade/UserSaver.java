package org.sopt.user.core.facade;

import org.sopt.user.core.domain.User;
import org.sopt.user.core.domain.UserEntity;
import org.sopt.user.core.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserSaver {

    private final UserRepository userRepository;

    public UserSaver(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User save(final String nickname) {
        final UserEntity userEntity = userRepository.save(new UserEntity(nickname));
        return User.fromEntity(userEntity);
    }
}