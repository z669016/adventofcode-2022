package com.putoet.day14;

import com.putoet.grid.Grid;
import com.putoet.grid.GridUtils;
import org.jetbrains.annotations.NotNull;

import java.util.List;

class Lines {
    public static final char LINE = '#';
    public static final char OPEN = '.';

    public static Grid asSmallGrid(@NotNull List<Line> lines){
        final var minx = lines.stream().mapToInt(Line::minX).min().orElseThrow() - 1;
        final var maxx = lines.stream().mapToInt(Line::maxX).max().orElseThrow() + 2;
        final var miny = 0;
        final var maxy = lines.stream().mapToInt(Line::maxY).max().orElseThrow() + 2;
        final var grid = new Grid(minx, maxx, miny, maxy, GridUtils.of(minx, maxx, miny, maxy, OPEN));

        for (var line : lines)
            draw(grid, line);

        return grid;
    }

    public static Grid asLargeGrid(@NotNull List<Line> lines){
        final var minx = lines.stream().mapToInt(Line::minX).min().orElseThrow() - 1;
        final var maxx = lines.stream().mapToInt(Line::maxX).max().orElseThrow() + 2;
        final var dx = (maxx - minx) * 2;
        final var miny = 0;
        final var maxy = lines.stream().mapToInt(Line::maxY).max().orElseThrow() + 2;
        final var grid = new Grid(minx - dx, maxx + dx, miny, maxy, GridUtils.of(minx - dx, maxx + dx, miny, maxy, '.'));

        for (var line : lines)
            draw(grid, line);

        return grid;
    }

    public static void draw(@NotNull Grid grid, @NotNull Line line) {
        var subLines = line.parts();
        while (subLines.hasNext()) {
            final var part = subLines.next();
            for (var y = part.minY(); y <= part.maxY(); y++) {
                for (var x = part.minX(); x <= part.maxX(); x++) {
                    grid.set(x, y, LINE);
                }
            }
        }
    }
}
