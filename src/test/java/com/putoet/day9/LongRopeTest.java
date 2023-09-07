package com.putoet.day9;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongRopeTest {
    private static final List<Instruction> instructions = ResourceLines.stream("/day9.txt")
            .map(Instruction::of)
            .toList();

    @Test
    void move() {
        final var ropes = LongRope.start(10).move(instructions);
        assertEquals(1, ropes.stream().map(Rope::tail).collect(Collectors.toSet()).size());
    }

    @Test
    void bigMove() {
        final var input = """
                R 5
                U 8
                L 8
                D 3
                R 17
                D 10
                L 25
                U 20""";
        final var instructions = Arrays.stream(input.split("\n"))
                .map(Instruction::of)
                .toList();
        final var ropes = LongRope.start(10).move(instructions);
        assertEquals(36, ropes.stream().map(Rope::tail).collect(Collectors.toSet()).size());
    }
}