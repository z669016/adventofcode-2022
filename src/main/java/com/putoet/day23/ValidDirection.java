package com.putoet.day23;

import com.putoet.grid.Point;
import org.jetbrains.annotations.NotNull;

import java.util.List;

record ValidDirection(@NotNull List<Point> options) {
    public static final ValidDirection NORTH = new ValidDirection(List.of(Point.of(-1,-1), Point.of(0,-1), Point.of(1,-1)));
    public static final ValidDirection WEST = new ValidDirection(List.of(Point.of(-1,-1), Point.of(-1,0), Point.of(-1,1)));
    public static final ValidDirection SOUTH = new ValidDirection(List.of(Point.of(1,1), Point.of(0,1), Point.of(-1,1)));
    public static final ValidDirection EAST = new ValidDirection(List.of(Point.of(1,1), Point.of(1,0), Point.of(1,-1)));

    public ValidDirection {
        assert options.size() == 3;
    }

    public Point move() {
        return options.get(1);
    }
}
