package com.putoet.day15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day15Test {

    @Test
    void usedPositionsInRangeForRow() {
        final Day15 day15 = new Day15(null);
        assertEquals(26, day15.usedPositionsInRangeForRow(10));
    }

    @Test
    void tuningFrequencies() {
        final Day15 day15 = new Day15(null);
        assertEquals(56000011, day15.tuningFrequencies(0, 20));
    }
}