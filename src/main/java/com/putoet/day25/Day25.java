package com.putoet.day25;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

import java.util.List;

public class Day25 {
    public static void main(String[] args) {
        final var input = ResourceLines.stream("/day25.txt")
                .map(Snafu::new)
                .toList();

        Timer.run(() -> part1(input));
    }

    static void part1(List<Snafu> input) {
        final var sum = input.stream()
                .mapToLong(Snafu::asDecimal)
                .sum();
        System.out.println("The SNAFU number to supply to Bob's console is " + Snafu.of(sum));
    }
}
