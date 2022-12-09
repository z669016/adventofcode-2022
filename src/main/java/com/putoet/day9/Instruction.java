package com.putoet.day9;

public record Instruction(Direction direction, int distance) {
    public Instruction {
        assert direction != null;
        assert distance > 0;
    }

    public static Instruction of(String line) {
        assert line != null;
        line = line.trim();

        final String[] split = line.split(" ");
        return new Instruction(Direction.of(split[0]), Integer.parseInt(split[1]));
    }
}
