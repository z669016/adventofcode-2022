package com.putoet.day17;

import org.jetbrains.annotations.NotNull;

record Rock(@NotNull String name, char[][] grid) {
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
