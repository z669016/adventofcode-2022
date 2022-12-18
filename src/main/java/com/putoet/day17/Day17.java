package com.putoet.day17;

import com.putoet.day.Day;
import com.putoet.resources.ResourceLines;

public class Day17 extends Day {
    private final String input = ResourceLines.line("/day17.txt");
    public Day17(String[] args) {
        super(args);
    }

    public static void main(String[] args) {
        final var day = new Day17(args);
        day.challenge();
    }

    @Override
    public void part1() {
        final var push = new Push(input);
        final var cave = new Cave(push);

        cave.run();

        // System.out.println(cave);
        System.out.println("The tower of rocks after 2022 rocks have stopped falling has height: " + cave.highestRock());
    }

    @Override
    public void part2() {
        final var maxRocks = 1_000_000_000_000L;
        final var analytics = analytics();
        System.out.println("The tower of rocks after " + maxRocks + " rocks have stopped falling has height: " +
                           heightAfter(analytics, maxRocks));
    }

    public CaveAnalytics.Analytics analytics() {
        final var cave = new CaveAnalytics(new Push(input));
        cave.run();
        return cave.analytics();
    }

    public long heightAfter(CaveAnalytics.Analytics analytics, long maxRocks) {
        final var push = new Push(input);
        final var cave = new CaveSequence(push, analytics.startRepeat(), analytics.repeatBlockSize());
        cave.run();

        final var sequence = cave.sequence();
        final int blockSize = sequence.list().stream().mapToInt(i -> i).sum();

        long height = sequence.start() +
                      ((maxRocks - analytics.startRepeat()) / analytics.repeatBlockSize()) * blockSize;
        for (int i = 0; i < (maxRocks - analytics.startRepeat()) % analytics.repeatBlockSize(); i++)
            height += sequence.list().get(i);

        return height;
    }
}
