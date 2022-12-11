package com.putoet.day11;

import com.putoet.day.Day;
import com.putoet.resources.ResourceLines;

import java.util.List;

public class Day11 extends Day {
    public Day11(String[] args) {
        super(args);
    }

    public static void main(String[] args) {
        final var day = new Day11(args);
        day.challenge();
    }

    @Override
    public void part1() {
        List<Monkey> monkeys = Game.monkeys(ResourceLines.list("/day11.txt"));
        Game.rounds(monkeys, 20);

        System.out.println("The level of monkey business after 20 rounds of stuff-slinging simian shenanigans is "
            + Game.monkeyBusiness(monkeys));
    }

    @Override
    public void part2() {
        List<Monkey> monkeys = Game.monkeys(ResourceLines.list("/day11.txt"), value -> value);
        Game.rounds(monkeys, 10_000);

        System.out.println("The level of monkey business after 10.000 rounds of stuff-slinging simian shenanigans is "
                           + Game.monkeyBusiness(monkeys));
    }
}
