package com.putoet.day9;

public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    static Direction of(String direction) {
        return switch (direction) {
            case "U" -> UP;
            case "D" -> DOWN;
            case "L" -> LEFT;
            case "R" -> RIGHT;
            default -> throw new IllegalArgumentException("Invalid direction: " + direction);
        };
    }
}
