package com.putoet.day22;

import com.putoet.grid.Point;
import org.jetbrains.annotations.NotNull;

record BoardStrategy(char[][] grid) implements MoveStrategy {
    @Override
    public Location nextLocation(@NotNull Point location, @NotNull Facing facing) {
        // move one step in the facing direction
        final var next = location.add(facing.point());

        // wrap x/y if necessary
        if (next.y() >= grid.length)
            return new Location(Point.of(location.x(), 0), facing);

        if (next.y() < 0)
            return new Location(Point.of(location.x(), grid.length - 1), facing);

        if (next.x() >= grid[next.y()].length)
            return new Location(Point.of(0, location.y()), facing);

        if (next.x() < 0)
            return new Location(Point.of(grid[next.y()].length - 1, location.y()), facing);

        // No wrapping required
        return new Location(next, facing);
    }
}