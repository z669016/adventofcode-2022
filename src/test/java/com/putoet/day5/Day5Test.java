package com.putoet.day5;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day5Test {
    @Test
    void part1() {
        final Day5 day5 = new Day5(null, new Cargo(List.of(
                new CrateStack("ZN"),
                new CrateStack("MCD"),
                new CrateStack("P")
        )));

        assertEquals("CMZ", day5.move());
    }
}