package com.putoet.day14;

import com.putoet.day.Day;
import com.putoet.grid.Grid;
import com.putoet.resources.ResourceLines;

import java.util.List;

public class Day14 extends Day {
    final private List<Line> lines;

    public Day14(String[] args) {
        super(args);

        lines = ResourceLines.stream("/day14.txt").map(Line::from).toList();
    }

    public static void main(String[] args) {
        final var day = new Day14(args);
        day.challenge();
    }

    @Override
    public void part1() {
        final Grid grid = Lines.asSmallGrid(lines);
        final long count = Sand.pour(grid);
        System.out.println("The number of units of sand come to rest before sand starts flowing into the abyss below is " + count);
    }

    @Override
    public void part2() {
        final Grid grid = Lines.asLargeGrid(lines);
        final long count = Sand.fill(grid);
        System.out.println("The number of units of sand come to rest before sand stops flowing is " + count);

        // 25725 is too low
    }
}