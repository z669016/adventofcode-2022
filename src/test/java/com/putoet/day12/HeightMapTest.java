package com.putoet.day12;

import com.putoet.grid.Grid;
import com.putoet.grid.GridUtils;
import com.putoet.grid.Point;
import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HeightMapTest {
    private final HeightMap heights = new HeightMap(new Grid(GridUtils.of(ResourceLines.list("/day12.txt"))));

    @Test
    void start() {
        assertEquals(Point.of(0,0), heights.start());
    }

    @Test
    void end() {
        assertEquals(Point.of(5,2), heights.end());
    }

    @Test
    void next() {
        assertEquals(List.of(Point.of(0,1), Point.of(1, 0)), heights.next(heights.start()));
        assertEquals(List.of(Point.of(5,0), Point.of(3, 0)), heights.next(Point.of(4,0)));
        assertEquals(List.of(Point.of(6, 1), Point.of(5, 0), Point.of(4, 1)), heights.next(Point.of(5,1)));
    }

    @Test
    void findAllLowest() {
        assertEquals(List.of(
                Point.of(0, 0),
                Point.of(1, 0),
                Point.of(0, 1),
                Point.of(0, 2),
                Point.of(0, 3),
                Point.of(0, 4)
        ), heights.findAllLowest());
    }
}