package com.putoet.day25;

import com.putoet.day.Day;
import com.putoet.resources.ResourceLines;

import java.util.List;

public class Day25 extends Day {
    private final List<Snafu> input = ResourceLines.stream("/day25.txt")
            .map(Snafu::new)
            .toList();

    public Day25(String[] args) {
        super(args);
    }

    public static void main(String[] args) {
        final var day = new Day25(args);
        day.challenge();
    }

    @Override
    public void part1() {
        final long sum = input.stream()
                .mapToLong(Snafu::asDecimal)
                .sum();
        System.out.println("The SNAFU number to supply to Bob's console is " + Snafu.from(sum));
    }

    @Override
    public void part2() {
    }
}
