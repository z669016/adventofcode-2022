package com.putoet.day1;

import com.putoet.day.Day;
import com.putoet.resources.ResourceLines;

import java.util.List;

public class Day1 extends Day {
    private final List<Elf> elves = Elves.from(ResourceLines.list("/day1.txt"));

    public Day1(String[] args) {
        super(args);
    }

    public static void main(String[] args) {
        final var day = new Day1(args);
        day.challenge();
    }

    @Override
    public void part1() {
        System.out.println("The elf carrying the most calories has " + Elves.mostCalories(elves).orElseThrow().calories());
    }

    @Override
    public void part2() {
        System.out.println("The top three elves carry " + Elves.topThreeMostCalories(elves));
    }
}
