package org.sopt.user.core.facade;

import lombok.RequiredArgsConstructor;
import org.sopt.user.core.domain.User;
import org.sopt.user.core.domain.UserEntity;
import org.sopt.user.core.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UserSaver {

    private final UserRepository userRepository;

    @Transactional
    public User save(final User user) {
        final UserEntity userEntity = userRepository.save(UserEntity.forCreate(user));
        return userEntity.toDomain();
    }
}