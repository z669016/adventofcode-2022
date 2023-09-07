package com.putoet.day14;

import com.putoet.grid.Grid;
import com.putoet.grid.Point;
import org.jetbrains.annotations.NotNull;

class Sand {
    public static final char OPEN = '.';
    public static final char SAND = 'o';

    public static final Point START = Point.of(500, 0);

    public static long pour(@NotNull Grid grid) {
        var count = 0L;

        var next = next(grid, START);
        while (next != null && next.y() < grid.maxY() - 1) {
            count++;
            grid.set(next.x(), next.y(), SAND);

            next = next(grid, START);
        }

        return count;
    }

    public static long fill(@NotNull Grid grid) {
        var count = 0L;

        var next = next(grid, START);
        while (next != null && next.y() < grid.maxY()) {
            count++;
            grid.set(next.x(), next.y(), SAND);

            next = next(grid, START);
        }

        return count;
    }

    private static Point next(Grid grid, Point sand) {
        Point prev = null;
        var next = sand;

        while (grid.contains(next.x(), next.y()) && grid.get(next.x(), next.y()) == OPEN) {
            prev = next;

            // move down one line
            next = Point.of(next.x(), next.y() + 1);

            // If one-down is on the grid or that position is not open
            if (grid.contains(next.x(), next.y()) && grid.get(next.x(), next.y()) != OPEN) {
                // move one to the left
                next = Point.of(next.x() - 1, next.y());
            }

            // If one-down and one-left is on the grid or that position is not open
            if (grid.contains(next.x(), next.y()) && grid.get(next.x(), next.y()) != OPEN) {
                // move two to the right
                next = Point.of(next.x() + 2, next.y());
            }
        }

        return prev;
    }
}
