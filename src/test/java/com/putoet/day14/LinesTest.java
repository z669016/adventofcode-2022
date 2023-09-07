package com.putoet.day14;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinesTest {

    @Test
    void asGrid() {
        final var lines = ResourceLines.stream("/day14.txt").map(Line::from).toList();
        final var grid = Lines.asSmallGrid(lines);

        assertEquals(493, grid.minX());
        assertEquals(505, grid.maxX());
        assertEquals(0, grid.minY());
        assertEquals(11, grid.maxY());

        System.out.println(grid);
    }

    @Test
    void full() {
        final var lines = ResourceLines.stream("/day14.txt").map(Line::from).toList();
        final var grid = Lines.asSmallGrid(lines);
        System.out.println(grid);
    }
}