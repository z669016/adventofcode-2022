package com.putoet.day20;

import com.putoet.day.Day;
import com.putoet.resources.ResourceLines;

import java.util.List;

public class Day20 extends Day {
    private final List<Integer> input;

    public Day20(String[] args) {
        super(args);
        input = ResourceLines.list("/day20.txt", Integer::parseInt);
    }

    public static void main(String[] args) {
        final var day = new Day20(args);
        day.challenge();
    }

    @Override
    public void part1() {
        final Decrypter decrypter = Decrypter.from(input);
        decrypter.mix();
        System.out.println("The sum of the three numbers that form the grove coordinates is " +
                           decrypter.groveCoordinates().stream().mapToLong(i -> i).sum());
    }

    @Override
    public void part2() {
        final Decrypter decrypter = Decrypter.from(input, 811589153);
        decrypter.mix(10);
        System.out.println("The sum of the three numbers that form the grove coordinates with key 811589153 is " +
                           decrypter.groveCoordinates().stream().mapToLong(i -> i).sum());
    }
}
