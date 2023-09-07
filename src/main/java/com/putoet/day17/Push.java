package com.putoet.day17;

import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

class Push implements Supplier<Character> {
    public static Character LEFT = '<';
    public static Character RIGHT = '>';

    private final char[] instructions;
    private int offset = 0;

    public Push(@NotNull String instructions) {
        this.instructions = instructions.toCharArray();
    }

    @Override
    public Character get() {
        if (offset >= instructions.length)
            offset = 0;

        return instructions[offset++];
    }
}
