package com.putoet.day21;

import com.putoet.day.Day;
import com.putoet.resources.ResourceLines;

import java.util.Optional;

public class Day21 extends Day {
    private final Monkeys monkeys;

    public Day21(String[] args) {
        super(args);
        monkeys = Monkeys.from(ResourceLines.list("/day21.txt"));
    }

    public static void main(String[] args) {
        final var day = new Day21(args);
        day.challenge();
    }

    @Override
    public void part1() {
        System.out.println("The monkey named root will yell " + monkeys.get());
    }

    @Override
    public void part2() {
        final var root = monkeys.get("root");
        monkeys.put(root.name(), new Monkey(root.name(), new MonkeyOperation() {
            @Override
            public Long get() {
                return (monkeys.get(left().orElseThrow()).get() == monkeys.get(right().orElseThrow()).get()) ? 1L : 0L;
            }

            @Override
            public Optional<String> left() {
                return root.left();
            }

            @Override
            public Optional<String> right() {
                return root.right();
            }
        }));
    }
}
