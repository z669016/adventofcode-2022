package com.putoet.day22;

import com.putoet.grid.Point;
import org.javatuples.Pair;

public class BoardStrategy implements MoveStrategy {
    private final char[][] grid;

    public BoardStrategy(char[][] grid) {
        this.grid = grid;
    }

    @Override
    public Pair<Point, Facing> nextLocation(Point location, Facing facing) {
        // move one step in the facing direction
        final Point next = location.add(facing.point());

        // wrap x/y if necessary
        if (next.y() >= grid.length)
            return Pair.with(Point.of(location.x(), 0), facing);

        if (next.y() < 0)
            return Pair.with(Point.of(location.x(), grid.length - 1), facing);

        if (next.x() >= grid[next.y()].length)
            return Pair.with(Point.of(0, location.y()), facing);

        if (next.x() < 0)
            return Pair.with(Point.of(grid[next.y()].length - 1, location.y()), facing);

        // No wrapping required
        return Pair.with(next, facing);
    }
}