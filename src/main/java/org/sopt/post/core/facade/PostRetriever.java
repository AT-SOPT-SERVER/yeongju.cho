package org.sopt.post.core.facade;

import org.sopt.post.api.dto.response.PostListResponse;
import org.sopt.post.core.domain.Post;
import org.sopt.post.core.domain.PostEntity;
import org.sopt.post.core.exception.PostCoreErrorCode;
import org.sopt.post.core.exception.PostNotFoundException;
import org.sopt.post.core.repository.PostRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostRetriever {
    private final PostRepository postRepository;

    public PostRetriever(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post findById(final long userId, final long postId) {
        final PostEntity postEntity = postRepository.findByUserIdAndId(userId, postId)
                .orElseThrow(()-> new PostNotFoundException(PostCoreErrorCode.NOT_FOUND_POST));
        return Post.fromEntity(postEntity);
    }

    public PostEntity findEntityById(final long userId, final long postId) {
        return postRepository.findByUserIdAndId(userId, postId)
                .orElseThrow(()-> new PostNotFoundException(PostCoreErrorCode.NOT_FOUND_POST));
    }

    public List<Post> findAllOrderByCreatedAtDesc() {
        return postRepository.findAllOrderByCreatedAtDesc().stream()
                .map(Post::fromEntity)
                .toList();
    }

    /*
     아직 미구현
     */
//    public List<PostListResponse.PostDto> findAllPostsByKeyword(final String keyword) {
//        return postRepository.findByKeyword(keyword).stream()
//                .map(Post::fromEntity)
//                .map(PostListResponse.PostDto::from)
//                .toList();
//    }
}
