package org.sopt.user.api.service;

import org.sopt.user.api.dto.UserSignupDto;
import org.sopt.user.core.domain.User;
import org.sopt.user.core.facade.UserRetriever;
import org.sopt.user.core.facade.UserSaver;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserSaver userSaver;
    private final UserRetriever userRetriever;

    public UserService(UserSaver userSaver, UserRetriever userRetriever) {
        this.userSaver = userSaver;
        this.userRetriever = userRetriever;
    }

    public long signup(UserSignupDto userSignupDto){
        return userSaver.save(userSignupDto.nickname()).getId();
    }

    public User findById(final long userId) {
        return userRetriever.findById(userId);
    }
}