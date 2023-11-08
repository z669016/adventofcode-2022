package com.putoet.day5;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day5Test {
    private static final Day5.Input input = Day5.init(ResourceLines.list("/day5.txt"));

    @Test
    void part1() {
        final Cargo cargo = new Cargo(input.stacks().stream().map(Crates::new).toList());

        assertEquals("CMZ", Day5.move(input.instructions(), cargo, Day5.crateMover9000));
    }

    @Test
    void part2() {
        final Cargo cargo = new Cargo(input.stacks().stream().map(Crates::new).toList());

        assertEquals("MCD", Day5.move(input.instructions(), cargo, Day5.crateMover9001));
    }
}