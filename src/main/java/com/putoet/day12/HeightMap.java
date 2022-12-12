package com.putoet.day12;

import com.putoet.grid.Grid;
import com.putoet.grid.Point;

import java.util.List;
import java.util.stream.Stream;

public record HeightMap(Grid grid) {
    public HeightMap {
        assert grid != null;
    }

    public Point start() {
        return grid.findFirst(c -> c == 'S').orElseThrow();
    }

    public Point end() {
        return grid.findFirst(c -> c == 'E').orElseThrow();
    }

    public List<Point> findAllLowest() {
        return grid.findAll(c -> c == 'a' || c == 'S');
    }

    public List<Point> next(Point current) {
        final char height = grid.get(current.x(), current.y());
        final Point start = start();

        return Stream.of(Point.NORTH, Point.EAST, Point.SOUTH, Point.WEST)
                .map(current::add)
                .filter(p -> grid.contains(p.x(), p.y()))
                .filter(p -> !p.equals(start))
                .filter(p -> difference(height, grid.get(p.x(), p.y())) < 2)
                .toList();
    }

    private static int difference(char from, char to) {
        if (to == 'S')
            throw new IllegalStateException("Should not move to S");
        if (from == 'E')
            throw new IllegalStateException("Should not move from E");

        if (from == 'S')
            return 1;

        if (to == 'E')
            to = 'z';

        return to - from;
    }
}
