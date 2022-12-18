package com.putoet.day17;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RocksTest {
    private static final Rocks ROCKS = new Rocks();

    @Test
    void get() {
        assertEquals(Rocks.BAR, ROCKS.get());
        assertEquals(Rocks.CROSS, ROCKS.get());
        assertEquals(Rocks.HOOK, ROCKS.get());
        assertEquals(Rocks.LINE, ROCKS.get());
        assertEquals(Rocks.BLOCK, ROCKS.get());
        assertEquals(Rocks.BAR, ROCKS.get());
        assertEquals(Rocks.CROSS, ROCKS.get());
        assertEquals(Rocks.HOOK, ROCKS.get());
        assertEquals(Rocks.LINE, ROCKS.get());
        assertEquals(Rocks.BLOCK, ROCKS.get());
        assertEquals(Rocks.BAR, ROCKS.get());
        assertEquals(Rocks.CROSS, ROCKS.get());
        assertEquals(Rocks.HOOK, ROCKS.get());
        assertEquals(Rocks.LINE, ROCKS.get());
        assertEquals(Rocks.BLOCK, ROCKS.get());
    }

    @Test
    void maxHeight() {
        assertEquals(4, Rocks.maxHeight());
    }
}