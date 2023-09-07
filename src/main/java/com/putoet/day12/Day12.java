package com.putoet.day12;

import com.putoet.grid.Grid;
import com.putoet.grid.GridUtils;
import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

public class Day12 {
    public static void main(String[] args) {
        final var heights = new HeightMap(new Grid(GridUtils.of(ResourceLines.list("/day12.txt"))));

        Timer.run(() -> part1(heights));
        Timer.run(() -> part2(heights));
    }

    static void part1(HeightMap heights) {
        final var found = Finder.solve(heights);
        System.out.println("The fewest steps required to move from your current position to the location that should get the best signal is " + (found.size() - 1));
    }

    static void part2(HeightMap heights) {
        final var starts = heights.findAllLowest();
        final var shortest = starts.stream()
                .parallel()
                .map(s -> Finder.solve(heights, s))
                .filter(l -> !l.isEmpty())
                .mapToInt(l -> l.size() - 1)
                .min()
                .orElseThrow();

        System.out.println("The fewest steps required to move from any 'a' to the location that should get the best signal is " + shortest);
    }
}
