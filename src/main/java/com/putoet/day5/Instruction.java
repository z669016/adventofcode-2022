package com.putoet.day5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Instruction(int count, int from, int to) {
    private static final Pattern MOVE_PATTERN = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)");
    public static Instruction of(String instruction) {
        final Matcher matcher = MOVE_PATTERN.matcher(instruction);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid move instruction: " + instruction);

        return new Instruction(
                Integer.parseInt(matcher.group(1)),
                Integer.parseInt(matcher.group(2)),
                Integer.parseInt(matcher.group(3))
        );
    }
}
