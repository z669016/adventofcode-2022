package com.putoet.day15;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

record Range(int lower, int upper) {
    static Set<Range> combine(@NotNull Set<Range> ranges, @NotNull Range toCombine) {
        final var newSet = new HashSet<Range>();

        for (var range : ranges) {
            if (!range.overlaps(toCombine)) {
                newSet.add(range);
            } else {
                toCombine = toCombine.combine(range);
            }
        }
        newSet.add(toCombine);

        return newSet;
    }

    public int count() {
        return upper - lower + 1;
    }

    public Range combine(@NotNull Range toCombine) {
        assert overlaps(toCombine);

        return new Range(Math.min(lower, toCombine.lower), Math.max(upper, toCombine.upper));
    }

    public boolean overlaps(@NotNull Range other) {
        return (lower <= other.lower && other.lower <= upper) ||
               (lower <= other.upper && other.upper <= upper) ||
               (other.lower <= lower && lower <= other.upper) ||
               (other.lower <= upper && upper <= other.upper);
    }
}
