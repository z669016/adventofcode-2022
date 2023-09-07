package com.putoet.day14;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SandTest {

    @Test
    void poor() {
        final var lines = ResourceLines.stream("/day14.txt").map(Line::from).toList();
        final var grid = Lines.asSmallGrid(lines);
        final var count = Sand.pour(grid);

        assertEquals(24L, count);
        System.out.println(grid);
    }

    @Test
    void fill() {
        final var lines = ResourceLines.stream("/day14.txt").map(Line::from).toList();
        final var grid = Lines.asLargeGrid(lines);
        final var count = Sand.fill(grid);

        assertEquals(93L, count);
        System.out.println(grid);
    }
}