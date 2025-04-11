package org.sopt.post.api.controller;

import org.sopt.post.api.service.PostService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostValidator {

    private static final int MAX_LENGTH = 30;
    private static final Pattern graphemePattern = Pattern.compile("\\X");

    private final PostService postService;
    public PostValidator(PostService postService) {
        this.postService = postService;
    }

    public boolean validateTitle(String title) {
        if (title == null) {
            System.out.println("제목은 필수로 입력되어야 합니다.");
            return false;
        }
        if (title.trim().isEmpty()) {
            System.out.println("제목은 필수로 입력되어야 합니다.");
            return false;
        }
        if (title.length() > MAX_LENGTH || countGraphemeClusters(title) > MAX_LENGTH) {
            System.out.println("제목은 30자 이하여야 합니다.");
            return false;
        }
        if (postService.existByTitle(title)) {
            System.out.println("❌ 이미 해당 제목의 게시글이 존재합니다. 제목은 중복될 수 없습니다.");
            return false;
        }
        return true;
    }

    public static int countGraphemeClusters(String body) {
        Matcher matcher = graphemePattern.matcher(body);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }
}
