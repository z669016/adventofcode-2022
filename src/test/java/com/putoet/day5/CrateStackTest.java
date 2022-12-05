package com.putoet.day5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CrateStackTest {

    @Test
    void construct() {
        final CrateStack stack = new CrateStack("MZP");
        assertEquals("MZP", stack.toString());
    }

    @Test
    void take() {
        final CrateStack stack = new CrateStack("MZP");
        assertEquals("PZ", stack.take(2).toString());
    }

    @Test
    void add() {
        final CrateStack one = new CrateStack("MZP");
        final CrateStack two = new CrateStack("ABCD");
        two.add(one.take(3));
        assertEquals("ABCDPZM", two.toString());
    }
}