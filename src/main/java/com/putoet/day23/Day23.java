package com.putoet.day23;

import com.putoet.day.Day;
import com.putoet.resources.ResourceLines;

import java.util.List;

public class Day23 extends Day {
    private final List<String> input = ResourceLines.list("/day23.txt");

    public static void main(String[] args) {
        final var day = new Day23();
        day.challenge();
    }

    @Override
    public void part1() {
        final ValidDirections validDirections = new ValidDirections();
        final Elves elves = Elves.from(input);
        for (int i = 0; i < 10; i++)
            elves.move(validDirections.get());

        System.out.println("Empty ground tiles is " + elves.emptyGrounds());
    }

    @Override
    public void part2() {
        final ValidDirections validDirections = new ValidDirections();
        final Elves elves = Elves.from(ResourceLines.list("/day23.txt"));

        int i = 1;
        while (elves.move(validDirections.get()) != 0)
            i++;

        System.out.println("The number of the first round where no Elf moves is " + i);
    }
}
