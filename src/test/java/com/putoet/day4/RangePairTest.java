package com.putoet.day4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RangePairTest {
    private static final Range first = new Range(3, 7);
    private final static Range second = new Range(4, 5);
    private final static Range third = new Range(6, 12);
    private final RangePair pairContains = new RangePair(first, second);
    private final RangePair pairNotContains = new RangePair(first, third);

    @Test
    void fullContainment() {
        assertTrue(pairContains.containment());
        assertFalse(pairNotContains.containment());
    }

    @Test
    void overlap() {
        assertTrue(pairContains.overlap());
        assertTrue(pairNotContains.overlap());
    }

    @Test
    void of() {
        assertEquals(pairContains, RangePair.of("3-7,4-5"));
    }
}