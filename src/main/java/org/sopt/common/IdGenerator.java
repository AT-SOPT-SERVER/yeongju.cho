package org.sopt.common;

import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {
    private static final AtomicInteger numbering = new AtomicInteger();

    public static int generateId() {
        return numbering.incrementAndGet();
    }
}