package com.putoet.day12;

import com.putoet.grid.Grid;
import com.putoet.grid.Point;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Stream;

record HeightMap(@NotNull Grid grid) {
    public Point start() {
        return grid.findFirst(c -> c == 'S').orElseThrow();
    }

    public Point end() {
        return grid.findFirst(c -> c == 'E').orElseThrow();
    }

    public List<Point> findAllLowest() {
        return grid.findAll(c -> c == 'a' || c == 'S');
    }

    public List<Point> next(@NotNull Point current) {
        final var height = grid.get(current.x(), current.y());
        final var start = start();

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
