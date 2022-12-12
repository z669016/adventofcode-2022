package com.putoet.day12;

import com.putoet.grid.Grid;
import com.putoet.grid.GridUtils;
import com.putoet.grid.Point;
import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FinderTest {
    private final HeightMap heights = new HeightMap(new Grid(GridUtils.of(ResourceLines.list("/day12.txt"))));

    @Test
    void solve() {
        final List<Point> found = Finder.solve(heights);
        assertEquals(31, found.size() - 1);
    }

    @Test
    void solveAll() {
        final List<Point> starts = heights.findAllLowest();
        final int shortest = starts.stream()
                .map(s -> Finder.solve(heights, s))
                .filter(l -> l.size() > 0)
                .mapToInt(l -> l.size() - 1)
                .min()
                .orElseThrow();
        assertEquals(29, shortest);
    }
}