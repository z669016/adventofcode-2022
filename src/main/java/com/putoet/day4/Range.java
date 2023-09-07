package com.putoet.day4;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

record Range(int lower, int upper) {
    public Range {
        if (lower > upper) {
            int temp = upper;
            upper = lower;
            lower = temp;
        }
    }

    private static final Pattern RANGE_PATTERN = Pattern.compile("(\\d+)-(\\d+)");

    public static Range of(@NotNull String range) {
        final var matcher = RANGE_PATTERN.matcher(range);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid range pattern: " + range);

        return new Range(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
    }
}
