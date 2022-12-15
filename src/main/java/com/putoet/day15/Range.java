package com.putoet.day15;

import java.util.HashSet;
import java.util.Set;

public record Range(int lower, int upper) {
    static Set<Range> combine(Set<Range> ranges, Range toCombine) {
        final Set<Range> newSet = new HashSet<>();

        for (Range range : ranges) {
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

    public Range combine(Range toCombine) {
        assert toCombine != null;
        assert overlaps(toCombine);

        return new Range(Math.min(lower, toCombine.lower), Math.max(upper, toCombine.upper));
    }

    public boolean overlaps(Range other) {
        assert other != null;

        return (lower <= other.lower && other.lower <= upper) ||
               (lower <= other.upper && other.upper <= upper) ||
               (other.lower <= lower && lower <= other.upper) ||
               (other.lower <= upper && upper <= other.upper);
    }
}
