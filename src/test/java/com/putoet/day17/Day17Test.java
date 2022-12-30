package com.putoet.day17;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day17Test {
    private Day17 day17;

    @BeforeEach
    void setup() {
        day17 = new Day17();
    }

    @Test
    void findRepeat() {
        var analytics = day17.analytics();
        assertEquals(26, analytics.startRepeat());
        assertEquals(35, analytics.repeatBlockSize());
    }

    @Test
    void height() {
        var triplet = day17.analytics();
        assertEquals(1_514_285_714_288L, day17.heightAfter(triplet, 1_000_000_000_000L));
    }
}