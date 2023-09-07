package com.putoet.day4;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

import java.util.List;

public class Day4 {
    public static void main(String[] args) {
        final var rangePairs = ResourceLines.list("/day4.txt", RangePair::of);

        Timer.run(() -> System.out.println("Containing pairs on the input are " + containingPairs(rangePairs)));
        Timer.run(() -> System.out.println("Overlapping pairs on the input are " + overlappingPairs(rangePairs)));
    }

    static long containingPairs(List<RangePair> rangePairs) {
        return rangePairs.stream()
                .filter(RangePair::containment)
                .count();
    }

    static long overlappingPairs(List<RangePair> rangePairs) {
        return rangePairs.stream()
                .filter(RangePair::overlap)
                .count();
    }
}
