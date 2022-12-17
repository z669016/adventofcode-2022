package com.putoet.day16;

import com.putoet.day.Day;
import com.putoet.resources.ResourceLines;

import java.util.Map;

public class Day16 extends Day {
    private final Map<String,Valve> valves;

    public Day16(String[] args) {
        super(args);
        valves = Valves.from(ResourceLines.list("/day16.txt"));
    }

    public static void main(String[] args) {
        final var day = new Day16(args);
        day.challenge();
    }

    @Override
    public void part1() {
        System.out.println("The most pressure you can release is " + PathFinder.bestPath(valves));
    }

    @Override
    public void part2() {
        System.out.println("The most pressure you can release collaborating with the elephant is " + PathFinder.bestPathWithElephant(valves));
    }
}
