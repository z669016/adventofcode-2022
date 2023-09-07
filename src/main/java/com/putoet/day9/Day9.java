package com.putoet.day9;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

import java.util.List;
import java.util.stream.Collectors;

public class Day9 {
    public static void main(String[] args) {
        final var instructions = ResourceLines.list("/day9.txt", Instruction::of);

        Timer.run(() -> part1(instructions));
        Timer.run(() -> part2(instructions));
    }

    static void part1(List<Instruction> instructions) {
        final var ropes = ShortRope.start().move(instructions);
        final var count = ropes.stream().map(Rope::tail).collect(Collectors.toSet()).size();
        System.out.println("The number of positions visited by the tail of the rope at least once is " + count);
    }

    static void part2(List<Instruction> instructions) {
        final var ropes = LongRope.start(10).move(instructions);
        final var count = ropes.stream().map(Rope::tail).collect(Collectors.toSet()).size();
        System.out.println("The number of positions visited by the tail of the rope at least once is " + count);
    }
}
