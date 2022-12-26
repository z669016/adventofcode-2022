package com.putoet.day22;

import com.putoet.grid.Point;

public enum Facing {
    WEST(Point.of(1, 0)),
    EAST(Point.of(-1, 0)),
    NORTH(Point.of(0, -1)),
    SOUTH(Point.of(0, 1));

    private final Point point;

    Facing(Point point) {
        this.point = point;
    }

    Point point() {
        return point;
    }

    int score() {
        return switch (this) {
            case WEST -> 0;
            case SOUTH -> 1;
            case EAST -> 2;
            case NORTH -> 3;
        };
    }
}
