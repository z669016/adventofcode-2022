package com.putoet.day24;

import com.putoet.grid.Point;

public record Blizzard(Point location, char symbol, Point facing, int max, Point reset) {

    public static final Point NORTH = Point.of(0, -1);
    public static final Point EAST = Point.of(1, 0);
    public static final Point SOUTH = Point.of(0, 1);
    public static final Point WEST = Point.of(-1, 0);

    public Blizzard(Point location, char symbol, int max, Point reset) {
        this(location, symbol, switch (symbol) {
            case '^' -> NORTH;
            case '>' -> EAST;
            case 'v' -> SOUTH;
            case '<' -> WEST;
            default -> throw new IllegalArgumentException("Invalid blizzard symbol '" + symbol + "'");
        }, max, reset);
    }
    public Blizzard next() {
        Point next = location.add(facing);
        if (wrapsAt(next))
            next = reset;

        return new Blizzard(next, symbol, facing, max, reset);
    }

    public boolean wrapsAt(Point next) {
        return switch (symbol) {
            case '^' -> next.y() == 0;
            case '>' -> next.x() > max;
            case 'v' -> next.y() > max;
            case '<' -> next.x() == 0;
            default -> throw new IllegalStateException("Cannot get here");
        };
    }
}
