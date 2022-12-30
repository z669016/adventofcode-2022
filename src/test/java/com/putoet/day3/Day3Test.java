package com.putoet.day3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day3Test {

    @Test
    void part1() {
        final var day3 = new Day3();
        assertEquals(157, day3.sharedPrioritySum());
    }

    @Test
    void part2() {
        final var day3 = new Day3();
        assertEquals(70, day3.batchPrioritySum());
    }
}