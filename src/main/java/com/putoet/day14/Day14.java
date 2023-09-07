package com.putoet.day14;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

import java.util.List;

public class Day14  {
    public static void main(String[] args) {
        final var lines = ResourceLines.list("/day14.txt", Line::from);

        Timer.run(() -> part1(lines));
        Timer.run(() -> part2(lines));
    }

    static void part1(List<Line> lines) {
        final var grid = Lines.asSmallGrid(lines);
        final var count = Sand.pour(grid);
        System.out.println("The number of units of sand come to rest before sand starts flowing into the abyss below is " + count);
    }

    static void part2(List<Line> lines) {
        final var grid = Lines.asLargeGrid(lines);
        final var count = Sand.fill(grid);
        System.out.println("The number of units of sand come to rest before sand stops flowing is " + count);
    }
}
