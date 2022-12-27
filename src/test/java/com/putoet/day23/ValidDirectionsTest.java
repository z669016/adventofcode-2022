package com.putoet.day23;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValidDirectionsTest {

    @Test
    void get() {
        final ValidDirections validDirections = new ValidDirections();
        assertEquals(List.of(ValidDirection.NORTH, ValidDirection.SOUTH, ValidDirection.WEST, ValidDirection.EAST), validDirections.get());
        assertEquals(List.of(ValidDirection.SOUTH, ValidDirection.WEST, ValidDirection.EAST, ValidDirection.NORTH), validDirections.get());
        assertEquals(List.of(ValidDirection.WEST, ValidDirection.EAST, ValidDirection.NORTH, ValidDirection.SOUTH), validDirections.get());
        assertEquals(List.of(ValidDirection.EAST, ValidDirection.NORTH, ValidDirection.SOUTH, ValidDirection.WEST), validDirections.get());
        assertEquals(List.of(ValidDirection.NORTH, ValidDirection.SOUTH, ValidDirection.WEST, ValidDirection.EAST), validDirections.get());
    }
}