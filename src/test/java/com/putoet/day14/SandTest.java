package com.putoet.day14;

import com.putoet.grid.Grid;
import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SandTest {

    @Test
    void poor() {
        final List<Line> lines = ResourceLines.stream("/day14.txt").map(Line::from).toList();
        final Grid grid = Lines.asSmallGrid(lines);

        final long count = Sand.pour(grid);
        assertEquals(24L, count);

        System.out.println(grid);
    }

    @Test
    void fill() {
        final List<Line> lines = ResourceLines.stream("/day14.txt").map(Line::from).toList();
        final Grid grid = Lines.asLargeGrid(lines);

        final long count = Sand.fill(grid);
        assertEquals(93L, count);

        System.out.println(grid);
    }
}