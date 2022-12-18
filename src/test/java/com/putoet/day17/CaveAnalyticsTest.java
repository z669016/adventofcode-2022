package com.putoet.day17;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CaveAnalyticsTest {
    private static final String input = ResourceLines.line("/day17.txt");

    private Push push;

    @BeforeEach
    void setup() {
        push = new Push(input);
    }

    @Test
    void findRepeat() {
        var cave = new CaveAnalytics(push);
        cave.run();

        var analytics = cave.analytics();
        assertEquals(26, analytics.startRepeat());
        assertEquals(35, analytics.repeatBlockSize());
    }
}