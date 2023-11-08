package com.putoet.day22;

import com.putoet.grid.GridUtils;
import com.putoet.grid.Point;
import org.jetbrains.annotations.NotNull;

class StrangeBoard {
    protected final char[][] grid;

    protected Point location;
    protected Facing facing = Facing.WEST;

    public StrangeBoard(char[][] grid) {
        this.grid = grid;
        start();
    }

    public void start() {
        for (var x = 0; x < grid[0].length; x++)
            if (grid[0][x] == MoveStrategy.OPEN) {
                location = Point.of(x, 0);
                break;
            }
    }

    public Point location() {
        return location;
    }

    public Facing facing() {
        return facing;
    }

    @Override
    public String toString() {
        return "location=" + location +
               ", facing=" + facing +
               ", board=[" + "\n" +
               String.join("\n", GridUtils.toList(grid)) +
               "]";
    }

    public void turnLeft() {
        facing = switch (facing) {
            case WEST -> Facing.NORTH;
            case SOUTH -> Facing.WEST;
            case EAST -> Facing.SOUTH;
            case NORTH -> Facing.EAST;
        };
    }

    public void turnRight() {
        facing = switch (facing) {
            case WEST -> Facing.SOUTH;
            case SOUTH -> Facing.EAST;
            case EAST -> Facing.NORTH;
            case NORTH -> Facing.WEST;
        };
    }

    public Location move(@NotNull MoveStrategy strategy, int distance) {
        while (distance-- > 0) {
            var next = move(strategy);

            location = next.point();
            facing = next.facing();
        }

        return new Location(location, facing);
    }

    public Location move(@NotNull MoveStrategy strategy) {
        // find the next valid grid location (i.e. non-void)
        var next = strategy.nextLocation(location, facing);
        while (grid[next.point().y()][next.point().x()] == MoveStrategy.VOID) {
            next = strategy.nextLocation(next.point(), next.facing());
        }

        // don't move if the next location is a wall
        if (grid[next.point().y()][next.point().x()] == MoveStrategy.WALL)
            return new Location(location, facing);

        // open location found
        return next;
    }
}
