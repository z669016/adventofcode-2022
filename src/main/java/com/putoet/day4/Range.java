package com.putoet.day4;


import org.javatuples.Pair;

import java.util.regex.Pattern;

public record Range(Pair<Integer,Integer> range) {
    public Range {
        assert range != null;
        assert range.getValue0() != null && range.getValue1() != null;
        assert range.getValue0() <= range.getValue1();
    }

    public int lower() {
        return range.getValue0();
    }

    public int upper() {
        return range.getValue1();
    }

    private static final Pattern RANGE_PATTERN = Pattern.compile("(\\d+)-(\\d+)");
    public static Range of(String range) {
        final var matcher = RANGE_PATTERN.matcher(range);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid range pattern: " + range);

        return new Range(Pair.with(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2))));
    }

}
