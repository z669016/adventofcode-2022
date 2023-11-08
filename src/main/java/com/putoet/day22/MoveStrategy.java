package com.putoet.day22;

import com.putoet.grid.Point;
import org.jetbrains.annotations.NotNull;

interface MoveStrategy {
    char VOID = ' ';
    char OPEN = '.';
    char WALL = '#';

    Location nextLocation(@NotNull Point location, @NotNull Facing facing);
}
