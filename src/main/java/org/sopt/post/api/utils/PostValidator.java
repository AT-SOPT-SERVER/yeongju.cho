package org.sopt.post.api.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostValidator {

    private static final int MAX_LENGTH = 30;
    private static final Pattern graphemePattern = Pattern.compile("\\X");

    public boolean validateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            System.out.println("제목은 필수로 입력되어야 합니다.");
            return false;
        }
        if (title.length() > MAX_LENGTH || countGraphemeClusters(title) > MAX_LENGTH) {
            System.out.println("제목은 30자 이하여야 합니다.");
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
