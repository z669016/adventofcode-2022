package com.putoet.day17;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

public class Day17 {
    public static void main(String[] args) {
        final var input = ResourceLines.line("/day17.txt");

        Timer.run(() -> part1(input));
        Timer.run(() -> part2(input));
    }

    static void part1(String input) {
        final var push = new Push(input);
        final var cave = new Cave(push);

        cave.run();

        System.out.println("The tower of rocks after 2022 rocks have stopped falling has height: " + cave.highestRock());
    }

    static void part2(String input) {
        final var maxRocks = 1_000_000_000_000L;
        final var analytics = analytics(input);
        System.out.println("The tower of rocks after " + maxRocks + " rocks have stopped falling has height: " +
                           heightAfter(input, analytics, maxRocks));
    }

    static CaveAnalytics.Analytics analytics(String input) {
        final var cave = new CaveAnalytics(new Push(input));
        cave.run();
        return cave.analytics();
    }

    static long heightAfter(String input, CaveAnalytics.Analytics analytics, long maxRocks) {
        final var push = new Push(input);
        final var cave = new CaveSequence(push, analytics.startRepeat(), analytics.repeatBlockSize());
        cave.run();

        final var sequence = cave.sequence();
        final var blockSize = sequence.list().stream().mapToInt(i -> i).sum();

        var height = sequence.start() +
                      ((maxRocks - analytics.startRepeat()) / analytics.repeatBlockSize()) * blockSize;
        for (var i = 0; i < (maxRocks - analytics.startRepeat()) % analytics.repeatBlockSize(); i++)
            height += sequence.list().get(i);

        return height;
    }
}
