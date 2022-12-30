package com.putoet.day4;

import com.putoet.day.Day;
import com.putoet.resources.ResourceLines;

import java.util.List;

public class Day4 extends Day {
    private final List<RangePair> rangePairs;

    public Day4() {
        rangePairs = ResourceLines.list("/day4.txt", RangePair::of);
    }

    public static void main(String[] args) {
        final var day = new Day4();
        day.challenge();
    }

    @Override
    public void part1() {
        System.out.println("Containing pairs on the input are " + containingPairs());
    }

    public long containingPairs() {
        return rangePairs.stream()
                .filter(RangePair::containment)
                .count();
    }

    @Override
    public void part2() {
        System.out.println("Overlapping pairs on the input are " + overlappingPairs());
    }

    public long overlappingPairs() {
        return rangePairs.stream()
                .filter(RangePair::overlap)
                .count();
    }
}
