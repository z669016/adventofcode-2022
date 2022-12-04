package com.putoet.day4;

import org.javatuples.Pair;

public record RangePair(Pair<Range,Range> pair) {
    public RangePair {
        assert pair.getValue0() != null && pair.getValue1() != null;
    }

    public Range first () {
        return pair.getValue0();
    }

    public Range second () {
        return pair.getValue1();
    }

    public boolean containment() {
        return containment(first(), second()) || containment(second(), first());
    }

    private static boolean containment(Range first, Range second) {
        return first.lower() >= second.lower() && first.upper() <= second.upper();
    }

    public boolean overlap() {
        return overlap(first(), second()) || overlap(second(), first());
    }

    public static boolean overlap(Range first, Range second) {
        return (first.lower() >= second.lower() && first.lower() <= second.upper()) ||
                (first.upper() >= second.lower() && first.upper() <= second.upper());
    }

    public static RangePair of (String rangePair) {
        final String[] split = rangePair.split(",");
        assert split.length == 2;

        return new RangePair(Pair.with(Range.of(split[0]), Range.of(split[1])));
    }
}
