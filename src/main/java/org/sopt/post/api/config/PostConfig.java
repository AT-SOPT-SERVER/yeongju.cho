package org.sopt.post.api.config;

import org.sopt.post.api.controller.PostController;
import org.sopt.post.api.service.PostService;
import org.sopt.post.core.repository.PostBasicRepository;
import org.sopt.post.core.service.PostRemover;
import org.sopt.post.core.service.PostRetriever;
import org.sopt.post.core.service.PostSaver;
import org.sopt.post.core.service.PostUpdater;

public class PostConfig {

    public static PostController configPostController() {
        PostBasicRepository postRepository = new PostBasicRepository();

        PostSaver postSaver = new PostSaver(postRepository);
        PostRetriever postRetriever = new PostRetriever(postRepository);
        PostRemover postRemover = new PostRemover(postRepository);
        PostUpdater postUpdater = new PostUpdater(postRepository);
        PostService postService = new PostService(
                postRemover,
                postRetriever,
                postSaver,
                postUpdater
        );
        return new PostController(postService);
    }
}