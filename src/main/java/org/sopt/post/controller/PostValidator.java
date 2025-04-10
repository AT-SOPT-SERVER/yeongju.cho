package org.sopt.post.controller;

import org.sopt.post.service.PostService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostValidator {

    private static final int MAX_LENGTH = 30;
    private static final String regex = "\\X";

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
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(body);

        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }
}
