package com.putoet.day4;

import org.jetbrains.annotations.NotNull;

record RangePair(@NotNull Range first, @NotNull Range second) {
    public boolean containment() {
        return containment(first, second) || containment(second, first);
    }

    private static boolean containment(Range first, Range second) {
        return first.lower() >= second.lower() && first.upper() <= second.upper();
    }

    public boolean overlap() {
        return overlap(first, second) || overlap(second, first);
    }

    public static boolean overlap(@NotNull Range first, @NotNull Range second) {
        return (first.lower() >= second.lower() && first.lower() <= second.upper()) ||
               (first.upper() >= second.lower() && first.upper() <= second.upper());
    }

    public static RangePair of(@NotNull String rangePair) {
        final var split = rangePair.split(",");
        assert split.length == 2;

        return new RangePair(Range.of(split[0]), Range.of(split[1]));
    }
}
