package com.putoet.day22;

import com.putoet.grid.GridUtils;
import com.putoet.grid.Point;
import org.javatuples.Pair;

public class StrangeBoard {

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

    public Pair<Point,Facing> move(MoveStrategy strategy, int distance) {
        while (distance-- > 0) {
            var next = move(strategy);

            location = next.getValue0();
            facing = next.getValue1();
        }

        return Pair.with(location, facing);
    }

    public Pair<Point,Facing> move(MoveStrategy strategy) {
        // find the next valid grid location (i.e. non void)
        Pair<Point,Facing> next = strategy.nextLocation(location, facing);

        while (grid[next.getValue0().y()][next.getValue0().x()] == MoveStrategy.VOID) {
            next = strategy.nextLocation(next.getValue0(), next.getValue1());
        }

        // don't move if the next location is a wall
        if (grid[next.getValue0().y()][next.getValue0().x()] == MoveStrategy.WALL)
            return Pair.with(location, facing);

        // open location found
        return next;
    }
}
