package org.sopt.user.core.facade;

import org.sopt.post.core.domain.Post;
import org.sopt.user.core.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class UserFacade {
    private final UserRetriever userRetriever;

    public UserFacade(UserRetriever userRetriever) {
        this.userRetriever = userRetriever;
    }

    public User findUser(final long userId) {
        return userRetriever.findById(userId);
    }

    public Map<Long, String> findWriterNickname(final List<Post> postList) {
        final List<Long> userIdList = postList.stream().map(Post::getUserId).toList();
        return userRetriever.findNicknameMap(userIdList);
    }
}