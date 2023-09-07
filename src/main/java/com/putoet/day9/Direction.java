package com.putoet.day9;

import org.jetbrains.annotations.NotNull;

enum Direction {
    UP, DOWN, LEFT, RIGHT;

    static Direction of(@NotNull String direction) {
        return switch (direction) {
            case "U" -> UP;
            case "D" -> DOWN;
            case "L" -> LEFT;
            case "R" -> RIGHT;
            default -> throw new IllegalArgumentException("Invalid direction: " + direction);
        };
    }
}
