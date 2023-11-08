package com.putoet.day24;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValvesTest {

    @Test
    void solve() {
        final var count = PathFinder.solve().orElseThrow();
        assertEquals(18, count.steps());
    }
}