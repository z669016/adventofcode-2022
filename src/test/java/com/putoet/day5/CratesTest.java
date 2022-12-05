package com.putoet.day5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CratesTest {

    @Test
    void construct() {
        final Crates stack = new Crates("MZP");
        assertEquals("MZP", stack.toString());
    }

    @Test
    void take() {
        final Crates stack = new Crates("MZP");
        assertEquals("PZ", stack.take(2).toString());
    }

    @Test
    void add() {
        final Crates one = new Crates("MZP");
        final Crates two = new Crates("ABCD");
        two.add(one.take(3));
        assertEquals("ABCDPZM", two.toString());
    }
}