package com.putoet.day8;

import com.putoet.day.Day;
import com.putoet.grid.GridUtils;
import com.putoet.resources.ResourceLines;

public class Day8 extends Day {
    private final char[][] grid;

    public Day8() {
        grid = GridUtils.of(ResourceLines.list("/day8.txt"));
    }

    public static void main(String[] args) {
        final var day = new Day8();
        day.challenge();
    }

    @Override
    public void part1() {
        final Forrest forrest = new Forrest(grid);
        System.out.println("The number of visible trees is " + forrest.visibleTrees());
    }

    @Override
    public void part2() {
        final Forrest forrest = new Forrest(grid);
        System.out.println("The max scenic score is " + forrest.highestScenicScore());
    }
}
