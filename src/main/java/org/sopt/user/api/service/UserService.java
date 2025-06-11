package org.sopt.user.api.service;

import lombok.RequiredArgsConstructor;
import org.sopt.user.api.dto.UserSignupDto;
import org.sopt.user.core.domain.User;
import org.sopt.user.core.exception.NicknameDuplicatedException;
import org.sopt.user.core.exception.UserCoreErrorCode;
import org.sopt.user.core.facade.UserRetriever;
import org.sopt.user.core.facade.UserSaver;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserSaver userSaver;
    private final UserRetriever userRetriever;

    public long signup(UserSignupDto userSignupDto){

        validateDuplicateNickname(userSignupDto.nickname());

        final User userToCreate = User.builder()
                .nickname(userSignupDto.nickname())
                .build();

        final User savedUser = userSaver.save(userToCreate);
        return savedUser.getId();
    }

    public User findById(final long userId) {
        return userRetriever.findById(userId);
    }

    private void validateDuplicateNickname(final String nickname) {
        if (userRetriever.existByNickname(nickname)) {
            throw new NicknameDuplicatedException(UserCoreErrorCode.NICKNAME_DUPLICATED);
        }
    }
}