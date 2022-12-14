package com.putoet.day2;

import com.putoet.day.Day;
import com.putoet.resources.ResourceLines;

import java.util.List;

public class Day2 extends Day {
    private final List<String> input;

    public Day2() {
        input = ResourceLines.list("/day2.txt");
    }

    public static void main(String[] args) {
        final var day = new Day2();
        day.challenge();
    }

    @Override
    public void part1() {
        final var score = input.stream().map(Game::of).mapToInt(Game::score).sum();
        System.out.println("The total score according to the strategy guide is " + score);
    }

    @Override
    public void part2() {
        final var score = input.stream().map(Game::of2).mapToInt(Game::score).sum();
        System.out.println("The total score according to the Elf's explanation is " + score);
    }
}
