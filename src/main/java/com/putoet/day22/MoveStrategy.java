package com.putoet.day22;

import com.putoet.grid.Point;
import org.javatuples.Pair;

public interface MoveStrategy {
    char VOID = ' ';
    char OPEN = '.';
    char WALL = '#';

    Pair<Point, Facing> nextLocation(Point location, Facing facing);
}
