package com.putoet.day12;

import com.putoet.day.Day;
import com.putoet.grid.Grid;
import com.putoet.grid.GridUtils;
import com.putoet.grid.Point;
import com.putoet.resources.ResourceLines;

import java.util.List;

public class Day12 extends Day {
    private final HeightMap heights;

    public Day12() {
        heights = new HeightMap(new Grid(GridUtils.of(ResourceLines.list("/day12.txt"))));
    }

    public static void main(String[] args) {
        final var day = new Day12();
        day.challenge();
    }

    @Override
    public void part1() {
        final List<Point> found = Finder.solve(heights);
        System.out.println("The fewest steps required to move from your current position to the location that should get the best signal is " + (found.size() - 1));
    }

    @Override
    public void part2() {
        final List<Point> starts = heights.findAllLowest();
        final int shortest = starts.stream()
                .parallel()
                .map(s -> Finder.solve(heights, s))
                .filter(l -> l.size() > 0)
                .mapToInt(l -> l.size() - 1)
                .min()
                .orElseThrow();

        System.out.println("The fewest steps required to move from any 'a' to the location that should get the best signal is " + shortest);
    }
}
