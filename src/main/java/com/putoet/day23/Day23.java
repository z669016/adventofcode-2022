package com.putoet.day23;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

import java.util.List;

public class Day23  {
    public static void main(String[] args) {
         final List<String> input = ResourceLines.list("/day23.txt");

        Timer.run(() -> part1(input));
        Timer.run(() -> part2(input));
    }

    static void part1(List<String> input) {
        final var validDirections = new ValidDirections();
        final var elves = Elves.of(input);
        for (var i = 0; i < 10; i++)
            elves.move(validDirections.get());

        System.out.println("Empty ground tiles is " + elves.emptyGrounds());
    }

    static void part2(List<String> input) {
        final var validDirections = new ValidDirections();
        final var elves = Elves.of(input);

        var i = 1;
        while (elves.move(validDirections.get()) != 0)
            i++;

        System.out.println("The number of the first round where no Elf moves is " + i);
    }
}
