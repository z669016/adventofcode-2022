package com.putoet.day5;

import com.putoet.resources.ResourceLines;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day5Test {
    private static final Pair<List<Instruction>,List<String>> input = Day5.init(ResourceLines.list("/day5.txt"));

    @Test
    void part1() {
        final var instructions = input.getValue0();
        final var stacks = input.getValue1();
        final Cargo cargo = new Cargo(stacks.stream().map(Crates::new).toList());

        assertEquals("CMZ", Day5.move(instructions, cargo, Day5.crateMover9000));
    }

    @Test
    void part2() {
        final var instructions = input.getValue0();
        final var stacks = input.getValue1();
        final Cargo cargo = new Cargo(stacks.stream().map(Crates::new).toList());

        assertEquals("MCD", Day5.move(instructions, cargo, Day5.crateMover9001));
    }
}