package com.putoet.day12;

import com.putoet.grid.Point;
import com.putoet.resources.ResourceLines;
import org.jgrapht.alg.shortestpath.BFSShortestPath;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HeightMapTest {
    private static final HeightMap heights = new HeightMap(Day12.createGraph(ResourceLines.list("/day12.txt")));

    @Test
    void createGraph() {
        assertEquals(40, heights.graph().vertexSet().size());
        assertEquals(111, heights.graph().edgeSet().size());
    }

    @Test
    void start() {
        assertEquals(new HeightPoint(Point.of(0, 0), 'S'), heights.start());
    }

    @Test
    void end() {
        assertEquals(new HeightPoint(Point.of(5, 2), 'E'), heights.end());
    }

    @Test
    void findAllLowest() {
        assertEquals(List.of(
                new HeightPoint(Point.of(0, 0), 'S'),
                new HeightPoint(Point.of(1, 0), 'a'),
                new HeightPoint(Point.of(0, 1), 'a'),
                new HeightPoint(Point.of(0, 2), 'a'),
                new HeightPoint(Point.of(0, 3), 'a'),
                new HeightPoint(Point.of(0, 4), 'a')
        ), heights.findAllLowest());
    }

    @Test
    void part1() {
        final var found = BFSShortestPath.findPathBetween(heights.graph(), heights.start(), heights.end());
        assertEquals(31, found.getLength());
    }
}