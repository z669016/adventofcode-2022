package com.putoet.day12;

import com.putoet.grid.Grid;
import com.putoet.grid.GridUtils;
import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FinderTest {
    private final HeightMap heights = new HeightMap(new Grid(GridUtils.of(ResourceLines.list("/day12.txt"))));

    @Test
    void solve() {
        final var found = Finder.solve(heights);
        assertEquals(31, found.size() - 1);
    }

    @Test
    void solveAll() {
        final var starts = heights.findAllLowest();
        final int shortest = starts.stream()
                .map(s -> Finder.solve(heights, s))
                .filter(l -> !l.isEmpty())
                .mapToInt(l -> l.size() - 1)
                .min()
                .orElseThrow();
        assertEquals(29, shortest);
    }
}