package com.putoet.day16;

import com.putoet.day.Day;
import com.putoet.resources.ResourceLines;

public class Day16 extends Day {
    private final Valves finder;

    public Day16() {
        finder = new Valves(ResourceLines.stream("/day16.txt").map(Valve::from).toList());
    }

    public static void main(String[] args) {
        final var day = new Day16();
        day.challenge();
    }

    @Override
    public void part1() {
        System.out.println("The most pressure you can release is " + finder.maximumPressure());
    }

    @Override
    public void part2() {
        System.out.println("The most pressure you can release collaborating with the elephant is " + finder.maximumPressureWithHelp());
    }
}
