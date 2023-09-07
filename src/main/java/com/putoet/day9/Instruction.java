package com.putoet.day9;

import org.jetbrains.annotations.NotNull;

record Instruction(@NotNull Direction direction, int distance) {
    public Instruction {
        assert distance > 0;
    }

    public static Instruction of(@NotNull String line) {
        final var split = line.trim().split(" ");
        return new Instruction(Direction.of(split[0]), Integer.parseInt(split[1]));
    }
}
