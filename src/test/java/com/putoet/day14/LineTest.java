package com.putoet.day14;

import com.putoet.grid.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LineTest {
    private static final String LINE0 = "498,4 -> 498,6 -> 496,6";
    private static final String LINE1 = "503,4 -> 502,4 -> 502,9 -> 494,9";

    private final Line[] lines = new Line[2];

    @BeforeEach
    void setup() {
        lines[0] = Line.from(LINE0);
        lines[1] = Line.from(LINE1);
    }

    @Test
    void minX() {
        assertEquals(494, lines[1].minX());
    }

    @Test
    void maxX() {
        assertEquals(503, lines[1].maxX());
    }

    @Test
    void minY() {
        assertEquals(4, lines[1].minY());
    }

    @Test
    void maxY() {
        assertEquals(9, lines[1].maxY());
    }

    @Test
    void parts() {
        final var i = lines[1].parts();
        assertTrue(i.hasNext());
        assertEquals(new Line(List.of(Point.of(503,4), Point.of(502,4))), i.next());
        assertTrue(i.hasNext());
        assertEquals(new Line(List.of(Point.of(502,4), Point.of(502,9))), i.next());
        assertTrue(i.hasNext());
        assertEquals(new Line(List.of(Point.of(502,9), Point.of(494,9))), i.next());
        assertFalse(i.hasNext());

        assertThrows(IllegalStateException.class, i::next);
    }

    @Test
    void testToString() {
        assertEquals(LINE0, lines[0].toString());
        assertEquals(LINE1, lines[1].toString());
    }
}