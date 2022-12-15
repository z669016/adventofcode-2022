package com.putoet.day15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RangeTest {

    @Test
    void combine() {
        assertEquals(new Range(-1,19), new Range(-1,9).combine(new Range(9, 19)));
        assertEquals(new Range(-1,19), new Range(9, 19).combine(new Range(-1,9)));

        assertEquals(new Range(-1,19), new Range(-1,10).combine(new Range(9, 19)));
        assertEquals(new Range(-1,19), new Range(9, 19).combine(new Range(-1,10)));

        assertEquals(new Range(-1,10), new Range(-1,10).combine(new Range(0, 8)));
        assertEquals(new Range(-1,10), new Range(0, 8).combine(new Range(-1,10)));
    }

    @Test
    void overlaps() {
        assertTrue(new Range(-1,9).overlaps(new Range(9, 19)));
        assertTrue((new Range(9, 19).overlaps(new Range(-1,9))));

        assertTrue(new Range(-1,10).overlaps(new Range(9, 19)));
        assertTrue((new Range(9, 19).overlaps(new Range(-1,9))));

        assertTrue(new Range(-1,10).overlaps(new Range(0, 8)));
        assertTrue((new Range(0, 8).overlaps(new Range(-1,10))));

        assertFalse(new Range(-1,8).overlaps(new Range(9, 19)));
    }
}