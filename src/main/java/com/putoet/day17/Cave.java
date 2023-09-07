package com.putoet.day17;

import com.putoet.grid.GridUtils;
import com.putoet.grid.Point;
import org.jetbrains.annotations.NotNull;

class Cave implements Runnable {
    public static final int MAX_ROCKS = 2022;
    public static final char OPEN = '.';
    public static final char ROCK = '#';

    private final char[][] grid;
    private final Push push;
    private final Rocks rocks;
    private final int maxRocks;
    private int highestRock = 0;

    public Cave(@NotNull Push push) {
        this(push, MAX_ROCKS);
    }

    public Cave(@NotNull Push push, int maxRocks) {
        this.push = push;
        this.rocks = new Rocks();
        this.maxRocks = maxRocks;
        this.grid = GridUtils.of(0, 7, 0, maxRocks * Rocks.maxHeight() + 2, '.');
    }

    public int highestRock() {
        return highestRock;
    }

    public char[][] grid() {
        return grid;
    }

    public Push push() {
        return push;
    }

    public Rocks rocks() {
        return rocks;
    }

    public int maxRocks() {
        return maxRocks;
    }

    @Override
    public void run() {
        for (var i = 0; i < maxRocks; i++)
            drop(rocks.get(), push);
    }

    public int drop(@NotNull Rock rock, @NotNull Push push) {
        var down = false;
        var next = Point.of(2, highestRock + 3);
        var prev = next;

        while (fits(rock, next)) {
            prev = next;

            if (down) {
                next = Point.of(next.x(), next.y() - 1);
                down = false;
            } else {
                final var direction = push.get();

                next = Point.of(next.x() + (direction == Push.LEFT ? -1 : +1), next.y());
                if (next.x() < 0 || next.x() + rock.width() > grid[0].length)
                    next = Point.of(prev.x(), next.y());

                // if you cannot move sideways, ignore the move,
                // for you might be able to move down again
                if (!fits(rock, next))
                    next = prev;

                down = true;
            }
        }

        final var oldHeight = highestRock;
        draw(rock, prev);
        final var newHeight = highestRock;

        return newHeight - oldHeight;
    }

    private void draw(Rock rock, Point point) {
        final var shape = rock.grid();

        for (var y = 0; y < rock.height(); y++) {
            for (var x = 0; x < rock.width(); x++) {
                if (shape[y][x] != OPEN)
                    grid[point.y() + y][point.x() + x] = shape[y][x];
            }
        }

        highestRock = Math.max(point.y() + rock.height(), highestRock);
    }

    public boolean fits(@NotNull Rock rock, @NotNull Point point) {
        if (point.y() < 0)
            return false;

        final var shape = rock.grid();

        for (var y = 0; y < rock.height(); y++) {
            for (var x = 0; x < rock.width(); x++) {
                if (grid[point.y() + y][point.x() + x] == ROCK && shape[y][x] == ROCK)
                    return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        final var sb = new StringBuilder();
        for (int y = highestRock + 1; y >= 0; y--) {
            sb.append("%4d |".formatted(y));
            for (var x = 0; x < grid[0].length; x++)
                sb.append(grid[y][x]);
            sb.append("|\n");
        }

        sb.append("     +-------+\n");
        return sb.toString();
    }
}
