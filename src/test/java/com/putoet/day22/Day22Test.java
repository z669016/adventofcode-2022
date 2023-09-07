package com.putoet.day22;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day22Test {

    @Test
    void boardInput() {
        final var boardInput = Day22.boardInput();
        for (var line : boardInput)
            assertEquals(16, line.length());
    }

    @Test
    void pathInput() {
        assertEquals("10R5L5R10L4R5L5", Day22.pathInput());
    }
}