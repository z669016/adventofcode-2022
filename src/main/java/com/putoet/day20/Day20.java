package com.putoet.day20;

import com.putoet.day.Day;
import com.putoet.resources.ResourceLines;

public class Day20 extends Day {
    private final int[] numbers;

    public Day20(String[] args) {
        super(args);

        numbers = ResourceLines.stream("/day20.txt")
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static void main(String[] args) {
        final var day = new Day20(args);
        day.challenge();
    }

    @Override
    public void part1() {
        final Decrypter decrypter = new Decrypter(numbers);
        final var sum = decrypter.mix().groveCoordinates().stream().mapToInt(i -> i).sum();
        System.out.println("The sum of the grove coordinates is: " + sum);

        // 2738 is too low
    }

    @Override
    public void part2() {
    }
}
