package com.putoet.day5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CratesTest {

    @Test
    void construct() {
        final var stack = new Crates("MZP");
        assertEquals("MZP", stack.toString());
    }

    @Test
    void take() {
        final var stack = new Crates("MZP");
        assertEquals("PZ", stack.take(2).toString());
    }

    @Test
    void add() {
        final var one = new Crates("MZP");
        final var two = new Crates("ABCD");
        two.add(one.take(3));
        assertEquals("ABCDPZM", two.toString());
    }
}