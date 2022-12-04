package com.putoet.day4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day4Test {

    @Test
    void containingPairs() {
        final var day4 = new Day4(null);
        assertEquals(2, day4.containingPairs());
    }

    @Test
    void overlappingPairs() {
        final var day4 = new Day4(null);
        assertEquals(4, day4.overlappingPairs());
    }
}