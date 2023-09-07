package com.putoet.day1;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

public class Day1 {
    public static void main(String[] args) {
        final var elves = Elves.of(ResourceLines.list("/day1.txt"));

        Timer.run(() ->
            System.out.println("The elf carrying the most calories has " + Elves.mostCalories(elves).orElseThrow().calories())
        );

        Timer.run(() ->
            System.out.println("The top three elves carry " + Elves.topThreeMostCalories(elves))
        );
    }
}
