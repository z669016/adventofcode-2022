package com.putoet.day14;

import com.putoet.grid.Grid;
import com.putoet.grid.GridUtils;

import java.util.List;

public class Lines {
    public static final char LINE = '#';
    public static final char OPEN = '.';

    public static Grid asSmallGrid(List<Line> lines){
        final int minx = lines.stream().mapToInt(Line::minX).min().orElseThrow() - 1;
        final int maxx = lines.stream().mapToInt(Line::maxX).max().orElseThrow() + 2;
        final int miny = 0;
        final int maxy = lines.stream().mapToInt(Line::maxY).max().orElseThrow() + 2;

        final Grid grid = new Grid(minx, maxx, miny, maxy, GridUtils.of(minx, maxx, miny, maxy, OPEN));

        for (var line : lines)
            draw(grid, line);

        return grid;
    }

    public static Grid asLargeGrid(List<Line> lines){
        final int minx = lines.stream().mapToInt(Line::minX).min().orElseThrow() - 1;
        final int maxx = lines.stream().mapToInt(Line::maxX).max().orElseThrow() + 2;
        final int dx = (maxx - minx) * 2;
        final int miny = 0;
        final int maxy = lines.stream().mapToInt(Line::maxY).max().orElseThrow() + 2;


        final Grid grid = new Grid(minx - dx, maxx + dx, miny, maxy, GridUtils.of(minx - dx, maxx + dx, miny, maxy, '.'));

        for (var line : lines)
            draw(grid, line);

        return grid;
    }

    public static void draw(Grid grid, Line line) {
        var subLines = line.parts();
        while (subLines.hasNext()) {
            final var part = subLines.next();
            for (int y = part.minY(); y <= part.maxY(); y++) {
                for (int x = part.minX(); x <= part.maxX(); x++) {
                    grid.set(x, y, LINE);
                }
            }
        }
    }
}
