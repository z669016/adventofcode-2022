package com.putoet.day;

import org.apache.commons.lang3.time.StopWatch;

public abstract class Day {
    protected final String[] args;

    protected Day(String[] args) {
        this.args = args;
    }

    public void challenge() {
        final StopWatch part1Timer = StopWatch.createStarted();
        part1();
        part1Timer.stop();
        System.out.println("Part one ran for " + part1Timer.getTime() + " ms");

        final StopWatch part2Timer = StopWatch.createStarted();
        part2();
        part2Timer.stop();
        System.out.println("Part two ran for " + part2Timer.getTime() + " ms");
        System.out.println("Running the entire challenge took " + (part1Timer.getTime() + part2Timer.getTime()) + " ms");
    }

    public void part1() {
        System.out.println("Not yet implemented");
    }

    public void part2() {
        System.out.println("Not yet implemented");
    }
}
