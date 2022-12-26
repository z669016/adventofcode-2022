package com.putoet.day22;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PathTest {

    @Test
    void next() {
        final Path path = new Path("10R5L5R10L4R5L5");
        assertEquals("10", path.next());
        assertEquals("R", path.next());
        assertEquals("5", path.next());
        assertEquals("L", path.next());
        assertEquals("5", path.next());
        assertEquals("R", path.next());
        assertTrue(path.hasNext());
        assertEquals("10", path.next());
        assertEquals("L", path.next());
        assertEquals("4", path.next());
        assertEquals("R", path.next());
        assertEquals("5", path.next());
        assertEquals("L", path.next());
        assertEquals("5", path.next());
        assertFalse(path.hasNext());
    }
}