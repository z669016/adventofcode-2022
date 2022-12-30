package com.putoet.day5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day5Test {

    @Test
    void part1() {
        final Day5 day5 = new Day5();
        assertEquals("CMZ", day5.move(day5.initCargo(), Day5.crateMover9000));
    }

    @Test
    void part2() {
        final Day5 day5 = new Day5();
        assertEquals("MCD", day5.move(day5.initCargo(), Day5.crateMover9001));
    }
}