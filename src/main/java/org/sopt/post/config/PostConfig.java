package org.sopt.post.config;

import org.sopt.post.controller.PostController;
import org.sopt.post.controller.PostValidator;
import org.sopt.post.service.*;
import org.sopt.post.repository.PostBasicRepository;
import org.sopt.post.service.facade.PostFacade;
import org.sopt.post.service.logic.PostRemover;
import org.sopt.post.service.logic.PostRetriever;
import org.sopt.post.service.logic.PostSaver;
import org.sopt.post.service.logic.PostUpdater;

public class PostConfig {

    public static PostController configPostController() {
        PostBasicRepository postRepository = new PostBasicRepository();

        PostSaver postSaver = new PostSaver(postRepository);
        PostRetriever postRetriever = new PostRetriever(postRepository);
        PostRemover postRemover = new PostRemover(postRepository);
        PostUpdater postUpdater = new PostUpdater(postRepository);
        PostService postService = new PostService(new PostFacade(postSaver, postRetriever, postRemover, postUpdater));
        PostValidator postValidator = new PostValidator(postService);

        return new PostController(postService, postValidator);
    }
}