package com.putoet.day9;

import com.putoet.day.Day;
import com.putoet.resources.ResourceLines;

import java.util.List;
import java.util.stream.Collectors;

public class Day9 extends Day {
    private final List<Instruction> instructions;

    public Day9() {
        instructions = ResourceLines.list("/day9.txt", Instruction::of);
    }

    public static void main(String[] args) {
        final var day = new Day9();
        day.challenge();
    }

    @Override
    public void part1() {
        final List<Rope> ropes = ShortRope.start().move(instructions);
        final int count = ropes.stream().map(Rope::tail).collect(Collectors.toSet()).size();
        System.out.println("The number of positions visited by the tail of the rope at least once is " + count);
    }

    @Override
    public void part2() {
        final List<Rope> ropes = LongRope.start(10).move(instructions);
        final int count = ropes.stream().map(Rope::tail).collect(Collectors.toSet()).size();
        System.out.println("The number of positions visited by the tail of the rope at least once is " + count);
    }
}
