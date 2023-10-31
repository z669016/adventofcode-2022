package com.putoet.day12;

import com.putoet.grid.Point;

public record HeightPoint(Point point, char label) {
    private static final int BASE = 'a';

    public int distance(HeightPoint other) {
        return Math.abs(this.height() - other.height());
    }

    private int height() {
        return switch (this.label()) {
            case 'S' -> 'a' - BASE;
            case 'E' -> 'z' - BASE;
            default -> this.label() - BASE;
        };
    }
}
