package com.putoet.day4;

import java.util.regex.Pattern;

public record Range(int lower, int upper) {
    public Range {
        assert lower <= upper;
    }

    private static final Pattern RANGE_PATTERN = Pattern.compile("(\\d+)-(\\d+)");
    public static Range of(String range) {
        final var matcher = RANGE_PATTERN.matcher(range);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid range pattern: " + range);

        return new Range(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
    }
}
