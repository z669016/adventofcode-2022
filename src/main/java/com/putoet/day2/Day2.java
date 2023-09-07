package com.putoet.day2;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

import java.util.List;

public class Day2 {
    public static void main(String[] args) {
        final var input = ResourceLines.list("/day2.txt");

        Timer.run(() -> part1(input));
        Timer.run(() -> part2(input));
    }

    static void part1(List<String> input) {
        final var score = input.stream().map(Game::of).mapToInt(Game::score).sum();
        System.out.println("The total score according to the strategy guide is " + score);
    }

    static void part2(List<String> input) {
        final var score = input.stream().map(Game::of2).mapToInt(Game::score).sum();
        System.out.println("The total score according to the Elf's explanation is " + score);
    }
}
