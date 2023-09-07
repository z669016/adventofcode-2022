package com.putoet.day20;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

import java.util.List;

public class Day20 {
    public static void main(String[] args) {
        final var input = ResourceLines.list("/day20.txt", Integer::parseInt);

        Timer.run(() -> part1(input));
        Timer.run(() -> part2(input));
    }

    static void part1(List<Integer> input) {
        final var decrypter = Decrypter.of(input);
        decrypter.mix();
        System.out.println("The sum of the three numbers that form the grove coordinates is " +
                           decrypter.groveCoordinates().stream().mapToLong(i -> i).sum());
    }

    static void part2(List<Integer> input) {
        final var decrypter = Decrypter.of(input, 811589153);
        decrypter.mix(10);
        System.out.println("The sum of the three numbers that form the grove coordinates with key 811589153 is " +
                           decrypter.groveCoordinates().stream().mapToLong(i -> i).sum());
    }
}
