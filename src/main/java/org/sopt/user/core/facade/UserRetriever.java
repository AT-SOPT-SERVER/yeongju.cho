package org.sopt.user.core.facade;

import org.sopt.user.core.domain.User;
import org.sopt.user.core.domain.UserEntity;
import org.sopt.user.core.exception.UserCoreErrorCode;
import org.sopt.user.core.exception.UserNotFoundException;
import org.sopt.user.core.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Transactional(readOnly = true)
public class UserRetriever {

    private final UserRepository userRepository;

    public UserRetriever(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(final long userId) {
        final UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(UserCoreErrorCode.USER_NOT_FOUND));
        return User.fromEntity(userEntity);
    }

    public Map<Long, String> findNicknameMap(final List<Long> userIdList) {
        final List<UserEntity> userList = userRepository.findAllById(userIdList);

        return userList.stream()
                .collect(
                        Collectors.toMap(
                                UserEntity::getId,
                                UserEntity::getNickname
                        )
                );
    }
}