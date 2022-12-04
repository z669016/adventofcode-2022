package com.putoet.day4;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RangePairTest {
    final static Range first = new Range(Pair.with(3,7));
    final static Range second = new Range(Pair.with(4,5));
    final static Range third = new Range(Pair.with(6,12));
    final RangePair pairContains = new RangePair(Pair.with(first,second));
    final RangePair pairNotContains = new RangePair(Pair.with(first,third));

    @Test
    void fullContainment() {
        assertTrue(pairContains.containment());
        assertFalse(pairNotContains.containment());
    }

    @Test void overlap() {
        assertTrue(pairContains.overlap());
        assertTrue(pairNotContains.overlap());
    }

    @Test
    void of() {
        assertEquals(pairContains, RangePair.of("3-7,4-5"));
    }
}