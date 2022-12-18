package com.putoet.day17;

public record Rock(String name, char[][] grid) {
    public Rock {
        assert name != null;
        assert grid != null;
    }

    @Override
    public String toString() {
        return name();
    }

    public int height() {
        return grid.length;
    }

    public int width() {
        return grid[0].length;
    }
}
