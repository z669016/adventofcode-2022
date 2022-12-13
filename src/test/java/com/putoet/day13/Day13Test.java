package com.putoet.day13;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day13Test {

    @Test
    void pairs() {
        final Day13 day13 = new Day13(null);
        // day13.pairs().forEach(System.out::println);
    }

    @Test
    void rightOrdered() {
        final Day13 day13 = new Day13(null);
        assertEquals(13, day13.rightOrdered());
    }

    @Test
    void decoderKey() {
        final Day13 day13 = new Day13(null);
        assertEquals(140, day13.decoderKey());
    }
}