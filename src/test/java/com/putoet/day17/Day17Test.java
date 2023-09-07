package com.putoet.day17;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day17Test {
    private static final String input = ResourceLines.line("/day17.txt");

    @Test
    void findRepeat() {
        var analytics = Day17.analytics(input);
        assertEquals(26, analytics.startRepeat());
        assertEquals(35, analytics.repeatBlockSize());
    }

    @Test
    void height() {
        var triplet = Day17.analytics(input);
        assertEquals(1_514_285_714_288L, Day17.heightAfter(input, triplet, 1_000_000_000_000L));
    }
}