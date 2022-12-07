package com.putoet.day7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day7Test {

    @Test
    void totalSize() {
        final Day7 day7 = new Day7(null);

        assertEquals(95437, day7.totalSize());
    }

    @Test
    void feeUp() {
        final Day7 day7 = new Day7(null);

        assertEquals(8381165, day7.freeUp());
    }

    @Test
    void smallestToDelete() {
        final Day7 day7 = new Day7(null);

        assertEquals(24933642, day7.smallestToDelete().orElseThrow().size());
    }
}