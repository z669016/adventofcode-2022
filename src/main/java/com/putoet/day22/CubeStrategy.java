package com.putoet.day22;

import com.putoet.grid.Point;
import org.jetbrains.annotations.NotNull;

record CubeStrategy(char[][] grid) implements MoveStrategy {
    // 1 going up   : 6 right x = 0, y = x + 100
    // 1 going right: 2 right x = x, y = y
    // 1 going down : 3 down x = x, y = y
    // 1 going left : 5 right x = 0, y = 149 - y
    public static Location moveFromOne(@NotNull Point location, @NotNull Facing facing) {
        return switch (facing) {
            case NORTH -> new Location(Point.of(0, location.x() + 100), Facing.WEST);
            case WEST, SOUTH -> new Location(location.add(facing.point()), facing);
            case EAST -> new Location(Point.of(0, 149 - location.y()), Facing.WEST);
        };
    }

    // 2 going up   : 6 up, x = x - 100, y = 199
    // 2 going right: 4 left x = 100, y = 149 - y
    // 2 going down : 3 left x = 99, y = x - 50
    // 2 going left : 1 left x = x, y = y
    public static Location moveFromTwo(@NotNull Point location, @NotNull Facing facing) {
        return switch (facing) {
            case NORTH -> new Location(Point.of(location.x() - 100, 199), Facing.NORTH);
            case WEST -> new Location(Point.of(99, 149 - location.y()), Facing.EAST);
            case SOUTH -> new Location(Point.of(99, location.x() - 50), Facing.EAST);
            case EAST -> new Location(location.add(facing.point()), facing);
        };
    }

    // 3 going up   : 1 up x = x, y = y
    // 3 going right: 2 up , x = y + 50, y = 49
    // 3 going down : 4 down, x = x, y = y
    // 3 going left : 5 down, x = y - 50, y = 100
    public static Location moveFromThree(@NotNull Point location, @NotNull Facing facing) {
        return switch (facing) {
            case NORTH, SOUTH -> new Location(location.add(facing.point()), facing);
            case WEST -> new Location(Point.of(location.y() + 50, 49), Facing.NORTH);
            case EAST -> new Location(Point.of(location.y() - 50, 100), Facing.SOUTH);
        };
    }

    // 4 going up   : 3 up, x = x, y = y
    // 4 going right: 2 left x = 149, y = 49 - (y - 100)
    // 4 going down : 6 left x = 49, y = x + 100
    // 4 going left : 5 left x = x, y = y
    public static Location moveFromFour(@NotNull Point location, @NotNull Facing facing) {
        return switch (facing) {
            case NORTH, EAST -> new Location(location.add(facing.point()), facing);
            case WEST -> new Location(Point.of(149, 49 - (location.y() - 100)), Facing.EAST);
            case SOUTH -> new Location(Point.of(49, location.x() + 100), Facing.EAST);
        };
    }

    // 5 going up   : 3 right x = 50, y = x + 50
    // 5 going right: 4 right x = x, y = y
    // 5 going down : 6 down, x = x, y = y
    // 5 going left : 1 right, x = 50, y = 49 - (y - 100)
    public static Location moveFromFive(@NotNull Point location, @NotNull Facing facing) {
        return switch (facing) {
            case NORTH -> new Location(Point.of(50, location.x() + 50), Facing.WEST);
            case WEST, SOUTH -> new Location(location.add(facing.point()), facing);
            case EAST -> new Location(Point.of(50, 49 - (location.y() - 100)), Facing.WEST);
        };
    }

    // 6 going up   : 5 up, x = x, y = y
    // 6 going right: 4 up, x = y - 100, y = 149
    // 6 going down : 2 down, x = x + 100, y = 0
    // 6 going left : 1 down, x = y - 100, y = 0
    public static Location moveFromSix(@NotNull Point location, @NotNull Facing facing) {
        return switch (facing) {
            case NORTH -> new Location(location.add(facing.point()), facing);
            case WEST -> new Location(Point.of(location.y() - 100, 149), Facing.NORTH);
            case SOUTH -> new Location(Point.of(location.x() + 100, 0), Facing.SOUTH);
            case EAST -> new Location(Point.of(location.y() - 100, 0), Facing.SOUTH);
        };
    }

    @Override
    public Location nextLocation(@NotNull Point location, @NotNull Facing facing) {
        // move one step in the facing direction
        final var next = location.add(facing.point());
        return switch (wrapping(location, next)) {
            case 0 -> new Location(next, facing);
            case 1 -> moveFromOne(location, facing);
            case 2 -> moveFromTwo(location, facing);
            case 3 -> moveFromThree(location, facing);
            case 4 -> moveFromFour(location, facing);
            case 5 -> moveFromFive(location, facing);
            case 6 -> moveFromSix(location, facing);
            default -> throw new IllegalStateException("Invalid wrapping value ...");
        };
    }

    private int wrapping(Point location, Point next) {
        if (next.x() >= 0 && next.x() < grid[0].length &&
            next.y() >= 0 && next.y() < grid.length) {
            if (grid[next.y()][next.x()] != VOID)
                return 0;
        }

        if (location.y() < 50) {
            if (location.x() < 50)
                throw new IllegalStateException("Invalid location");
            else
                return (location.x() < 100) ? 1 : 2;
        }

        if (location.y() < 100) {
            if (location.x() < 50 || location.x() > 99)
                throw new IllegalStateException("Invalid location");
            else
                return 3;
        }

        if (location.y() < 150) {
            if (location.x() > 99)
                throw new IllegalStateException("Invalid location");
            else
                return (location.x() < 50) ? 5 : 4;
        }

        if (location.x() > 49)
            throw new IllegalStateException("Invalid location");

        return 6;
    }
}
